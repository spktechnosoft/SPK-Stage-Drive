/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.export;

import com.codahale.metrics.annotation.Metered;
import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.ExportDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/exports")
@Api(value = "exports", description = "Export resource")
public class ExportResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExportResource.class);

    @Inject
    private ExportController exportController;

    private AppConfiguration configuration;

    @Inject
    public ExportResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add export request",
            notes = "Add export request",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    public Response createEvent(@Restricted(required = true) Account account,
                                @ApiParam(required = true) @Valid ExportDTO exportDTO) throws Exception {


        exportController.requestExport(exportDTO);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId("1");
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.EVENT_CREATE);

        return Response.ok(responseDto).build();
    }

}
