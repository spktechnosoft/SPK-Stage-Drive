package com.stagedriving.modules.user.rest.l1;

import com.codahale.metrics.annotation.Metered;
import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountConnectionDAO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountConnection;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.mapper.AccountConnectionMapperImpl;
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
public class ConnectionAccountResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConnectionAccountResource.class);

    @Inject
    AccountConnectionMapperImpl connectionMapper;
    @Inject
    AccountConnectionDAO connectionDAO;
    @Inject
    AccountDAO accountDAO;

    private AppConfiguration configuration;

    @Inject
    public ConnectionAccountResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve user connections",
            notes = "Retrieve user connections")
    public Response getAccountConnections(String accountId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.CONNECTIONS_GET_ERROR);

        Account account = accountDAO.findByUid(accountId);
        Preconds.checkConditions(account == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST);

        List<AccountConnection> connections = connectionDAO.findByAccount(account);

        return Response.ok(connectionMapper.connectionsToConnectionDtos(connections)).build();
    }
}
