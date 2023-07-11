package com.stagedriving.modules.payment.controller;

import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.http.serializer.Json;
import com.paypal.orders.*;
import com.paypal.payments.CapturesRefundRequest;
import com.paypal.payments.Money;
import com.paypal.payments.Refund;
import com.paypal.payments.RefundRequest;
import com.stagedriving.Service;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

@Singleton
@Slf4j
public class PaymentController {

    @Data
    public class Result {
        private String statusCode;
        private String statusMessage;
        private String providerId;
        private Double refundedAmount;
        private Double providerFee;
    }

    @Inject
    private AppConfiguration configuration;

    public String generateToken(String customerId) {
        ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
                .customerId(customerId);
        String clientToken = Service.braintreeGateway.clientToken().generate(clientTokenRequest);

        return clientToken;
    }

    //// PAYPAL

    /**
     * Method to capture order after creation. Pass a valid, approved order ID
     * an argument to this method.
     *
     * @param orderId Authorization ID from authorizeOrder response
     * @param debug   true = print response data
     * @return HttpResponse<Capture> response received from API
     * @throws IOException Exceptions from API if any
     */
    public Result captureOrder(String orderId, boolean debug) {
        Result result = new Result();
        try {
            PayPalHttpClient client = new PayPalHttpClient(Service.payPalEnvironment);

            OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
            request.requestBody(new OrderRequest());
            //3. Call PayPal to capture an order
            HttpResponse<Order> response = null;
            response = client.execute(request);
            //4. Save the capture ID to your database. Implement logic to save capture to your database for future reference.
            if (debug) {
                log.info("Status Code: " + response.statusCode());
                log.info("Status: " + response.result().status());
                log.info("Order ID: " + response.result().id());
                log.info("Links: ");
                for (LinkDescription link : response.result().links()) {
                    log.info("\t" + link.rel() + ": " + link.href());
                }
                log.info("Capture ids:");
                for (PurchaseUnit purchaseUnit : response.result().purchaseUnits()) {
                    for (Capture capture : purchaseUnit.payments().captures()) {
                        log.info("\t" + capture.id());
                    }
                }
                log.info("Buyer: ");
                Payer buyer = response.result().payer();
                log.info("\tEmail Address: " + buyer.email());
                log.info("\tName: " + buyer.name().fullName());
                log.info("\tPhone Number: " + buyer.phoneWithType());
            }

            result.setStatusCode(String.valueOf(response.statusCode()));
            result.setStatusMessage(response.result().status());
            for (PurchaseUnit purchaseUnit : response.result().purchaseUnits()) {
                for (Capture capture : purchaseUnit.payments().captures()) {
                    result.setProviderId(capture.id());
                    result.setProviderFee(Double.valueOf(capture.sellerReceivableBreakdown().paypalFee().value()));
                }
            }
        } catch (Exception ex) {
            log.error("Oops", ex);
            throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INTERNAL_ERROR, "Problems processing your payment");
        }
        return result;
    }

    public Result refundOrder(String captureId, Double amount, boolean debug) {
        log.info("Refunding amount "+amount+" on order "+captureId);
        Result result = new Result();
        try {
            PayPalHttpClient client = new PayPalHttpClient(Service.payPalEnvironment);

            CapturesRefundRequest request = new CapturesRefundRequest(captureId);
            request.prefer("return=representation");

            String amountStr = String.format(Locale.ROOT, "%.2f", amount);

            RefundRequest refundRequest = new RefundRequest();
            Money money = new Money();
            money.currencyCode("EUR");
            money.value(amountStr);
            refundRequest.amount(money);
            request.requestBody(refundRequest);

            HttpResponse<Refund> response = client.execute(request);
            if (debug) {
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Status: " + response.result().status());
                System.out.println("Refund Id: " + response.result().id());
                System.out.println("Links: ");

                System.out.println("Full response body:");
                System.out.println(new JSONObject(new Json()
                        .serialize(response.result())).toString(4));
            }

            result.setStatusCode(String.valueOf(response.statusCode()));
            result.setStatusMessage(response.result().status());
            if (response.result().sellerPayableBreakdown() != null) {
                result.setRefundedAmount(Double.valueOf(response.result().sellerPayableBreakdown().netAmount().value()));
                result.setProviderFee(Double.valueOf(response.result().sellerPayableBreakdown().paypalFee().value()));
            }

        } catch (Exception ex) {
            log.error("Oops", ex);
            throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INTERNAL_ERROR, "Problems processing your payment");
        }
        return result;
    }

    //// END PAYPAL

    //// BRAINTREE

    public com.braintreegateway.Result<Transaction> createTransaction(Double amount, String nonceFromTheClient) {
        com.braintreegateway.Result<Transaction> result = null;
        try {
            String amountStr = String.format(Locale.ROOT, "%.2f", amount);
            TransactionRequest request = new TransactionRequest()
                    .amount(new BigDecimal(amountStr))
                    .paymentMethodNonce(nonceFromTheClient)
                    .options()
                    .submitForSettlement(true)
                    .done();

            result = Service.braintreeGateway.transaction().sale(request);
        } catch (Exception ex) {
            log.error("Oops", ex);
            throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INTERNAL_ERROR, "Problems processing your payment");
        }
        return result;
    }

    public com.braintreegateway.Result<Transaction> refundTransaction(Double amount, String transactionId) {
        String amountStr = String.format(Locale.ROOT, "%.2f", amount);

        Transaction transaction = Service.braintreeGateway.transaction().find(transactionId);

        com.braintreegateway.Result<Transaction> result = null;
        if (transaction.getStatus() == Transaction.Status.SUBMITTED_FOR_SETTLEMENT) {
            // can void
            result = Service.braintreeGateway.transaction().voidTransaction(transactionId);
        } else if (transaction.getStatus() == Transaction.Status.SETTLED) {
            // will have to refund it
            result = Service.braintreeGateway.transaction().refund(transactionId, new BigDecimal(amountStr));
        }

        return result;
    }

    //// END BRAINTREE

}
