/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.admin;

import com.google.inject.Inject;
import com.justbit.jedis.AbstractJedisDAO;
import com.justbit.sque.bundle.SqueConfiguration;
import com.justbit.sque.ds.daos.*;
import com.justbit.sque.ds.entities.SqueStatsEntity;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author simone
 */
@Path("/v1/admin/queues")
@Api(value = "Admin queues")
public class SqueQueueResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SqueQueueResource.class);

    private SqueConfiguration configuration;

    @Inject
    private SqueQueueDAO squeQueueDAO;
    @Inject
    private SqueJobDAO squeJobDAO;
    @Inject
    private SqueWorkerDAO squeWorkerDAO;
    @Inject
    private SqueStatsDAO squeStatsDAO;
    @Inject
    private SqueNodeDAO squeNodeDAO;
    @Inject
    private AccountController accountController;

    @Inject
    public SqueQueueResource(AppConfiguration scxConfiguration) {
        this.configuration = scxConfiguration.getSque();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Retrieve queues",
            response = AbstractJedisDAO.Results.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getQueues(@Restricted(role = StgdrvData.AccountRoles.ADMIN) Account account) throws Exception {

        AbstractJedisDAO.Results queues = squeQueueDAO.findAllStandardQueues();

        StgdrvResponseDTO responseDTO = new StgdrvResponseDTO();

        return Response.ok(queues).build();
    }

    @GET
    @Path("{queueId}/stats")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Retrieve queue stats",
            response = SqueStatsEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getQueuesStats(
            @PathParam("queueId") String queue,
            @QueryParam("type") String type,
            @QueryParam("node") String node,
            @QueryParam("page") Integer page,
            @QueryParam("limit") Integer limit,
            @Restricted(role = StgdrvData.AccountRoles.ADMIN) Account account) throws Exception {

        SqueStatsEntity statsEntity = squeStatsDAO.getQueueStats(queue);

        return Response.ok(statsEntity).build();
    }

}
