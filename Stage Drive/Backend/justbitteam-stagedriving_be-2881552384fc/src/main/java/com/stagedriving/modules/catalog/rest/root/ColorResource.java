/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.catalog.rest.root;

import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.ColorDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.ColorDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.ColorMapper;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/colors")
@Api(value = "colors", description = "Colors resource")
public class ColorResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ColorResource.class);

    @Inject
    ColorMapper colorMapper;
    @Inject
    ColorDAO colorDAO;

    private AppConfiguration configuration;

    @Inject
    public ColorResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @ApiOperation(value = "Add new color",
            notes = "Add new color",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createColor(@Restricted(required = true) Account me,
                                @ApiParam(required = true) ColorDTO colorDto) {

        Preconds.checkState(me.getRole().equalsIgnoreCase(StgdrvData.AccountRoles.ADMIN),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.ACCOUNT_NOT_A_ADMIN));

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{colorId}")
    @UnitOfWork
    @ApiOperation(value = "Modify existing color",
            notes = "Modify existing color",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyColor(@Restricted(required = true) Account me,
                                @PathParam("colorId") String colorId) {

        Preconds.checkState(me.getRole().equalsIgnoreCase(StgdrvData.AccountRoles.ADMIN),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.ACCOUNT_NOT_A_ADMIN));

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(colorId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve colors",
            notes = "Retrieves colors",
            response = ColorDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getColors(@QueryParam("limit") String limit,
                              @QueryParam("page") String page) {

        return Response.ok(colorMapper.colorsToColorDtos(colorDAO.findAll())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{colorId}")
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve color",
            notes = "Retrieve color",
            response = ColorDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getColor(@PathParam("colorId") String colorId) {

        return Response.ok(colorMapper.colorToColorDto(colorDAO.findByUid(colorId))).build();
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{colorId}")
    @UnitOfWork
    @ApiOperation(value = "Delete existing color",
            notes = "Delete existing color",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteColor(@Restricted(required = true) Account me,
                                @PathParam("colorId") String colorId) {

        Preconds.checkState(me.getRole().equalsIgnoreCase(StgdrvData.AccountRoles.ADMIN),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.ACCOUNT_NOT_A_ADMIN));

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(colorId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }
}
