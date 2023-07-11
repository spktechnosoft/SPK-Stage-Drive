/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.user.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountGroupDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountGroupDAO;
import com.stagedriving.modules.commons.ds.entities.AccountGroup;
import com.stagedriving.modules.commons.mapper.AccountGroupMapperImpl;
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
@Path("/v1/groups")
@Api(value = "groups", description = "Group resource")
public class GroupResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GroupResource.class);

    @Inject
    AccountGroupDAO groupDAO;
    @Inject
    AccountGroupMapperImpl groupMapper;

    private AppConfiguration configuration;

    @Inject
    public GroupResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new group",
            notes = "Add new group",
            response = StgdrvResponseDTO.class)
    public Response createGroup(@ApiParam(required = true) AccountGroupDTO groupDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationError.GROUP_CREATE_ERROR);

        AccountGroup group = groupMapper.groupDtoToGroup(groupDto);
        group.setUid(TokenUtils.generateUid());
        group.setCreated(new Date());
        group.setModified(new Date());

        groupDAO.create(group);

        responseDto.setId(group.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.GROUP_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{groupId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing group",
            notes = "Modify existing group",
            response = StgdrvResponseDTO.class)
    public Response modifyGroup(@PathParam("groupId") String groupId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(groupId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.GROUP_MODIFIED);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve groups",
            notes = "Retrieves groups",
            response = AccountGroupDTO.class,
            responseContainer = "List")
    public Response getGroups(@QueryParam("limit") String limit,
                              @QueryParam("page") String page) {

        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{groupId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve group",
            notes = "Retrieve group",
            response = AccountGroupDTO.class)
    public Response getApp(@PathParam("groupId") String groupId) {

        return Response.ok(groupMapper.groupToGroupDto(groupDAO.findByUid(groupId))).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{groupId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing group",
            notes = "Delete existing group",
            response = StgdrvResponseDTO.class)
    public Response deleteApp(@PathParam("groupId") String groupId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(groupId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.GROUP_DELETE);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{groupId}/accounts")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve group accounts",
            notes = "Retrieve group accounts",
            response = AccountGroupDTO.class,
            responseContainer = "List")
    public Response getGroupAccounts(@QueryParam("limit") String limit,
                                     @QueryParam("page") String page) {

        return Response.ok().build();
    }
}
