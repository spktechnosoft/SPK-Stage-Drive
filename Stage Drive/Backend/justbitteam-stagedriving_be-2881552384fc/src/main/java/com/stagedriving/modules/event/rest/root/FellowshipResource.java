/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.FellowshipDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.FellowshipDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Fellowship;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.FellowshipMapperImpl;
import com.stagedriving.modules.commons.utils.event.FellowshipUtils;
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
@Path("/v1/fellowships")
@Api(value = "fellowships", description = "Fellowships resource")
public class FellowshipResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FellowshipResource.class);

    @Inject
    Preconditions preconditions;
    @Inject
    FellowshipMapperImpl fellowshipMapper;
    @Inject
    FellowshipDAO fellowshipDAO;
    @Inject
    FellowshipUtils fellowshipUtils;

    private AppConfiguration configuration;

    @Inject
    public FellowshipResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new fellowship",
            notes = "Add new fellowship. Fellowship represents a stores group. " +
                    "Every event must belong to a fellowship. in the simplest case one event belongs to a one fellowship." +
                    "Mandatory fields are: (1) name",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createFellowship(@Restricted(required = true) Account me,
                                     @ApiParam(required = true) FellowshipDTO fellowshipDto) {

        preconditions.checkNotNull(fellowshipDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.FELLOWSHIP_DTO_NULL));

        preconditions.checkNotNull(fellowshipDto.getName(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.FELLOWSHIP_NAME_NULL));

        Fellowship fellowship = fellowshipMapper.fellowshipDtoToFellowship(fellowshipDto);
        fellowship.setUid(TokenUtils.generateUid());
        fellowship.setCreated(new Date());
        fellowship.setModified(new Date());
        fellowship.setStatus(StgdrvData.FellowshipStatus.PENDING);
        fellowship.setVisible(true);
        fellowship.setAccountid(me.getId());

        fellowshipDAO.create(fellowship);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(fellowship.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.FELLOWSHIP_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{fellowshipId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing fellowship",
            notes = "Modify existing fellowship",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyFellowship(@Restricted(required = true) Account me,
                                     @ApiParam(required = true) FellowshipDTO fellowshipDto,
                                     @PathParam("fellowshipId") String fellowshipId) {


        preconditions.checkNotNull(fellowshipDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.FELLOWSHIP_DTO_NULL));

        Fellowship fellowshipOld = fellowshipDAO.findByUid(fellowshipId);
        Fellowship fellowshipNew = fellowshipMapper.fellowshipDtoToFellowship(fellowshipDto);
        fellowshipUtils.merge(fellowshipOld, fellowshipNew);

        fellowshipDAO.edit(fellowshipOld);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(fellowshipOld.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.FELLOWSHIP_CREATE);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve fellowship",
            notes = "Retrieves fellowship",
            response = FellowshipDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getPipes(@QueryParam("limit") String limit,
                             @QueryParam("page") String page) {

        return Response.ok(fellowshipMapper.fellowshipsToFellowshipDtos(fellowshipDAO.findAll())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{fellowshipId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve fellowship",
            notes = "Retrieve fellowship",
            response = FellowshipDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getFellowship(@PathParam("fellowshipId") String fellowshipId) {

        return Response.ok(fellowshipMapper.fellowshipToFellowshipDto(fellowshipDAO.findByUid(fellowshipId))).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{fellowshipId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing fellowship",
            notes = "Delete existing fellowship",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteFellowship(@PathParam("fellowshipId") String fellowshipId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(fellowshipId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.FELLOWSHIP_DELETE);

        return Response.ok(responseDto).build();
    }
}
