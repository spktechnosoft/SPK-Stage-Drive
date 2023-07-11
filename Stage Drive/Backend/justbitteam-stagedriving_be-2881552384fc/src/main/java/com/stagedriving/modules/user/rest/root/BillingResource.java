package com.stagedriving.modules.user.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.BillingDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountBillingDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountBilling;
import com.stagedriving.modules.commons.ds.entities.AccountBillingId;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.AccountBillingMapperImpl;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import com.stagedriving.modules.commons.utils.billing.BillingUtils;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/billings")
@Api(value = "billings", description = "Billings resource")
public class BillingResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BillingResource.class);

    @Inject
    BillingUtils billingUtils;
    @Inject
    AccountBillingMapperImpl billingMapper;
    @Inject
    AccountMapperDecorator accountMapper;
    @Inject
    AccountBillingDAO billingDAO;

    private AppConfiguration configuration;

    @Inject
    public BillingResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "New billing",
            notes = "New billing",
            response = StgdrvResponseDTO.class)
    public Response createBilling(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) BillingDTO billingDto) throws Exception {

        Preconditions.checkNotNull(billingDto.getIban(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_IBAN_NULL));

        Preconditions.checkNotNull(billingDto.getProvider(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_PROVIDER_NULL));

        AccountBillingId accountBillingId = new AccountBillingId();
        accountBillingId.setAccountId(me.getId());
        AccountBilling billing = billingMapper.billingDtoToBilling(billingDto);
        billing.setId(accountBillingId);
        billing.setAccount(me);
        billing.setUid(TokenUtils.generateUid());
        billing.setCreated(new Date());
        billing.setModified(new Date());
        billing.setIban(billingDto.getIban());
        billing.setProvider(billingDto.getProvider());

        billingDAO.create(billing);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(billing.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BILLING_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{billingId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing billing",
            notes = "Modify existing billing",
            response = StgdrvResponseDTO.class)
    public Response modifyAccount(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) BillingDTO billingDto,
                                  @PathParam("billingId") String billingId) {

        Preconditions.checkNotNull(billingDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_DTO_NULL));

        Preconditions.checkNotNull(billingDto.getIban(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_IBAN_NULL));

        Preconditions.checkNotNull(billingDto.getProvider(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_PROVIDER_NULL));

        AccountBilling billing = billingDAO.findByUid(billingId);
        Preconditions.checkNotNull(billing,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_DOES_NOT_EXIST));

        AccountBilling billingOld = billingDAO.findByUid(billingId);
        AccountBilling billingNew = billingMapper.billingDtoToBilling(billingDto);
        billingUtils.merge(billingOld, billingNew);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BILLING_MODIFIED);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieves billings",
            notes = "Retrieves billings",
            response = BillingDTO.class,
            responseContainer = "List")
    public Response getBillings(@ApiParam(value = "Response page size") @QueryParam("limit") String limit,
                                @ApiParam(value = "Response page index") @QueryParam("page") String page) {

        return Response.ok(billingMapper.billingsToBillingDtos(billingDAO.findAll())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{billingId}")
    //@Metered
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve billing",
            notes = "Retrieve billing",
            response = BillingDTO.class)
    public Response getBilling(@PathParam("billingId") String billingId) {

        AccountBilling billing = billingDAO.findByUid(billingId);
        Preconditions.checkNotNull(billing,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_DOES_NOT_EXIST));

        BillingDTO billingDto = billingMapper.billingToBillingDto(billing);
        Account account = billing.getAccount();
        if (account != null) {
            AccountDTO accountDto = accountMapper.accountToAccountDto(billing.getAccount());
            billingDto.setAccount(accountDto);
        }

        return Response.ok(billingDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{billingId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing billing",
            notes = "Delete existing billing",
            response = StgdrvResponseDTO.class)
    public Response deleteBilling(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) @PathParam("billingId") String billingId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.BILLING_DELETE_ERROR);

        AccountBilling billing = billingDAO.findByUid(billingId);
        Preconditions.checkNotNull(billing,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BILLING_DOES_NOT_EXIST));

        billingDAO.delete(billing);

        return Response.ok(responseDto).build();
    }
}
