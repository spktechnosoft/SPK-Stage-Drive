package com.stagedriving.modules.payment.rest;

import com.braintreegateway.ClientTokenRequest;
import com.google.inject.Inject;
import com.stagedriving.Service;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.RideDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.Ride;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/payments")
@Api(value = "payments", description = "Payments resource")
public class PaymentResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PaymentResource.class);

    private AppConfiguration configuration;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private EventDAO eventDAO;

    @Inject
    public PaymentResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/token")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve payment token",
            notes = "Retrieve payment token",
            response = StgdrvResponseDTO.class,
            responseContainer = "List")
    public Response generateToken(@ApiParam(value = "customerId") @QueryParam("customerId") String customerId) {

        ClientTokenRequest clientTokenRequest = new ClientTokenRequest()
                .customerId(customerId);
        String clientToken = Service.braintreeGateway.clientToken().generate(clientTokenRequest);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(clientToken);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.PAYMENT_TOKEN_CREATE);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/redirect")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Redirect")
    public Response redirect(
            @ApiParam(value = "seats") @QueryParam("seats") Integer seats,
            @ApiParam(value = "amount") @QueryParam("amount") Double amount,
            @ApiParam(value = "fee") @QueryParam("fee") Double fee,
            @ApiParam(value = "ride") @QueryParam("ride") String rideId,
            @ApiParam(value = "at") @QueryParam("at") String at,
            @ApiParam(value = "successUrl") @QueryParam("successUrl") String successUrl,
            @ApiParam(value = "failUrl") @QueryParam("failUrl") String failUrl) {

        Ride ride = rideDAO.findByUid(rideId);
        Event event = null;
        if (ride.getEventid() != null) {
            event = eventDAO.findByUid(ride.getEventid());
        }
        if (ride.getFromEventId() != null) {
            event = eventDAO.findByUid(ride.getFromEventId());
        }
        if (ride.getToEventId() != null) {
            event = eventDAO.findByUid(ride.getToEventId());
        }

        amount = ride.getPrice()*seats;
        fee = ride.getFee()*seats;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://stagedriving.com/payment");
        stringBuilder.append("?at=");
        stringBuilder.append(at);
        stringBuilder.append("&ride=");
        stringBuilder.append(ride.getUid());
        stringBuilder.append("&event=");
        stringBuilder.append(event.getUid());
        stringBuilder.append("&amount=");
        stringBuilder.append(amount);
        stringBuilder.append("&fee=");
        stringBuilder.append(fee);
        stringBuilder.append("&successUrl=");
        stringBuilder.append(successUrl);
        stringBuilder.append("&failUrl=");
        stringBuilder.append(failUrl);


        return Response.seeOther(URI.create(stringBuilder.toString())).build();
    }
}
