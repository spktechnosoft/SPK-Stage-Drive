/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.rest.root;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.LikeDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasActionDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasAction;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.decorators.ActionMapperDecorator;
import com.stagedriving.modules.event.controllers.EventController;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import io.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/likes")
@Api(value = "likes", description = "Likes resource")
public class ActionLikeResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ActionLikeResource.class);

    private String pathEventId;
    private String pathAccountId;

    @Inject
    private ActionMapperDecorator actionMapper;
    @Inject
    private EventHasActionDAO eventHasActionDAO;
    @Inject
    private EventController eventController;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private AccountController accountController;

    private AppConfiguration configuration;

    public String getPathEventId() {
        return pathEventId;
    }

    public String getPathAccountId() {
        return pathAccountId;
    }

    public void setPathAccountId(String pathAccountId) {
        this.pathAccountId = pathAccountId;
    }

    public void setPathEventId(String pathEventId) {
        this.pathEventId = pathEventId;
    }

    @Inject
    public ActionLikeResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve likes",
            notes = "Retrieves likes",
            response = LikeDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getLikes(@Restricted(required = false) Account me,
                            @ApiParam(name = "limit", value = "Max number of results", required = false) @QueryParam("limit") @DefaultValue("100") IntParam limit,
                            @ApiParam(name = "page", value = "Page index", required = false) @QueryParam("page") @DefaultValue("0") IntParam page) {


        Event event = null;
        if (pathEventId != null) {
            event = eventDAO.findByUid(pathEventId);
        }
        Integer accountId = null;
        if (pathAccountId != null) {
            Account account = accountDAO.findByUid(pathAccountId);
            if (account != null) {
                accountId = account.getId();
            }
        }

        boolean isAdmin = accountController.isAdmin(me);

        List<EventHasAction> eventHasActions = eventHasActionDAO.findByFilters(isAdmin, page.get(), limit.get(), event, null, null, "like", accountId);

        return Response.ok(actionMapper.eventHasActionsToLikeDtos(eventHasActions, me)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new like action",
            notes = "Add new like action.",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createLike(@Restricted(required = true) Account account,
                                @ApiParam(required = true) @Valid LikeDTO likeDTO) throws IOException, InterruptedException {

        Preconditions.checkArgument(pathEventId != null, StgdrvMessage.MessageError.EVENT_ID_NULL);

        Event event = eventDAO.findByUid(pathEventId);
        Preconditions.checkArgument(event != null, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);

        // Check if like action already exists for user on this event
        List<EventHasAction> eventHasActions = eventHasActionDAO.findByEventAndAccount(event, account.getId(), "like");
        Preconditions.checkArgument(eventHasActions.size() == 0, "Like action already exists");

        EventHasAction eventHasAction = new EventHasAction();
        eventHasAction.setUid(TokenUtils.generateUid());
        eventHasAction.setCreated(new Date());
        eventHasAction.setEvent(event);
        eventHasAction.setTaxonomy("like");
        eventHasAction.setVisible(true);
        eventHasAction.setAccountid(account.getId());
        event.getEventHasActions().add(eventHasAction);

        eventHasActionDAO.edit(eventHasAction);

//        eventController.updateEventInterestAfterChanges(account.getId(), event.getUid());

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(eventHasAction.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{likeId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Get like action",
            notes = "Get like action",
            response = LikeDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getLike(@Restricted(required = true) Account me,
                               @PathParam("likeId") String likeId) {


        EventHasAction eventHasAction = eventHasActionDAO.findByUid(likeId);
        Preconditions.checkState(eventHasAction != null, StgdrvMessage.MessageError.ACTION_DOES_NOT_EXIST);

        LikeDTO likeDTO = actionMapper.eventHasActionToLikeDto(eventHasAction, me);

        return Response.ok(likeDTO).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{likeId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Delete like action",
            notes = "Delete like action",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteLike(@Restricted(required = true) Account me,
                                      @PathParam("likeId") String likeId) {

        Event event = eventDAO.findByUid(pathEventId);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACTION_DELETE);

        EventHasAction eventHasAction = eventHasActionDAO.findByUid(likeId);
        Preconditions.checkArgument(eventHasAction != null, StgdrvMessage.MessageError.ACTION_DOES_NOT_EXIST);
        eventHasActionDAO.delete(eventHasAction);

//        eventController.updateEventInterestAfterChanges(me.getId(), event.getUid());

        return Response.ok(responseDto).build();
    }

}
