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
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventImage;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.*;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import com.stagedriving.modules.commons.mapper.decorators.EventMapperDecorator;
import com.stagedriving.modules.commons.utils.FieldValidator;
import com.stagedriving.modules.commons.utils.event.EventUtils;
import com.stagedriving.modules.commons.utils.media.ImageUtils;
import com.stagedriving.modules.event.rest.l1.CatalogEventResource;
import com.stagedriving.modules.event.rest.l1.CheckinEventResource;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.DateTimeParam;
import io.swagger.annotations.*;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/events")
@Api(value = "events", description = "Event resource")
public class EventResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EventResource.class);

    @Inject
    EventUtils eventUtils;
    @Inject
    EventImageDAO eventImageDAO;
    @Inject
    AccountController accountController;
    @Inject
    EventDAO eventDAO;
    @Inject
    AccountDAO accountDAO;
    @Inject
    CatalogDAO catalogDAO;
    @Inject
    ItemDAO itemDAO;
    @Inject
    CatalogHasItemDAO catalogHasItemDAO;
    @Inject
    EventHasBuyerDAO eventHasBuyerDAO;
    @Inject
    EventHasActionDAO eventHasActionDAO;
    @Inject
    CheckinDAO checkinDAO;
    @Inject
    BrandDAO brandDAO;
    @Inject
    ItemCategoryDAO itemCategoryDAO;
    @Inject
    ItemMapperImpl itemMapper;
    @Inject
    BrandMapperImpl brandMapper;
    @Inject
    ColorMapperImpl colorMapper;
    @Inject
    CatalogMapperImpl catalogMapper;
    @Inject
    AccountMapperDecorator accountMapper;
    @Inject
    EventMapperDecorator eventMapper;
    @Inject
    ActionMapperImpl actionMapper;
    @Inject
    AccountImageMapperImpl imageMapper;
    @Inject
    EventHasCheckinDAO eventHasCheckinDAO;
    @Inject
    CheckinEventResource checkinEventResource;
    @Inject
    CatalogEventResource catalogEventResource;
    @Inject
    BookingResource bookingResource;
    @Inject
    InterestResource interestResource;
    @Inject
    ActionLikeResource actionLikeResource;
    @Inject
    ActionCommentResource actionCommentResource;
    @Inject
    ActionResource actionResource;
    @Inject
    ImageUtils imageUtils;

    private AppConfiguration configuration;

    @Inject
    public EventResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new event",
            notes = "Add new event. Mandatory fields are: (1)name (event's name), (2) business (event's business name), (3) piva or taxcode (at least one must be not null)",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createEvent(@Restricted(required = true) Account account,
                                @ApiParam(required = true) @Valid EventDTO eventDto) throws IOException, InterruptedException {

        Preconditions.checkNotNull(eventDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));

        Preconditions.checkNotNull(eventDto.getName(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_NAME_NULL));

        Preconditions.checkArgument(eventDto.getCoordinate().getLatitude() != null, StgdrvMessage.MessageError.EVENT_LATITUDE_NULL);
        Preconditions.checkArgument(eventDto.getCoordinate().getLongitude() != null, StgdrvMessage.MessageError.EVENT_LONGITUDE_NULL);
        Preconditions.checkArgument(eventDto.getCountry() != null, StgdrvMessage.MessageError.EVENT_COUNTRY_NULL);

        Preconditions.checkArgument(FieldValidator.isStringFieldValid(eventDto.getStatus(), StgdrvData.EventStatus.class), StgdrvMessage.MessageError.EVENT_INVALID_STATUS);

        Event event = null;
        if (eventDto.getId() != null) {
            event = eventDAO.findByUid(eventDto.getId());
            Event newEvent = eventMapper.eventDtoToEvent(eventDto);
            event = eventUtils.merge(event, newEvent);
        } else {
            event = eventMapper.eventDtoToEvent(eventDto);
            event.setUid(TokenUtils.generateUid());

            if (account != null) {
                event.setAccountid(account.getId());
            } else {
                event.setAccountid(0);
            }

            event.setCreated(new Date());
            event.setVisible(true);
//            event.setStatus(StgdrvData.EventStatus.DRAFT);
        }

        event.setModified(new Date());

        eventDAO.create(event);

        if (eventDto.getImages() != null && eventDto.getImages().size() > 0) {

            if (!event.getEventImages().isEmpty()) {
                List<EventImage> imagesToDelete = new ArrayList<>();
                for (EventImage image : event.getEventImages()) {
                    imagesToDelete.add(image);
                }
                for (EventImage image : imagesToDelete) {
                    event.getEventImages().remove(image);
                    eventImageDAO.delete(image);
                    //image.setImage(null);
                }
            }
            for (ImageDTO eventImageDTO : eventDto.getImages()) {
                Preconditions.checkNotNull(eventImageDTO.getUri(),
                        new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_IMAGE_NORMAL_URI_NULL));

                EventImage eventImage = new EventImage();

                eventImage.setUid(TokenUtils.generateUid());
                eventImage.setCreated(new Date());
                eventImage.setTaxonomy(StgdrvData.EventImageCategory.COVER);
                eventImage.setEvent(event);
                eventImage.setModified(new Date());

                String img = eventImageDTO.getUri();
                String uri = imageUtils.saveImageUri(img, eventImage.getUid(), "png", configuration.getStorage().getEventBucket());

                eventImage.setImage(uri);

                eventImageDAO.create(eventImage);
                event.getEventImages().add(eventImage);
                eventDAO.edit(event);
            }
        }

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(event.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.EVENT_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{eventId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing event",
            notes = "Modify existing event",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyEvent(@Restricted(required = true) Account me,
                                @ApiParam(required = true) EventDTO eventDto,
                                @PathParam("eventId") String eventId) throws IOException, InterruptedException {

//        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
//        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
//        responseDto.setMessage(StgdrvMessage.OperationSuccess.EVENT_MODIFIED);
//
//        Preconditions.checkNotNull(eventDto,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));
//
//        Event eventOld = eventDAO.findByUid(eventId);
//        Event eventNew = eventMapper.eventDtoToEvent(eventDto);
//
//        eventUtils.merge(eventOld, eventNew);
//
//        eventOld.setModified(new Date());
//        eventDAO.edit(eventOld);
//
//        responseDto.setId(eventOld.getUid());
//
//        return Response.ok(responseDto).build();
        //eventDto.setId(eventId);
        return createEvent(me, eventDto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve event",
            notes = "Retrieves event",
            response = EventDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getEvents(@Restricted(required = false) Account me,
                              @ApiParam(name = "limit", value = "Max number of results", required = false) @DefaultValue("120") @QueryParam("limit") String limit,
                              @ApiParam(name = "page", value = "Page index", required = false) @DefaultValue("0") @QueryParam("page") String page,
                              @ApiParam(value = "Order") @QueryParam("order") String order,
                              @ApiParam(value = "Sort") @QueryParam("sort") String sort,
                              @ApiParam(value = "Id like") @QueryParam("id_like") String idLike,
                              @ApiParam(value = "Name like") @QueryParam("name_like") String nameLike,
                              @ApiParam(value = "Category like") @QueryParam("category_like") String categoryLike,
                              @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size,
                              @ApiParam(name = "sdate", value = "Start date filter", required = false, format = "date-time") @QueryParam("sdate") DateTimeParam startDateParam,
                              @ApiParam(name = "fdate", value = "Finish date filter", required = false, format = "date-time") @QueryParam("fdate") DateTimeParam finishDateParam,
                              @ApiParam(name = "categories", value = "Categories filter", required = false) @QueryParam("categories") String categoriesParam,
                              @ApiParam(name = "filter", value = "Filter", required = false, allowableValues = "trending, bigger") @QueryParam("filter") String filterParam,
                              @ApiParam(name = "search", value = "Search as string", required = false) @QueryParam("search") String searchParam,
                              @ApiParam(name = "distance", value = "Distance in km to get proximity events", required = false) @DefaultValue("50") @QueryParam("distance") Double distanceParam,
                              //@ApiParam(name = "distanceType", value = "Return distance type in km=kilometers", allowableValues = "km", required = false) @QueryParam("distanceType") String distanceTypeParam,
                              @ApiParam(name = "coordinates", value = "Coordinates specified in string as {lat,lon} without graph symbols", required = false) @QueryParam("coordinates") String coordinatesParam) {

        Double latitude = null;
        Double longitude = null;

        PagedResults<EventDTO> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        List<Event> events = new ArrayList<>();
        ArrayList<EventDTO> eventDtos = new ArrayList<>();

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = Integer.parseInt(configuration.getDefaultLimit());
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        if (coordinatesParam != null && coordinatesParam.length() > 0) {
            String[] coordinatesSplitted = coordinatesParam.split(",");
            if (coordinatesSplitted.length != 2) {
                throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.WRONG_LAT_LNG_FORMAT_QUERY_PARAM);
            }
            try {
                latitude = Double.parseDouble(coordinatesSplitted[0]);
                longitude = Double.parseDouble(coordinatesSplitted[1]);
            } catch (NumberFormatException nfe) {
                throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.WRONG_COORDINATES_FORMAT);
            }
        }

        boolean isAdmin = accountController.isAdmin(me);

        Integer accountId = null;
        if (me != null) {
            accountId = me.getId();
        }

        events = eventDAO.findByFilters(isAdmin, pageQuery, limitQuery, searchParam, startDateParam != null ? startDateParam.get().toDate() : null, finishDateParam != null ? finishDateParam.get().toDate() : null, latitude, longitude, distanceParam, categoriesParam, -1, filterParam, accountId, sort, order, idLike, nameLike, categoryLike, results);
        List<EventDTO> eventDTOS = eventMapper.eventsToEventDtos(events, me, latitude, longitude);

        if (results != null) {
            results.setData(eventDTOS);
            return Response.ok(results).build();
        }

        return Response.ok(eventDTOS).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{eventId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve event",
            notes = "Retrieve event",
            response = EventDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getEvent(@Restricted(required = false) Account me,
                             @PathParam("eventId") String eventId) {

        boolean isAdmin = accountController.isAdmin(me);

        Event event = eventDAO.findByUid(eventId);
        Preconditions.checkNotNull(event,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));

        Preconditions.checkArgument(!event.getStatus().equals(StgdrvData.EventStatus.DELETED), StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);

        if (isAdmin || (me != null && event.getAccountid() == me.getId())) {

        } else {
            Preconditions.checkArgument(event.getStatus().equals(StgdrvData.EventStatus.PUBLISHED), StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);
        }
        EventDTO eventDto = eventMapper.eventToEventDto(event, me);
        Preconditions.checkNotNull(event,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));//Check if user is buyer

        return Response.ok(eventDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{eventId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing event",
            notes = "Delete existing event",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteEvent(@Restricted(required = true) Account me,
                                @PathParam("eventId") String eventId) {

//        Preconditions.checkState(me.getRole().equalsIgnoreCase(StgdrvData.AccountRoles.ADMIN),
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.ACCOUNT_NOT_A_ORGANIZER));
        // TODO Check for admin

        Event event = eventDAO.findByUid(eventId);
        Preconditions.checkNotNull(event,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

//        event.getEventHasCheckins().stream().forEach((eventHasCheckin) -> {
//            Checkin checkin = eventHasCheckin.getCheckin();
//            if (checkin != null) {
//                checkin.setVisible(false);
//                checkinDAO.edit(checkin);
//
//                eventHasCheckin.setVisible(false);
//                eventHasCheckinDAO.edit(eventHasCheckin);
//            }
//        });

        Preconds.checkConditions(event.getEventHasInterests().size() > 0, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.EVENT_NOT_CANCELLABLE);

        event.setStatus(StgdrvData.EventStatus.DELETED);
        event.setVisible(false);
        eventDAO.edit(event);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(eventId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.EVENT_DELETE);

        return Response.ok(responseDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{eventId}/checkins")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new checkin to the event",
            notes = "Add new checkin to the event",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response addCheckinToEvent(@Restricted(required = true) Account me,
                                      @PathParam("eventId") String eventId,
                                      @ApiParam(required = true) CheckinDTO checkinDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CHECKIN_CREATE);

        Preconditions.checkNotNull(checkinDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CHECKIN_DTO_NULL));

        Preconditions.checkNotNull(eventId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));

        Event event = eventDAO.findByUid(eventId);

        Preconditions.checkNotNull(event,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));

        return checkinEventResource.createCheckin(eventId, checkinDto);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{eventId}/catalogs")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new catalog to the event",
            notes = "Add new catalog to the event",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response addCatalogToEvent(@Restricted(required = true) Account me,
                                      @PathParam("eventId") String eventId,
                                      @ApiParam(required = true) CatalogDTO catalogDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CATALOG_CREATE);

        Preconditions.checkNotNull(catalogDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DTO_NULL));

        Preconditions.checkNotNull(eventId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));

        Event event = eventDAO.findByUid(eventId);

        Preconditions.checkNotNull(event,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));

        return catalogEventResource.createCatalog(eventId, catalogDto);
    }

    @Path("/{eventId}/bookings")
    public BookingResource bookingResource(@ApiParam(required = true) @PathParam("eventId") String eventUid) {
        bookingResource.setPathEventId(eventUid);
        return bookingResource;
    }

    @Path("/{eventId}/interests")
    public InterestResource interestResource(@ApiParam(required = true) @PathParam("eventId") String eventUid) {
        interestResource.setPathEventId(eventUid);
        return interestResource;
    }

    @Path("/{eventId}/likes")
    public ActionLikeResource actionLikeResource(@ApiParam(required = true) @PathParam("eventId") String eventUid) {
        actionLikeResource.setPathEventId(eventUid);
        return actionLikeResource;
    }

    @Path("/{eventId}/comments")
    public ActionCommentResource actionCommentResource(@ApiParam(required = true) @PathParam("eventId") String eventUid) {
        actionCommentResource.setPathEventId(eventUid);
        return actionCommentResource;
    }

    @Path("/{eventId}/actions")
    public ActionResource actionResource(@ApiParam(required = true) @PathParam("eventId") String eventUid) {
        actionResource.setPathEventId(eventUid);
        return actionResource;
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/{eventId}/buyers")
//    @UnitOfWork
//    @ApiOperation(value = "Add new buyer to the event",
//            notes = "Add new buyer to the event. AccountDto (me) cannot be null",
//            response = StgdrvResponseDTO.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
//    })
//    public Response addBuyerToEvent(@Restricted(required = true) Account me,
//                                    @PathParam("eventId") String eventId,
//                                    @ApiParam(required = true) AccountDTO accountD) {
//
//        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
//        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
//        responseDto.setMessage(StgdrvMessage.OperationSuccess.BUYER_CREATE);
//
//        Preconditions.checkNotNull(eventId,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));
//
//        Event event = eventDAO.findByUid(eventId);
//
//        Preconditions.checkNotNull(event,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));
//
//        EventHasBuyer eventHasBuyer = eventHasBuyerDAO.findByEventByAccountId(event, me.getId());
//        Preconditions.checkState(eventHasBuyer == null,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BUYER_ALREADY_EXIST));
//
//        eventHasBuyer = new EventHasBuyer();
//        eventHasBuyer.setUid(TokenUtils.generateUid());
//        eventHasBuyer.setCreated(new Date());
//        eventHasBuyer.setModified(new Date());
//        eventHasBuyer.setVisible(true);
//        eventHasBuyer.setEvent(event);
//        eventHasBuyer.setAccountid(me.getId());
//        eventHasBuyerDAO.create(eventHasBuyer);
//
//        event.getEventHasBuyers().add(eventHasBuyer);
//        eventDAO.edit(event);
//
//        return Response.ok(responseDto).build();
//    }

//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/{eventId}/buyers")
//    @UnitOfWork
//    @ApiOperation(value = "Delete buyer from the event",
//            notes = "Delete buyer from the event",
//            response = StgdrvResponseDTO.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
//    })
//    public Response deleteBuyerFromEvent(@Restricted(required = true) Account me,
//                                         @PathParam("eventId") String eventId) {
//
//        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
//        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
//        responseDto.setMessage(StgdrvMessage.OperationSuccess.BUYER_DELETE);
//
//        Preconditions.checkNotNull(eventId,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));
//
//        Event event = eventDAO.findByUid(eventId);
//
//        Preconditions.checkNotNull(event,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));
//
//        EventHasBuyer eventHasBuyer = eventHasBuyerDAO.findByEventByAccountId(event, me.getId());
//        Preconditions.checkNotNull(eventHasBuyer,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BUYER_DOES_NOT_EXIST));
//        eventHasBuyerDAO.delete(eventHasBuyer);
//
//        eventDAO.edit(event);
//
//        return Response.ok(responseDto).build();
//    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{eventId}/buyers")
//    @UnitOfWork(readOnly = true)
//    @ApiOperation(value = "Retrieve event buyers",
//            notes = "Retrieve event buyers. Response contains an arraylist of accountDto which are event followers (buyers)",
//            response = AccountDTO.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
//    })
//    public Response getBuyersOfEvent(@Restricted(required = true) Account me,
//                                     @PathParam("eventId") String eventId) {
//
//        Event event = eventDAO.findByUid(eventId);
//
//        Preconditions.checkNotNull(event,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));
//
//        List<EventHasBuyer> eventHasBuyers = eventHasBuyerDAO.findByEvent(event);
//
//        List<AccountDTO> accountDTOs = new ArrayList<>();
//
//        if (eventHasBuyers != null && eventHasBuyers.size() > 0) {
//            eventHasBuyers.stream().forEach((eventHasBuyer) -> {
//                if (eventHasBuyer.getVisible()) {
//                    int accountid = eventHasBuyer.getAccountid();
//                    Account account = accountDAO.findById(accountid);
//                    if (account != null) {
//                        accountDTOs.add(accountMapper.accountToAccountDto(account));
//                    }
//                }
//            });
//        }
//
//        return Response.ok(accountDTOs).build();
//    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/{eventId}/actions")
//    @UnitOfWork
//    @ApiOperation(value = "Add new action to the event",
//            notes = "Add new action to the event. AccountDto (me) cannot be null",
//            response = StgdrvResponseDTO.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
//    })
//    public Response addActionToEvent(@Restricted(required = true) Account me,
//                                     @PathParam("eventId") String eventId,
//                                     @ApiParam(required = true) ActionDTO actionDTO) {
//
//        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
//        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
//        responseDto.setMessage(StgdrvMessage.OperationSuccess.BUYER_CREATE);
//
//        Preconditions.checkNotNull(eventId,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));
//
//        Preconditions.checkNotNull(actionDTO,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_DTO_NULL));
//
//        Preconditions.checkNotNull(actionDTO.getNormalUri(),
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_URI_NULL));
//
//        Event event = eventDAO.findByUid(eventId);
//
//        Preconditions.checkNotNull(event,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));
//
//        EventHasAction eventHasAction = new EventHasAction();
//        eventHasAction.setUid(TokenUtils.generateUid());
//        eventHasAction.setCreated(new Date());
//        eventHasAction.setModified(new Date());
//        eventHasAction.setVisible(true);
//        eventHasAction.setEvent(event);
//        eventHasAction.setAccountid(me.getId());
//        eventHasAction.setImage(actionDTO.getNormalUri());
//        eventHasAction.setStatus(StgdrvData.BrandStatus.APPROVED);
//        eventHasActionDAO.create(eventHasAction);
//
//        event.getEventHasActions().add(eventHasAction);
//        eventDAO.edit(event);
//
//        List<EventHasCheckin> eventHasCheckins = eventHasCheckinDAO.findUserCheckinsToEvent(event, me.getId());
//        if (!eventHasCheckins.isEmpty()) {
//            Collections.sort(eventHasCheckins, new Comparator<EventHasCheckin>() {
//                public int compare(EventHasCheckin result1, EventHasCheckin result2) {
//                    if (result1.getCheckin().getCreated() != null && result2.getCheckin().getCreated() != null) {
//                        if (result1.getCheckin().getCreated().after(result2.getCheckin().getCreated())) {
//                            return 1;
//                        } else if (result1.getCheckin().getCreated().before(result2.getCheckin().getCreated())) {
//                            return -1;
//                        } else {
//                            return 0;
//                        }
//                    } else if (result1.getCheckin().getCreated() == null && result2.getCheckin().getCreated() != null) {
//                        return 1;
//                    } else if (result1.getCheckin().getCreated() != null && result2.getCheckin().getCreated() == null) {
//                        return -1;
//                    }
//                    return -1;
//                }
//            });
//
//            GregorianCalendar cal = new GregorianCalendar();
//            cal.setTime(new Date());
//            cal.add(Calendar.DATE, -1);
//
//            if (cal.getTime().after(eventHasCheckins.get(eventHasCheckins.size() - 1).getCreated())) {
//                Checkin checkin = new Checkin();
//                checkin.setUid(TokenUtils.generateUid());
//                checkin.setCreated(new Date());
//                checkin.setModified(new Date());
//                checkin.setVisible(true);
//                checkin.setAccountid(me.getId());
//                checkin.setVisible(true);
//                checkinDAO.create(checkin);
//
//                EventHasCheckinId eventHasCheckinId = new EventHasCheckinId();
//                eventHasCheckinId.setCheckinId(checkin.getId());
//                eventHasCheckinId.setEventId(event.getId());
//                EventHasCheckin eventHasCheckin = new EventHasCheckin();
//                eventHasCheckin.setId(eventHasCheckinId);
//                eventHasCheckin.setCreated(new Date());
//                eventHasCheckin.setModified(new Date());
//                eventHasCheckin.setEvent(event);
//                eventHasCheckin.setCheckin(checkin);
//                eventHasCheckin.setVisible(true);
//                eventHasCheckinDAO.create(eventHasCheckin);
//
//                event.setNcheckin(event.getNcheckin() + 1);
//                eventDAO.edit(event);
//            } else {
//                throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.TOO_MANY_CHECKINS);
//            }
//        }
//
//        responseDto.setId(eventHasAction.getUid());
//
//        return Response.ok(responseDto).build();
//    }
//
//    @PUT
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/{eventId}/actions/{actionId}")
//    @UnitOfWork
//    @ApiOperation(value = "Modify action event",
//            notes = "Modify action event. AccountDto (me) cannot be null",
//            response = StgdrvResponseDTO.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
//    })
//    public Response modifyActionEvent(@Restricted(required = true) Account me,
//                                      @PathParam("eventId") String eventId,
//                                      @PathParam("actionId") String actionId,
//                                      @ApiParam(required = true) ActionDTO actionDTO) {
//
//        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
//        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
//        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACTION_MODIFIED);
//
//        Preconditions.checkNotNull(eventId,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));
//
//        Preconditions.checkNotNull(actionDTO,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_DTO_NULL));
//
//        Preconditions.checkNotNull(actionDTO.getNormalUri(),
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_URI_NULL));
//
//        Event event = eventDAO.findByUid(eventId);
//
//        Preconditions.checkNotNull(event,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));
//
//        EventHasAction eventHasAction = eventHasActionDAO.findByUid(actionId);
//        Preconditions.checkState(eventHasAction != null,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_DTO_NULL));
//        eventHasAction.setModified(new Date());
//        eventHasAction.setImage(actionDTO.getNormalUri());
//        eventHasAction.setStatus(StgdrvData.BrandStatus.PENDING);
//        eventHasActionDAO.edit(eventHasAction);
//
//        return Response.ok(responseDto).build();
//    }
//
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{eventId}/actions")
//    @UnitOfWork
//    @ApiOperation(value = "Get event actions",
//            notes = "Get event actions",
//            response = StgdrvResponseDTO.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
//    })
//    @ApiResponses(value = {
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
//            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
//    })
//    public Response getActionsEvent(@Restricted(required = true) Account me,
//                                    @PathParam("eventId") String eventId) {
//
//        Preconditions.checkNotNull(eventId,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DTO_NULL));
//
//        Event event = eventDAO.findByUid(eventId);
//
//        Preconditions.checkNotNull(event,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST));
//
//        List<EventHasAction> eventHasActions = eventHasActionDAO.findByEvent(event);
//
//        List<ActionDTO> actionDtos = new ArrayList<>();
//        for (EventHasAction eventHasAction : eventHasActions) {
//            Account account = accountDAO.findById(eventHasAction.getAccountid());
//            AccountDTO accountDto = accountMapper.accountToAccountDto(account);
//            accountDto.setImages(imageMapper.accountImagesToAccountImageDtos(account.getAccountImages()));
//            ActionDTO actionDto = actionMapper.eventHasActionToAtionDto(eventHasAction);
//            actionDto.setAccount(accountDto);
//            actionDtos.add(actionDto);
//        }
//
//        return Response.ok(actionDtos).build();
//    }
}
