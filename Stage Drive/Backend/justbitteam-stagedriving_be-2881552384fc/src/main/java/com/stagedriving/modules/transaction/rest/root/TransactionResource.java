/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.transaction.rest.root;

import com.braintreegateway.Result;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.TransactionDTO;
import com.stagedriving.modules.catalog.controller.CatalogController;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import com.stagedriving.modules.commons.mapper.decorators.TransactionMapperDecorator;
import com.stagedriving.modules.payment.controller.PaymentController;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTime;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/transactions")
@Api(value = "transactions", description = "Transactions")
public class TransactionResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TransactionResource.class);

    @Inject
    AccountDAO accountDAO;
    @Inject
    AccountController accountController;
    @Inject
    TransactionMapperDecorator transactionMapper;
    @Inject
    AccountGroupDAO groupDAO;
    @Inject
    EventDAO eventDAO;
    @Inject
    RideDAO rideDAO;
    @Inject
    AccountHasGroupDAO accountHasGroupDAO;
    @Inject
    TransactionDAO transactionDAO;
    @Inject
    AccountMapperDecorator accountMapper;
    @Inject
    PaymentController paymentController;

    private Double standardFee;

    private AppConfiguration configuration;

    @Inject
    public TransactionResource(AppConfiguration configuration, CatalogController catalogController) {
        this.configuration = configuration;

//        String strFee = catalogController.getCatalogFirstItemName(StgdrvData.Catalogs.STANDARD_FEE);
//        if (strFee != null) {
//            standardFee = Double.valueOf(strFee);
//        } else {
        standardFee = 0.2;
//        }

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "New transaction",
            notes = "New transaction",
            response = StgdrvResponseDTO.class)
    public Response addTransaction(@Restricted(required = true) Account me,
                                   @ApiParam(required = true) @Valid TransactionDTO transactionDto) {


        //Add transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setUid(TokenUtils.generateUid());
        newTransaction.setCreated(new Date());
        newTransaction.setModified(new Date());
        newTransaction.setStatus(StgdrvData.TransactionStatusses.PENDING);
        newTransaction.setVisible(true);

        Event event = eventDAO.findByUid(transactionDto.getEventId());
        Ride ride = rideDAO.findByUid(transactionDto.getRideId());

        Preconds.checkConditions(event == null || ride == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_EVENT_NULL);

        newTransaction.setRideId(ride.getId());
        newTransaction.setEventId(event.getId());

        Account fromAccount = me;
        Account toAccount = accountDAO.findByUid(ride.getAccountid());

        Preconds.checkConditions(fromAccount == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_FROM_NULL);

        Preconds.checkConditions(toAccount == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_TO_NULL);

        newTransaction.setAccountIdFrom(fromAccount.getId());
        newTransaction.setAccountIdTo(toAccount.getId());

        newTransaction.setProvider("cash");
        if (transactionDto.getProvider() != null) {
            newTransaction.setProvider(transactionDto.getProvider());
        }
        newTransaction.setProviderToken(transactionDto.getProviderToken());
        newTransaction.setAmount(transactionDto.getAmount());
        newTransaction.setFee(transactionDto.getFee());
        newTransaction.setProviderId(transactionDto.getProviderOrderId());
        if (transactionDto.getFee() == null) {
            newTransaction.setFee(newTransaction.getAmount() * standardFee);
        }
        if (transactionDto.getTotalAmount() == null) {
            transactionDto.setTotalAmount(newTransaction.getAmount() + newTransaction.getFee());
        }
        newTransaction.setTotalAmount(transactionDto.getTotalAmount());
        newTransaction.setCurrency("EUR");
        if (transactionDto.getCurrency() != null) {
            newTransaction.setCurrency(transactionDto.getCurrency());
        }

        // Make braintree transaction
        if (newTransaction.getProvider().equals("braintree") && newTransaction.getProviderToken() != null) {
            Result<com.braintreegateway.Transaction> result = paymentController.createTransaction(newTransaction.getTotalAmount(), newTransaction.getProviderToken());

            Preconds.checkConditions(!result.isSuccess(), StgdrvResponseDTO.Codes.UNABLE_TO_MAKE_TRANSACTION, result.getMessage());

            newTransaction.setProviderId(result.getTarget().getId());
            newTransaction.setStatusMessage(result.getMessage());
        } else if (newTransaction.getProvider().equals("paypal") && newTransaction.getProviderId() != null) {
            PaymentController.Result res = paymentController.captureOrder(newTransaction.getProviderId(), !configuration.isProduction());

            Preconds.checkConditions(res == null, StgdrvResponseDTO.Codes.UNABLE_TO_MAKE_TRANSACTION, "");

            newTransaction.setProviderId(res.getProviderId());
            newTransaction.setStatusMessage(res.getStatusMessage());
            newTransaction.setProviderFee(res.getProviderFee());
        }

        newTransaction.setStatus(StgdrvData.TransactionStatusses.PROCESSED);
        newTransaction.setPayedAt(new Date());

        transactionDAO.create(newTransaction);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setId(newTransaction.getUid());
        responseDto.setMessage(StgdrvMessage.OperationSuccess.TRANSACTION_CREATE);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieves transactions",
            notes = "Retrieves transactions",
            response = TransactionDTO.class,
            responseContainer = "List")
    public Response getTransactions(
            @ApiParam(value = "From Account Id") @QueryParam("fromAccountId") String fromAccountId,
            @ApiParam(value = "To Account Id") @QueryParam("toAccountId") String toAccountId,
            @ApiParam(value = "Event Id") @QueryParam("eventId") String eventId,
            @ApiParam(value = "Ride Id") @QueryParam("rideId") String rideId,
            @Restricted(required = true) Account requestAccount,
            @ApiParam(value = "Order") @QueryParam("order") String order,
            @ApiParam(value = "Sort") @QueryParam("sort") String sort,
            @ApiParam(value = "Id like") @QueryParam("id_like") String idLike,
            @ApiParam(value = "Amount like") @QueryParam("amount_like") String amountLike,
            @ApiParam(value = "Fee like") @QueryParam("fee_like") String feeLike,
            @ApiParam(value = "TotalAmount like") @QueryParam("totalAmount_like") String totalAmountLike,
            @ApiParam(value = "Status like") @QueryParam("status_like") String statusLike,
            @ApiParam(value = "Provider like") @QueryParam("provider_like") String providerLike,
            @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size,
            @ApiParam(value = "Response page size") @QueryParam("limit") @DefaultValue("100") IntParam limit,
            @ApiParam(value = "Response page index") @QueryParam("page") @DefaultValue("0") IntParam page) {

        Account fromAccount = null;
        Account toAccount = null;
        Event event = null;
        Ride ride = null;

        PagedResults<TransactionDTO> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        Boolean isAdmin = accountController.isAdmin(requestAccount);

        // TODO Check for permission

        if (fromAccountId != null) {
            fromAccount = accountDAO.findByUid(fromAccountId);

            Preconds.checkConditions(!requestAccount.equals(fromAccount), StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.INVALID_PERMISSION);
        }
        if (toAccountId != null) {
            toAccount = accountDAO.findByUid(toAccountId);

            Preconds.checkConditions(!requestAccount.equals(toAccount), StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.INVALID_PERMISSION);
        }
        if (eventId != null) {
            event = eventDAO.findByUid(eventId);
        }
        if (rideId != null) {
            ride = rideDAO.findByUid(rideId);
        }

        List<Transaction> transactions = transactionDAO.findByFilters(fromAccount, toAccount, event, ride, sort, order, page.get(), limit.get(), idLike, amountLike, feeLike, totalAmountLike, statusLike, providerLike, results, isAdmin);

        List<TransactionDTO> transactionDTOS = transactionMapper.transactionsToTransactionDtos(transactions);

        if (results != null) {
            results.setData(transactionDTOS);
            return Response.ok(results).build();
        }

        return Response.ok(transactionDTOS).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{transactionId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve transaction",
            notes = "Retrieve transaction",
            response = TransactionDTO.class)
    public Response getTransaction(@Restricted(required = true) Account me,
                                   @PathParam("transactionId") String transactionId) {

        Preconds.checkNotNull(transactionId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_ID_NULL));

        Transaction transaction = transactionDAO.findByUid(transactionId);
        Preconds.checkNotNull(transaction,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_DOES_NOT_EXIST));

        return Response.ok(transactionMapper.transactionToTransactionDto(transaction)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{transactionId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Edit transaction",
            notes = "Edit transaction",
            response = StgdrvResponseDTO.class)
    public Response editTransaction(@ApiParam(required = true) TransactionDTO transactionDto,
                                    @PathParam("transactionId") String transactionId) {

        Transaction transaction = transactionDAO.findByUid(transactionDto.getId());
        Preconds.checkNotNull(transaction,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_DOES_NOT_EXIST));

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(transactionId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.TRANSACTION_MODIFIED);

        return Response.ok(responseDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{transactionId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete transaction",
            notes = "Delete transaction",
            response = StgdrvResponseDTO.class)
    public Response deleteTransaction(@PathParam("transactionId") String transactionId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.MessageError.TRANSACTION_DELETED);

        Transaction transaction = transactionDAO.findByUid(transactionId);
        Preconds.checkConditions(transaction == null || transaction.getRideId() == null || transaction.getStatus().equals(StgdrvData.TransactionStatusses.DELETED),
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_DOES_NOT_EXIST);

        Ride ride = rideDAO.findById(transaction.getRideId());

        Double amountToRefund = transaction.getAmount();
        /*if (transaction.getProviderFee() != null) {
            amountToRefund -= transaction.getProviderFee();
        }*/
//        Preconds.checkConditions(amountToRefund != ride.getPrice(),
//                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TRANSACTION_DELETION_PROBLEM);

        DateTime goingDeparture = new DateTime(ride.getGoingDepartureDate());

        if (goingDeparture.plusHours(24).isAfterNow()) {

            if (transaction.getProvider().equals(StgdrvData.TransactionProviders.BRAINTREE)) {

                Result<com.braintreegateway.Transaction> result = paymentController.refundTransaction(amountToRefund, transaction.getProviderId());
                if (result != null) {
                    Preconds.checkConditions(!result.isSuccess(), StgdrvResponseDTO.Codes.UNABLE_TO_REFUND_TRANSACTION, result.getMessage());
                    transaction.setStatusMessage(result.getMessage());
                }
                transaction.setRefundedAt(new Date());
            } else if (transaction.getProvider().equals(StgdrvData.TransactionProviders.PAYPAL)) {

                PaymentController.Result res = paymentController.refundOrder(transaction.getProviderId(), amountToRefund, !configuration.isProduction());
                transaction.setStatusMessage(res.getStatusMessage());
                transaction.setProviderFee(transaction.getProviderFee()-res.getProviderFee());
                transaction.setRefundedAmount(res.getRefundedAmount());
                transaction.setRefundedAt(new Date());
            }

            transaction.setStatus(StgdrvData.TransactionStatusses.REFUNDED);
        } else {
            transaction.setStatus(StgdrvData.TransactionStatusses.DELETED);
        }

        transactionDAO.edit(transaction);

        return Response.ok(responseDto).build();
    }
}
