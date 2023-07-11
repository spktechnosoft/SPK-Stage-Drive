/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.admin;

import com.google.inject.Inject;
import com.justbit.jedis.AbstractJedisDAO;
import com.justbit.sque.SqueController;
import com.justbit.sque.bundle.SqueConfiguration;
import com.justbit.sque.ds.daos.*;
import com.justbit.sque.ds.entities.SqueJob;
import com.justbit.sque.ds.entities.SqueLog;
import com.justbit.sque.ds.entities.SqueWorkerEntity;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author simone
 */
@Path("/v1/admin/jobs")
@Api(value = "Admin jobs")
public class SqueJobResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SqueJobResource.class);


    private SqueConfiguration configuration;

    @Inject
    private SqueQueueDAO squeQueueDAO;
    @Inject
    private SqueJobDAO squeJobDAO;
    @Inject
    private SqueController squeController;
    @Inject
    private SqueWorkerDAO squeWorkerDAO;
    @Inject
    private SqueStatsDAO squeStatsDAO;
    @Inject
    private SqueNodeDAO squeNodeDAO;
    @Inject
    private SqueLogDAO squeLogDAO;
    @Inject
    private AccountController accountController;

    @Inject
    public SqueJobResource(AppConfiguration scxConfiguration) {
        this.configuration = scxConfiguration.getSque();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Retrieve queue jobs",
            response = PagedResults.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getQueuesJobs(
            @QueryParam("queue") String queue,
            @QueryParam("type") String type,
            @QueryParam("node") String node,
            @QueryParam("page") Integer page,
            @QueryParam("limit") Integer limit,
            @Restricted(role = StgdrvData.AccountRoles.ADMIN) Account account) throws Exception {

        AbstractJedisDAO.Results queues = null;

        queues = squeJobDAO.getAllJobsOnQueueWithType(queue, node, type, page, limit);

        PagedResults<SqueJob> resultsDTO = new PagedResults<SqueJob>();
        resultsDTO.setSize(queues.size);
        resultsDTO.setData(queues.data);

        return Response.ok(resultsDTO).build();
    }

    @GET
    @Path("{jobId}/log")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Retrieve job log",
            response = SqueLog.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getJobLog(
            @PathParam("jobId") String jobId,
            @QueryParam("level") String level,
            @QueryParam("page") Integer page,
            @QueryParam("limit") Integer limit,
            @Restricted(role = StgdrvData.AccountRoles.ADMIN) Account account) throws Exception {

        List<SqueLog> logs = squeLogDAO.getAllLogsOfParentId(jobId, page, limit);

        return Response.ok(logs).build();
    }

    @DELETE
    @Path("{jobId}")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Delete job",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteJob(
            @PathParam("jobId") String jobId,
//            @QueryParam("queue") String queue,
//            @QueryParam("type") String type,
            @Restricted(role = StgdrvData.AccountRoles.ADMIN) Account account) throws Exception {

        SqueJob job = squeJobDAO.load(jobId, SqueJob.class);
        squeJobDAO.remove(job);

        StgdrvResponseDTO responseDTO = StgdrvResponseDTO.newInstance(StgdrvResponseDTO.Codes.IS_OK, "Job deleted");
        return Response.ok(responseDTO).build();
    }

    @GET
    @Path("/workers")
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Retrieve workers",
            response = SqueWorkerEntity.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getWorkers(@Restricted(role = StgdrvData.AccountRoles.ADMIN) Account account) throws Exception {

        List<SqueWorkerEntity> queues = squeWorkerDAO.getAllWorkers();

        StgdrvResponseDTO responseDTO = new StgdrvResponseDTO();

        return Response.ok(queues).build();
    }

}
