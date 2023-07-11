package com.stagedriving.modules.user.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.ConnectionDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountConnectionDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountConnection;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.AccountConnectionMapperImpl;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import com.stagedriving.modules.commons.utils.account.AccountUtils;
import com.stagedriving.modules.user.controller.ConnectionController;
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
@Path("/v1/connections")
@Api(value = "connections", description = "Connections resource")
public class ConnectionResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConnectionResource.class);

    @Inject
    AccountUtils accountUtils;
    @Inject
    AccountConnectionMapperImpl connectionMapper;
    @Inject
    AccountMapperDecorator accountMapper;
    @Inject
    AccountConnectionDAO connectionDAO;
    @Inject
    ConnectionController connectionController;

    private AppConfiguration configuration;

    @Inject
    public ConnectionResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "New connection",
            notes = "New connection",
            response = StgdrvResponseDTO.class)
    public Response createConnection(@Restricted(required = true) Account me,
                                     @ApiParam(required = true) ConnectionDTO connectionDto) throws Exception {

        Preconds.checkConditions(connectionDto.getProvider() == null,
                 StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_PROVIDER_NULL);

        Preconds.checkConditions(connectionDto.getToken() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_TOKEN_NULL);

        Preconds.checkConditions(connectionDto.getId() == null,
                 StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_ID_NULL);

        AccountConnection connection = connectionMapper.connectionDtoToConnection(connectionDto);
        connection.setAccount(me);
        connection.setId(connectionDto.getId());
        connection.setUid(TokenUtils.generateUid());
        connection.setCreated(new Date());
        connection.setModified(new Date());
        connection.setToken(TokenUtils.generateToken());
        connection.setExpires(new Date());

        connectionDAO.create(connection);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(connection.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CONNECTION_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{connectionId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing connection interface",
            notes = "Modify existing connection interface",
            response = StgdrvResponseDTO.class)
    public Response modifyAccount(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) ConnectionDTO connectionDto,
                                  @PathParam("connectionId") String connectionId) {

        Preconds.checkNotNull(connectionDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_DTO_NULL));

        Preconds.checkNotNull(connectionDto.getProvider(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_PROVIDER_NULL));

        Preconds.checkNotNull(connectionDto.getToken(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_TOKEN_NULL));

        Preconds.checkNotNull(connectionDto.getId(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_ID_NULL));

        AccountConnection connection = connectionDAO.findByUid(connectionId);
        Preconds.checkNotNull(connection,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_DOES_NOT_EXIST));

        AccountConnection connectionOld = connectionDAO.findByUid(connectionId);
        AccountConnection connectionNew = connectionMapper.connectionDtoToConnection(connectionDto);
        accountUtils.merge(connectionOld, connectionNew);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CONNECTION_MODIFIED);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieves connections",
            notes = "Retrieves connections",
            response = ConnectionDTO.class,
            responseContainer = "List")
    public Response getConnections(@ApiParam(value = "Response page size") @QueryParam("limit") String limit,
                                   @ApiParam(value = "Response page index") @QueryParam("page") String page) {

        return Response.ok(connectionMapper.connectionsToConnectionDtos(connectionDAO.findAll())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{connectionId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve connection",
            notes = "Retrieve connection",
            response = ConnectionDTO.class)
    public Response getConnection(@PathParam("connectionId") String connectionId,
                                  @ApiParam(value = "Connection access token") @QueryParam("token") String token,
                                  @ApiParam(value = "Connection provider") @QueryParam("provider") String provider) {

        if (connectionId != null && provider != null && token != null) {
            Preconds.checkConditions(!connectionController.checkConnectionTokenOnProvider(connectionId, provider, token),
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_PROVIDER_NULL);
        }

        AccountConnection connection = connectionDAO.findByConnectionIdByProvider(connectionId, provider);
        Preconds.checkConditions(connection == null,
                StgdrvResponseDTO.Codes.CONNECTION_DOES_NOT_EXIST, StgdrvMessage.MessageError.CONNECTION_DOES_NOT_EXIST);

        if (token != null && !token.equalsIgnoreCase(connection.getToken())) connection.setToken(token);

        ConnectionDTO connectionDto = connectionMapper.connectionToConnectionDto(connection);

        Account account = connection.getAccount();
        if (account != null) {
            AccountDTO accountDto = accountMapper.accountToAccountDto(connection.getAccount());
            accountDto.setToken(connection.getAccount().getToken());
            accountDto.setExpires(connection.getAccount().getExpires());
            connectionDto.setAccount(accountDto);
        }

        return Response.ok(connectionDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{connectionId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing connection",
            notes = "Delete existing connection",
            response = StgdrvResponseDTO.class)
    public Response deleteConnection(@Restricted(required = true) Account me,
                                     @ApiParam(required = true) @PathParam("connectionId") String connectionId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.CONNECTION_DELETE_ERROR);

        AccountConnection connection = connectionDAO.findByUid(connectionId);
        Preconds.checkConditions(connection == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CONNECTION_DOES_NOT_EXIST);

        connectionDAO.delete(connection);

        return Response.ok(responseDto).build();
    }
}
