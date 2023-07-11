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
import com.stagedriving.commons.v1.resources.CommentDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasActionDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasAction;
import com.stagedriving.modules.commons.exception.Preconds;
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
@Path("/v1/comments")
@Api(value = "comments", description = "Comments resource")
public class ActionCommentResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ActionCommentResource.class);

    private String pathEventId;

    @Inject
    private ActionMapperDecorator actionMapper;
    @Inject
    private EventHasActionDAO eventHasActionDAO;
    @Inject
    private EventController eventController;
    @Inject
    private AccountController accountController;
    @Inject
    private EventDAO eventDAO;

    private AppConfiguration configuration;

    public String getPathEventId() {
        return pathEventId;
    }

    public void setPathEventId(String pathEventId) {
        this.pathEventId = pathEventId;
    }

    @Inject
    public ActionCommentResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve comments",
            notes = "Retrieves comments",
            response = CommentDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getComments(@Restricted(required = false) Account me,
                            @ApiParam(name = "limit", value = "Max number of results", required = true) @QueryParam("limit") IntParam limit,
                            @ApiParam(name = "page", value = "Page index", required = true) @QueryParam("page") IntParam page) {

        Preconditions.checkArgument(pathEventId != null, StgdrvMessage.MessageError.EVENT_ID_NULL);

        Event event = eventDAO.findByUid(pathEventId);

        boolean isAdmin = accountController.isAdmin(me);

        List<EventHasAction> eventHasActions = eventHasActionDAO.findByFilters(isAdmin, page.get(), limit.get(), event, null, null, "comment", null);

        return Response.ok(actionMapper.eventHasActionsToCommentDtos(eventHasActions)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Add new comment action",
            notes = "Add new comment action.",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createComment(@Restricted(required = true) Account account,
                                @ApiParam(required = true) @Valid CommentDTO commentDTO) throws IOException, InterruptedException {

        Preconditions.checkArgument(pathEventId != null, StgdrvMessage.MessageError.EVENT_ID_NULL);

        Event event = eventDAO.findByUid(pathEventId);

        // Check if comment action already exists for user on this event
        //List<EventHasAction> eventHasActions = eventHasActionDAO.findByEventAndAccount(event, account.getId(), "comment");
        //Preconditions.checkArgument(eventHasActions.size() == 0, "Comment action already exists");

        EventHasAction eventHasAction = null;
        if (commentDTO.getId() != null) {
            eventHasAction = eventHasActionDAO.findByUid(commentDTO.getId());
            Preconds.checkConditions(eventHasAction == null, StgdrvResponseDTO.Codes.INVALID_PARAMETERS, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST);
        } else {
            eventHasAction = new EventHasAction();
            eventHasAction.setUid(TokenUtils.generateUid());
            eventHasAction.setCreated(new Date());
            eventHasAction.setEvent(event);
            eventHasAction.setTaxonomy("comment");
            eventHasAction.setVisible(true);
            eventHasAction.setAccountid(account.getId());
            event.getEventHasActions().add(eventHasAction);
        }
        eventHasAction.setContent(commentDTO.getContent());

        eventHasActionDAO.edit(eventHasAction);

//        eventController.updateEventInterestAfterChanges(account.getId(), event.getUid());

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(eventHasAction.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{commentId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Edit comment action",
            notes = "Edit comment action.",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyComment(@Restricted(required = true) Account account,
                                  @PathParam("commentId") String commentId,
                                  @ApiParam(required = true) @Valid CommentDTO commentDTO) throws IOException, InterruptedException {
        commentDTO.setId(commentId);
        return createComment(account, commentDTO);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{commentId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Get comment action",
            notes = "Get comment action",
            response = CommentDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getComment(@Restricted(required = true) Account me,
                               @PathParam("commentId") String commentId) {


        EventHasAction eventHasAction = eventHasActionDAO.findByUid(commentId);
        Preconditions.checkState(eventHasAction != null, StgdrvMessage.MessageError.ACTION_DOES_NOT_EXIST);

        CommentDTO commentDTO = actionMapper.eventHasActionToCommentDto(eventHasAction);

        return Response.ok(commentDTO).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{commentId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Delete comment action",
            notes = "Delete comment action",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteComment(@Restricted(required = true) Account me,
                                      @PathParam("commentId") String commentId) {

        Event event = eventDAO.findByUid(pathEventId);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACTION_DELETE);

        EventHasAction eventHasAction = eventHasActionDAO.findByUid(commentId);
        Preconditions.checkState(eventHasAction != null, StgdrvMessage.MessageError.ACTION_DOES_NOT_EXIST);
        eventHasActionDAO.delete(eventHasAction);

//        eventController.updateEventInterestAfterChanges(me.getId(), event.getUid());

        return Response.ok(responseDto).build();
    }

}
