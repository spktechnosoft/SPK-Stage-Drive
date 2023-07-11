/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.CheckinDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.CheckinDAO;
import com.stagedriving.modules.commons.ds.entities.Checkin;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.mapper.CheckinMapperImpl;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/checkins")
@Api(value = "checkins", description = "Checkins resource")
public class CheckinResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CheckinResource.class);

    @Inject
    Preconditions preconditions;
    @Inject
    CheckinMapperImpl checkinMapper;
    @Inject
    CheckinDAO checkinDAO;

    private AppConfiguration configuration;

    @Inject
    public CheckinResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new checkin",
            notes = "Add new checkin",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createCheckin(@ApiParam(required = true) CheckinDTO checkinDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BRAND_CREATE);

        preconditions.checkNotNull(checkinDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CHECKIN_DTO_NULL));

        preconditions.checkNotNull(checkinDto.getEvents(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));

        Checkin checkin = checkinMapper.checkinDtoToCheckin(checkinDto);
        checkin.setUid(TokenUtils.generateUid());
        checkin.setCreated(new Date());
        checkin.setModified(new Date());
        checkin.setVisible(true);

        checkinDAO.create(checkin);

        responseDto.setId(checkin.getUid());

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{checkinId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing checkin",
            notes = "Modify existing checkin",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyCheckin(@PathParam("checkinId") String checkinId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(checkinId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve checkin",
            notes = "Retrieves checkin",
            response = CheckinDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getCheckins(@QueryParam("limit") String limit,
                                @QueryParam("page") String page) {

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{checkinId}")
    //@Metered
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve checkin",
            notes = "Retrieve checkin",
            response = CheckinDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getApp(@PathParam("checkinId") String checkinId) {

        return Response.ok().build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{checkinId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Delete existing checkin",
            notes = "Delete existing checkin",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteCheckin(@PathParam("checkinId") String checkinId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(checkinId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }
}
