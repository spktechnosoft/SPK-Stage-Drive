/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.admin;

import com.google.inject.Inject;
import com.justbit.sque.bundle.SqueConfiguration;
import com.justbit.sque.ds.daos.*;
import com.justbit.sque.ds.entities.SqueNodeEntity;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author simone
 */
@Path("/v1/admin/nodes")
@Api(value = "Admin nodes")
public class SqueNodeResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SqueNodeResource.class);

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
    public SqueNodeResource(AppConfiguration scxConfiguration) {
        this.configuration = scxConfiguration.getSque();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Retrieve nodes",
            response = SqueNodeEntity.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getNodes(@Restricted(role = StgdrvData.AccountRoles.ADMIN) Account account) throws Exception {

        List<SqueNodeEntity> queues = squeNodeDAO.getAllNodes();

        StgdrvResponseDTO responseDTO = new StgdrvResponseDTO();

        return Response.ok(queues).build();
    }

}
