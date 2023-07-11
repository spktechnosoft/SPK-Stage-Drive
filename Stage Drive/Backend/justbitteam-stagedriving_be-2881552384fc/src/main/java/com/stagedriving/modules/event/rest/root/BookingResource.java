/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.rest.root;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.BookingDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.BookingDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasBookingDAO;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.decorators.BookingMapperDecorator;
import com.stagedriving.modules.event.controllers.EventController;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import io.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/bookings")
@Api(value = "bookings", description = "Booking resource")
public class BookingResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BookingResource.class);

    private String pathEventId;
    private String pathAccountId;

    @Inject
    private EventController eventController;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private AccountController accountController;
    @Inject
    private BookingDAO bookingDAO;
    @Inject
    private EventHasBookingDAO eventHasBookingDAO;
    @Inject
    private BookingMapperDecorator bookingMapper;
    @Inject
    private AccountDAO accountDAO;

    private AppConfiguration configuration;

    public String getPathEventId() {
        return pathEventId;
    }

    public void setPathEventId(String pathEventId) {
        this.pathEventId = pathEventId;
    }

    public String getPathAccountId() {
        return pathAccountId;
    }

    public void setPathAccountId(String pathAccountId) {
        this.pathAccountId = pathAccountId;
    }

    @Inject
    public BookingResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new booking",
            notes = "Add new booking",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createBooking(@Restricted Account account,
                                  @ApiParam(required = true) @Valid BookingDTO bookingDTO) {

        Preconditions.checkArgument(pathEventId != null, StgdrvMessage.MessageError.EVENT_ID_NULL);

        Event event = eventDAO.findByUid(pathEventId);
        Preconds.checkConditions(event.getStatus().equals(StgdrvData.EventStatus.DELETED), StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);

        // Check if booking already exists for user
        List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByEventAndAccount(event, account.getId());
        Preconditions.checkArgument(eventHasBookings.size() == 0, "Booking already exists");

        Booking booking = bookingMapper.bookingDtoToBooking(bookingDTO);
        booking.setUid(TokenUtils.generateUid());
        booking.setCreated(new Date());
        if (booking.getWithTicket() != null && booking.getWithTicket()) {
            // Populate event interest
        }
        booking.setVisible(true);
        bookingDAO.create(booking);

        EventHasBooking eventHasBooking = new EventHasBooking();
        eventHasBooking.setId(new EventHasBookingId(booking.getId(), event.getId()));
        eventHasBooking.setCreated(new Date());
        eventHasBooking.setModified(new Date());
        eventHasBooking.setBooking(booking);
        eventHasBooking.setAccountid(account.getId());
        eventHasBooking.setEvent(event);
        eventHasBookingDAO.edit(eventHasBooking);

        booking.getEventHasBookings().add(eventHasBooking);
        booking.setModified(new Date());
        bookingDAO.edit(booking);

//        eventController.updateEventInterestAfterChanges(account.getId(), event.getUid());

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(booking.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{bookingId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Modify existing booking",
            notes = "Modify existing booking",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyBooking(@Restricted Account account,
                                  @PathParam("bookingId") String bookingId,
                                  @ApiParam(required = true) @Valid BookingDTO bookingDTO) {

        Preconditions.checkArgument(pathEventId != null, StgdrvMessage.MessageError.EVENT_ID_NULL);

        Event event = eventDAO.findByUid(pathEventId);
        Preconds.checkConditions(event.getStatus().equals(StgdrvData.EventStatus.DELETED), StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);

        // Check if booking already exists for user
        List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByEventAndAccount(event, account.getId());
        Preconditions.checkArgument(eventHasBookings.size() == 0, "Booking already exists");

        Booking booking = bookingMapper.bookingDtoToBooking(bookingDTO);
        if (booking.getWithTicket() != null && booking.getWithTicket()) {
            // Populate event interest
        }
        booking.setModified(new Date());
        bookingDAO.edit(booking);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(booking.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve booking",
            notes = "Retrieves booking",
            response = BookingDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getBookings(@Restricted(required = false) Account account,
                                @QueryParam("eventId") String eventUid,
                                @QueryParam("accountId") String accountUid,
                                @QueryParam("limit") @ApiParam(required = true) @DefaultValue("100") IntParam limit,
                                @QueryParam("page") @ApiParam(required = true) @DefaultValue("0") IntParam page) {



        Event event = null;
        if (eventUid != null) {
            pathEventId = eventUid;
        }

        if (pathEventId != null) {
            event = eventDAO.findByUid(pathEventId);
        }

        if (pathAccountId != null) {
            accountUid = pathAccountId;
        }

        Integer accountId = null;
        if (accountUid != null && !accountUid.isEmpty()) {
            Account acc = accountDAO.findByUid(accountUid);
            Preconditions.checkArgument(acc != null, "Invalid account");
            accountId = acc.getId();
        }
        boolean isAdmin = accountController.isAdmin(account);

        List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByFilters(isAdmin, page.get(), limit.get(), event, accountId);
        List<BookingDTO> bookingDTOS = bookingMapper.eventHasBookingsToBookingDtos(eventHasBookings, account);

        return Response.ok(bookingDTOS).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{bookingId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve booking",
            notes = "Retrieve booking",
            response = BookingDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getBooking(@Restricted Account account,
                           @PathParam("bookingId") String bookingId) {

        Booking booking = bookingDAO.findByUid(bookingId);
        BookingDTO bookingDTO = bookingMapper.bookingToBookingDto(booking);

        return Response.ok(bookingDTO).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{bookingId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Delete existing booking",
            notes = "Delete existing booking",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteBooking(@Restricted Account account,
                                  @PathParam("bookingId") String bookingId) {

        Booking booking = bookingDAO.findByUid(bookingId);
        Preconditions.checkArgument(booking != null, "Invalid booking");

        EventHasBooking eventHasBooking = booking.getEventHasBookings().get(0);
        Event event = eventHasBooking.getEvent();
        Preconds.checkConditions(event.getStatus().equals(StgdrvData.EventStatus.DELETED), StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);
        eventHasBookingDAO.delete(eventHasBooking);
        bookingDAO.delete(booking);

        //eventController.updateEventInterestAfterChanges(account.getId(), event.getUid());

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(bookingId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }
}
