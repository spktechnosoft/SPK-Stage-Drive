package com.stagedriving.modules.user.rest.l1;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountBillingDAO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountBilling;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.mapper.AccountBillingMapperImpl;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class BillingAccountResourse {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BillingAccountResourse.class);
    
    @Inject
    AccountDAO accountDAO;
    @Inject
    AccountBillingDAO billingDAO;
    @Inject
    AccountBillingMapperImpl billingMapper;

    private AppConfiguration configuration;

    @Inject
    public BillingAccountResourse(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve user billing methods",
            notes = "Retrieve user billing methods")
    public Response getAccountBillings(String accountId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.BILLINGS_GET_ERROR);

        Account account = accountDAO.findByUid(accountId);
        Preconditions.checkNotNull(account,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST));

        List<AccountBilling> billings = billingDAO.findByAccount(account);

        return Response.ok(billingMapper.billingsToBillingDtos(billings)).build();
    }
}
