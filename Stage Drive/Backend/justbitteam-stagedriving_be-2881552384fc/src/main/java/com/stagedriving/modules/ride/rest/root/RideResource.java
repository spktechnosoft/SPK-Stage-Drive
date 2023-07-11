/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.ride.rest.root;

import com.google.inject.Inject;
import com.justbit.commons.GeocoderUtils;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.CoordinateDTO;
import com.stagedriving.commons.v1.resources.RideDTO;
import com.stagedriving.commons.v1.resources.RidePassengerDTO;
import com.stagedriving.commons.v1.resources.RidePriceDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.EventMapperImpl;
import com.stagedriving.modules.commons.mapper.RideMapperImpl;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import com.stagedriving.modules.commons.mapper.decorators.RidePassengerMapperDecorator;
import com.stagedriving.modules.commons.utils.DateUtils;
import com.stagedriving.modules.event.controllers.EventController;
import com.stagedriving.modules.ride.controllers.RideController;
import com.stagedriving.modules.transaction.rest.root.TransactionResource;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/rides")
@Api(value = "rides", description = "Rides")
public class RideResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RideResource.class);

    @Inject
    AccountDAO accountDAO;
    @Inject
    RideMapperImpl rideMapper;
    @Inject
    AccountController accountController;
    @Inject
    RidePassengerMapperDecorator ridePassengerMapper;
    @Inject
    RideDAO rideDAO;
    @Inject
    AccountGroupDAO groupDAO;
    @Inject
    AccountHasGroupDAO accountHasGroupDAO;
    @Inject
    RidePassengerDAO ridePassengerDAO;
    @Inject
    AccountMapperDecorator accountMapper;
    @Inject
    EventDAO eventDAO;
    @Inject
    EventMapperImpl eventMapper;
    @Inject
    RideController rideController;
    @Inject
    AccountMetaDAO accountMetaDAO;
    @Inject
    TransactionResource transactionResource;
    @Inject
    EventController eventController;

    private AppConfiguration configuration;

    @Inject
    public RideResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "New ride",
            notes = "New ride",
            response = StgdrvResponseDTO.class)
    public Response addRide(@Restricted(required = true) Account me,
                            @ApiParam(required = true) RideDTO rideDto) {

        Preconds.checkConditions(rideDto == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DTO_NULL);

        Preconds.checkConditions(rideDto.getSeats() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_AVAILABLE_SEATS);

        Preconds.checkConditions(rideDto.getGoingArrivalDate() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_GOING_ARRIVAL_DATE);

        Preconds.checkConditions(rideDto.getGoingDepartureDate() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_GOING_DEPARTURE_DATE);

        if (rideDto.getHasReturn() != null && rideDto.getHasReturn()) {
            Preconds.checkConditions(rideDto.getReturnDepartureDate() == null,
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_GOING_DEPARTURE_DATE);

            Preconds.checkConditions(rideDto.getReturnArrivalDate() == null,
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_RETURN_DEPARTURE_DATE);
        }

        Preconds.checkConditions(rideDto.getFromCoordinate() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_FROM_NULL);

        Preconds.checkConditions(rideDto.getFromCoordinate().getLatitude() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_FROM_LAT_NULL);

        Preconds.checkConditions(rideDto.getFromCoordinate().getLongitude() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_FROM_LON_NULL);

        Preconds.checkConditions(rideDto.getToCoordinate() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_TO_NULL);

        Preconds.checkConditions(rideDto.getToCoordinate().getLatitude() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_TO_LAT_NULL);

        Preconds.checkConditions(rideDto.getToCoordinate().getLongitude() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_TO_LON_NULL);

        //Check is phone is verified (connection with provider [PHONE])
//        if (!me.getAccountConnections().isEmpty()) {
//            Boolean hasPhoneConnection = false;
//            for (AccountConnection accountConnection : me.getAccountConnections()) {
//                if (accountConnection.getProvider().equalsIgnoreCase(StgdrvData.Provider.PHONE)) {
//                    hasPhoneConnection = true;
//                    break;
//                }
//            }
//            if (!hasPhoneConnection)
//                throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_NOT_A_DRIVER);
//        } else {
//            throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_NOT_A_DRIVER);
//        }

        boolean isAdmin = accountController.isAdmin(me);

        if (!isAdmin && !accountController.accountHasRole(me, StgdrvData.AccountGroups.DRIVER)) {
            throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_NOT_A_DRIVER);
        }

        rideDto.setAccountId(me.getUid());

        Ride newRide = null;
        if (rideDto.getId() != null) {
            newRide = rideDAO.findByUid(rideDto.getId());

            if (!isAdmin) {
                if (newRide != null) {
                    Preconds.checkConditions(!newRide.getRidePassengers().isEmpty(), StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.RIDE_NOT_UPDATABLE);
                }
            }
        }

        if (newRide == null) {
            //Add ride
            newRide = rideMapper.rideDtoToRide(rideDto);
            newRide.setUid(TokenUtils.generateUid());
            newRide.setCreated(new Date());
            newRide.setAccountid(rideDto.getAccountId());
        }

        if (rideDto.getFromEventId() != null) {
            newRide.setFromEventId(rideDto.getFromEventId());
        }

        if (rideDto.getToEventId() != null) {
            newRide.setToEventId(rideDto.getToEventId());
        }

        newRide.setModified(new Date());

        newRide.setGoingArrivalDate(DateUtils.stringToDate(rideDto.getGoingArrivalDate()));
        newRide.setGoingDepartureDate(DateUtils.stringToDate(rideDto.getGoingDepartureDate()));
        if (rideDto.getReturnDepartureDate() != null)
            newRide.setReturnDepartureDate(DateUtils.stringToDate(rideDto.getReturnDepartureDate()));
        if (rideDto.getReturnArrivalDate() != null)
            newRide.setReturnArrivalDate(DateUtils.stringToDate(rideDto.getReturnArrivalDate()));
        if (rideDto.getEventId() != null)
            newRide.setEventid(rideDto.getEventId());
        if (rideDto.getFromCoordinate() != null) {
            newRide.setFromLat(rideDto.getFromCoordinate().getLatitude());
            newRide.setFromLon(rideDto.getFromCoordinate().getLongitude());
            newRide.setFromAddress(rideDto.getFromCoordinate().getAddress());

            if (newRide.getFromAddress() == null) {
                GeocoderUtils.locationToAddress(configuration.getGmaps().getKey(), newRide.getFromLat(), newRide.getFromLon(), "it");
            }
        }
        newRide.setHasReturn(rideDto.getHasReturn());
        if (rideDto.getToCoordinate() != null) {
            newRide.setToLat(rideDto.getToCoordinate().getLatitude());
            newRide.setToLon(rideDto.getToCoordinate().getLongitude());
            newRide.setToAddress(rideDto.getToCoordinate().getAddress());

            if (newRide.getToAddress() == null) {
                GeocoderUtils.locationToAddress(configuration.getGmaps().getKey(), newRide.getToLat(), newRide.getToLon(), "it");
            }
        }

        Preconds.checkConditions(newRide.getGoingDepartureDate().after(newRide.getGoingArrivalDate()),
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_INVALID_DATE);

        if (newRide.getHasReturn() != null && newRide.getHasReturn()) {
            Preconds.checkConditions(newRide.getReturnDepartureDate().after(newRide.getReturnArrivalDate()),
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_INVALID_DATE);

            Preconds.checkConditions(newRide.getGoingArrivalDate().after(newRide.getReturnDepartureDate()),
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_INVALID_DATE);
        }

        Event event = null;
        if (newRide.getFromEventId() != null) {
            event = eventDAO.findByUid(newRide.getFromEventId());

            Preconds.checkConditions(event == null,
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);
        }

        if (newRide.getToEventId() != null) {
            event = eventDAO.findByUid(newRide.getToEventId());

            Preconds.checkConditions(event == null,
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.EVENT_DOES_NOT_EXIST);
        }

        newRide.setSeats(rideDto.getSeats());
        newRide.setPrice(Math.round(rideDto.getPrice().getPrice() * 100.0) / 100.0);
        newRide.setTotalPrice(Math.round(rideDto.getPrice().getTotalPrice() * 100.0) / 100.0);
        newRide.setFee(Math.round(rideDto.getPrice().getFee() * 100.0) / 100.0);
        newRide.setCurrency("EUR");

        rideDAO.create(newRide);

        //addPassengerToRide(accountMapper.accountToAccountDto(me), newRide.getUid());

        //Check if user belongs to the rider group
        boolean isRider = false;
        if (me != null && me.getAccountHasGroups() != null) {
            for (AccountHasGroup accountHasGroup : me.getAccountHasGroups()) {
                if (accountHasGroup != null && accountHasGroup.getAccountGroup() != null) {
                    if (accountHasGroup.getAccountGroup().getName().equalsIgnoreCase(StgdrvData.AccountGroups.DRIVER)) {
                        isRider = true;
                        break;
                    }
                }
            }
        }
        //Add user to rider group (if not belong)
//        if (!isRider) {
//            AccountGroup groupRide = groupDAO.findByName(StgdrvData.AccountGroups.DRIVER);
//            Preconds.checkNotNull(groupRide,
//                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INTERNAL_ERROR, StgdrvMessage.MessageError.GROUP_DOES_NOT_EXIST));
//
//            AccountHasGroupId accountHasGroupId = new AccountHasGroupId();
//            accountHasGroupId.setAccountId(me.getId());
//            accountHasGroupId.setGroupId(groupRide.getId());
//            AccountHasGroup accountHasGroup = new AccountHasGroup();
//            accountHasGroup.setId(accountHasGroupId);
//            accountHasGroup.setAccount(me);
//            accountHasGroup.setAccountGroup(groupRide);
//            accountHasGroup.setCreated(new Date());
//            accountHasGroup.setModified(new Date());
//            accountHasGroupDAO.create(accountHasGroup);
//        }

        //Return response
        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setId(newRide.getUid());
        responseDto.setMessage(StgdrvMessage.OperationSuccess.RIDE_CREATE);

        eventController.updateEventDataAfterChanges(event.getUid());

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieves rides",
            notes = "Retrieves rides",
            response = RideDTO.class,
            responseContainer = "List")
    public Response getRides(@Restricted(required = false) Account me,
                             @ApiParam(value = "From coordinate (lat,comma,lon without brackets)") @QueryParam("fromCoordinate") String fromCoordinate,
                             @ApiParam(value = "To coordinate (lat,comma,lon without brackets)") @QueryParam("toCoordinate") String toCoordinate,
                             @ApiParam(value = "Coordinate max distance") @DefaultValue("200") @QueryParam("distance") Double distance,
                             @ApiParam(value = "From Event Id") @QueryParam("fromEventId") String fromEventId,
                             @ApiParam(value = "To Event Id") @QueryParam("toEventId") String toEventId,
                             @ApiParam(value = "Going departure date") @QueryParam("goingDepartureDate") String goingDepartureDate,
                             @ApiParam(value = "Going arrival date") @QueryParam("goingArrivalDate") String goingArrivalDate,
                             @ApiParam(value = "Return departure date") @QueryParam("returnDepartureDate") String returnDepartureDate,
                             @ApiParam(value = "Return arrival date") @QueryParam("returnArrivalDate") String returnArrivalDate,
                             @ApiParam(value = "The rides associated with the event") @QueryParam("eventId") String eventId,
                             @ApiParam(value = "Ride with tickets") @QueryParam("withTickets") Boolean withTickets,
                             @ApiParam(value = "Ride with bookings") @QueryParam("withBookings") Boolean withBookings,
                             @ApiParam(value = "Ride with friends") @DefaultValue("true") @QueryParam("withFriends") Boolean withFriends,
                             @ApiParam(value = "Ride with return") @QueryParam("hasReturn") Boolean hasReturn,
                             @ApiParam(value = "Ride filters") @QueryParam("filter") String filter,
                             @ApiParam(value = "Account id") @QueryParam("accountId") String accountId,
                             @ApiParam(value = "Number of available seats") @QueryParam("availableSeats") Integer availableSeats,
                             @ApiParam(value = "Number of booked seats") @QueryParam("bookedSeats") Integer bookedSeats,
                             @ApiParam(value = "Maximum price") @QueryParam("price") Double price,
                             @ApiParam(value = "Maximum total price") @QueryParam("price") Double totalPrice,
                             @ApiParam(value = "Order") @QueryParam("order") String order,
                             @ApiParam(value = "Sort") @QueryParam("sort") String sort,
                             @ApiParam(value = "Id like") @QueryParam("id_like") String idLike,
                             @ApiParam(value = "From Event Id like") @QueryParam("fromEventId_like") String fromEventIdLike,
                             @ApiParam(value = "To Event Id like") @QueryParam("toEventId_like") String toEventIdLike,
//                             @ApiParam(value = "Name like") @QueryParam("name_like") String nameLike,
//                             @ApiParam(value = "Category like") @QueryParam("category_like") String categoryLike,
                             @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size,
                             @ApiParam(value = "Response page size") @DefaultValue("50") @QueryParam("limit") Integer limit,
                             @ApiParam(value = "Response page index") @DefaultValue("0") @QueryParam("page") Integer page) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.RIDES_GET_ERROR);

        PagedResults<RideDTO> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        List<RideDTO> rideDtos = new ArrayList<>();

        //Check if user belongs to the rider group
        boolean isRider = false;
        if (me != null && me.getAccountHasGroups() != null && me.getAccountHasGroups().size() > 0) {
            for (AccountHasGroup accountHasGroup : me.getAccountHasGroups()) {
                if (accountHasGroup != null && accountHasGroup.getAccountGroup() != null) {
                    if (accountHasGroup.getAccountGroup().getName().equalsIgnoreCase(StgdrvData.AccountGroups.DRIVER)) {
                        isRider = true;
                        break;
                    }
                }
            }
        }

        //Check if user belongs to the user group
        boolean isUser = false;
        if (me != null && me.getAccountHasGroups() != null && me.getAccountHasGroups().size() > 0) {
            for (AccountHasGroup accountHasGroup : me.getAccountHasGroups()) {
                if (accountHasGroup != null && accountHasGroup.getAccountGroup() != null) {
                    if (accountHasGroup.getAccountGroup().getName().equalsIgnoreCase(StgdrvData.AccountGroups.USER)) {
                        isUser = true;
                        break;
                    }
                }
            }
        }

        Integer passengerAccountId = null;
        String driverAccountUid = null;

        Account reqAccount = null;
        if (accountId != null) {
            reqAccount = accountDAO.findByUid(accountId);
        } else if (me != null) {
            reqAccount = me;
        }

        if (filter != null) {
            if (filter.equals("created")) {
                if (accountId != null) {
                    driverAccountUid = accountId;
                } else if (me != null) {
                    driverAccountUid = me.getUid();
                }
            } else if (filter.equals("taken")) {
                if (accountId != null) {
                    Account acc = accountDAO.findByUid(accountId);
                    passengerAccountId = acc.getId();
                } else if (me != null) {
                    passengerAccountId = me.getId();
                }
            }
        }

        if (driverAccountUid == null && passengerAccountId == null && goingDepartureDate == null) {
            goingDepartureDate = DateUtils.dateToString(new Date());
        }

        String friends = null;
        if (withFriends != null && withFriends && reqAccount != null) {
            List<AccountMeta> metas = accountMetaDAO.findByMwrenchByAccount(StgdrvData.AccountMetas.FRIENDS, reqAccount.getUid());
            if (metas != null && metas.size() > 0) {
                friends = metas.get(0).getMvalue();
            }
        }

        boolean isAdmin = accountController.isAdmin(me);

//        Integer accountId = null;
        if (me != null && isAdmin) {
            friends = null;
            filter = null;
            goingDepartureDate = null;
            goingArrivalDate = null;
            returnDepartureDate = null;
            returnArrivalDate = null;
        }

        List<Ride> rides = rideDAO.findByFilter(fromCoordinate, toCoordinate,
                distance,
                goingDepartureDate,
                goingArrivalDate,
                returnDepartureDate,
                returnArrivalDate,
                fromEventId,
                toEventId,
                withTickets,
                withBookings,
                withFriends,
                hasReturn,
                availableSeats,
                bookedSeats,
                driverAccountUid,
                passengerAccountId,
                friends,
                totalPrice,
                price,
                page,
                limit,
                sort,
                order,
                idLike,
                fromEventIdLike,
                toEventIdLike,
                results);
        rideDtos = rideMapper.ridesToRideDtos(rides);
        if (!rideDtos.isEmpty()) {
            for (RideDTO rideDto : rideDtos) {
                Ride ride = rideDAO.findByUid(rideDto.getId());
                Account driver = accountDAO.findByUid(ride.getAccountid());

                //Price
                RidePriceDTO ridePriceDto = new RidePriceDTO();
                ridePriceDto.setPrice(ride.getPrice());
                ridePriceDto.setTotalPrice(ride.getTotalPrice());
                ridePriceDto.setFee(ride.getFee());
                rideDto.setPrice(ridePriceDto);

                if (ride.getReturnDepartureDate() != null)
                    rideDto.setReturnDepartureDate(DateUtils.dateToString(ride.getReturnDepartureDate()));
                if (ride.getReturnArrivalDate() != null)
                    rideDto.setReturnArrivalDate(DateUtils.dateToString(ride.getReturnArrivalDate()));
                if (ride.getGoingArrivalDate() != null)
                    rideDto.setGoingArrivalDate(DateUtils.dateToString(ride.getGoingArrivalDate()));
                if (ride.getGoingDepartureDate() != null)
                    rideDto.setGoingDepartureDate(DateUtils.dateToString(ride.getGoingDepartureDate()));
                if (ride.getHasReturn() != null)
                    rideDto.setHasReturn(ride.getHasReturn());

                if (ride.getReturnDepartureDate() != null && ride.getReturnArrivalDate() != null) {
                    rideDto.setHasReturn(true);
                }

                //From-To
                CoordinateDTO from = new CoordinateDTO();
                from.setLatitude(ride.getFromLat());
                from.setLongitude(ride.getFromLon());
                from.setAddress(ride.getFromAddress());
                rideDto.setFromCoordinate(from);
                CoordinateDTO to = new CoordinateDTO();
                to.setLatitude(ride.getToLat());
                to.setLongitude(ride.getToLon());
                to.setAddress(ride.getToAddress());
                rideDto.setToCoordinate(to);
                rideDto.setEventId(ride.getEventid());

                Event gEvent = null;
                if (ride.getHasReturn() != null)
                    rideDto.setHasReturn(ride.getHasReturn());

                if (ride.getEventid() != null) {
                    Event event = eventDAO.findByUid(ride.getEventid());
                    gEvent = event;
                    rideDto.setToEvent(eventMapper.eventToEventDto(event));
                }

                if (ride.getFromEventId() != null) {
                    Event event = eventDAO.findByUid(ride.getFromEventId());
                    gEvent = event;
                    rideDto.setFromEvent(eventMapper.eventToEventDto(event));
                }

                if (ride.getToEventId() != null) {
                    Event event = eventDAO.findByUid(ride.getToEventId());
                    gEvent = event;
                    rideDto.setToEvent(eventMapper.eventToEventDto(event));
                }

                //AccountId
                rideDto.setAccountId(ride.getAccountid());
                if (driver != null) {
                    rideDto.setAccount(accountMapper.accountToAccountDtoLight(driver));
                    rideDto.getAccount().setHasTicket(eventController.accountHasTicket(driver.getId(), gEvent.getUid()));
                }

                if (friends != null && !friends.equals("")) {
                    List<RidePassenger> friendsPassengers = ridePassengerDAO.findByRideAndAccountIds(ride, friends);
                    if (friendsPassengers != null && !friendsPassengers.isEmpty()) {
                        List<RidePassengerDTO> friendsPassengersDTO = ridePassengerMapper.ridePassengersToRidePassengerDtos(friendsPassengers);
                        rideDto.setFriends(friendsPassengersDTO);
                    }
                }
            }
        }

        if (results != null) {
            results.setData(rideDtos);
            return Response.ok(results).build();
        }

        return Response.ok(rideDtos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{rideId}")
    //@Metered
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve ride",
            notes = "Retrieve ride",
            response = RideDTO.class)
    public Response getRide(@Restricted(required = false) Account me,
                            @PathParam("rideId") String rideId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.RIDE_GET_ERROR);

        Preconds.checkNotNull(rideId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_ID_NULL));

        Ride ride = rideDAO.findByUid(rideId);
        Preconds.checkConditions(ride == null || (ride.getStatus() != null && ride.getStatus().equals(StgdrvData.RideStatusses.DELETED)),
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST);

        RideDTO rideDto = rideMapper.rideToRideDto(ride);
        Account driver = accountDAO.findByUid(ride.getAccountid());
        List<RidePassenger> ridePassengers = ridePassengerDAO.findByRide(ride);
        List<RidePassenger> passengers = new ArrayList<>();
        if (!ridePassengers.isEmpty()) {
            for (RidePassenger ridePassenger : ridePassengers) {
                if (ridePassenger.getStatus() == null || !ridePassenger.getStatus().equals("deleted")) {
                    passengers.add(ridePassenger);
                }
            }
            rideDto.setPassengers(ridePassengerMapper.ridePassengersToRidePassengerDtos(passengers));
        }


        String friends = null;
        if (me != null) {
            List<AccountMeta> metas = accountMetaDAO.findByMwrenchByAccount(StgdrvData.AccountMetas.FRIENDS, me.getUid());
            if (metas != null && metas.size() > 0) {
                friends = metas.get(0).getMvalue();
            }
        }

        Integer bookedSeats = rideController.getRideBookedSeats(ride);

        //Price
        RidePriceDTO ridePriceDto = new RidePriceDTO();
        ridePriceDto.setPrice(ride.getPrice());
        ridePriceDto.setTotalPrice(ride.getTotalPrice());
        ridePriceDto.setFee(ride.getFee());
        rideDto.setPrice(ridePriceDto);
        //Going
        if (ride.getGoingDepartureDate() != null)
            rideDto.setGoingDepartureDate(DateUtils.dateToString(ride.getGoingDepartureDate()));
        if (ride.getGoingArrivalDate() != null)
            rideDto.setGoingArrivalDate(DateUtils.dateToString(ride.getGoingArrivalDate()));
        //Return
        if (ride.getReturnDepartureDate() != null)
            rideDto.setReturnArrivalDate(DateUtils.dateToString(ride.getReturnArrivalDate()));
        if (ride.getReturnArrivalDate() != null)
            rideDto.setReturnArrivalDate(DateUtils.dateToString(ride.getReturnArrivalDate()));
        if (ride.getHasReturn() != null) {
            rideDto.setHasReturn(ride.getHasReturn());
        }
        if (ride.getReturnDepartureDate() != null && ride.getReturnArrivalDate() != null) {
            rideDto.setHasReturn(true);
        }
        //From-To
        CoordinateDTO from = new CoordinateDTO();
        from.setLatitude(ride.getFromLat());
        from.setLongitude(ride.getFromLon());
        from.setAddress(ride.getFromAddress());
        rideDto.setFromCoordinate(from);
        CoordinateDTO to = new CoordinateDTO();
        to.setLatitude(ride.getToLat());
        to.setLongitude(ride.getToLon());
        to.setAddress(ride.getToAddress());
        rideDto.setToCoordinate(to);

        Event gEvent = null;
        if (ride.getEventid() != null) {
            Event event = eventDAO.findByUid(ride.getEventid());
            gEvent = event;
            rideDto.setToEvent(eventMapper.eventToEventDto(event));
        }

        if (ride.getFromEventId() != null) {
            Event event = eventDAO.findByUid(ride.getFromEventId());
            gEvent = event;
            rideDto.setFromEvent(eventMapper.eventToEventDto(event));
        }

        if (ride.getToEventId() != null) {
            Event event = eventDAO.findByUid(ride.getToEventId());
            gEvent = event;
            rideDto.setToEvent(eventMapper.eventToEventDto(event));
        }

        //AccountId
        rideDto.setAccountId(ride.getAccountid());
        if (driver != null) {
            rideDto.setAccount(accountMapper.accountToAccountDto(driver));
            rideDto.getAccount().setHasTicket(eventController.accountHasTicket(driver.getId(), gEvent.getUid()));
        }

        if (friends != null && !friends.equals("")) {
            List<RidePassenger> friendsPassengers = ridePassengerDAO.findByRideAndAccountIds(ride, friends);
            if (friendsPassengers != null && !friendsPassengers.isEmpty()) {
                List<RidePassengerDTO> friendsPassengersDTO = ridePassengerMapper.ridePassengersToRidePassengerDtos(friendsPassengers);
                rideDto.setFriends(friendsPassengersDTO);
            }
        }

        rideDto.setEventId(ride.getEventid());
        rideDto.setSeats(ride.getSeats());
        rideDto.setAvailableSeats(ride.getSeats() - bookedSeats);
        rideDto.setBookedSeats(bookedSeats);

        // Check for reservation
        if (me != null) {
            List<RidePassenger> ridePassengs = ridePassengerDAO.findByAccountAndRide(me, ride);
            if (ridePassengs != null && !ridePassengs.isEmpty()) {
                rideDto.setUserPassenger(ridePassengerMapper.ridePassengerToRidePassengerDto(ridePassengs.get(0)));
            }

        }

        return Response.ok(rideDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{rideId}")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Edit ride",
            notes = "Edit ride",
            response = StgdrvResponseDTO.class)
    public Response editRide(
            @Restricted Account me,
            @ApiParam(required = true) RideDTO rideDto,
            @PathParam("rideId") String rideId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.RIDE_MODIFIED);

        rideDto.setId(rideId);

        return addRide(me, rideDto);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{rideId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete ride",
            notes = "Delete ride",
            response = StgdrvResponseDTO.class)
    public Response deleteRide(@PathParam("rideId") String rideId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage("Done");

        Ride ride = rideDAO.findByUid(rideId);
        Preconds.checkConditions(ride == null, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST);
        Preconds.checkConditions(ride.getStatus() != null && ride.getStatus().equals("deleted"), StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST);

        Event gEvent = null;
        if (ride.getEventid() != null) {
            Event event = eventDAO.findByUid(ride.getEventid());
            gEvent = event;
        }
        if (ride.getFromEventId() != null) {
            Event event = eventDAO.findByUid(ride.getFromEventId());
            gEvent = event;
        }
        if (ride.getToEventId() != null) {
            Event event = eventDAO.findByUid(ride.getToEventId());
            gEvent = event;
        }

        if (!ride.getRidePassengers().isEmpty()) {
            for (RidePassenger ridePassenger : ride.getRidePassengers()) {
                RidePassengerId ridePassengerId = new RidePassengerId();
                ridePassengerId.setRideId(ridePassenger.getRide().getId());
                RidePassenger ridePassengerToDelete = ridePassengerDAO.findById(ridePassengerId);
                if (ridePassengerToDelete != null) {
                    ridePassengerToDelete.setStatus("deleted");
                    ridePassengerDAO.edit(ridePassengerToDelete);
                }
            }
        }

        ride.setStatus("deleted");
        rideDAO.edit(ride);

        eventController.updateEventDataAfterChanges(gEvent.getUid());

        return Response.ok(responseDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{rideId}/passengers")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add passenger to this ride",
            notes = "Add passenger to this ride",
            response = StgdrvResponseDTO.class)
    public Response addPassengerToRide(
            @Restricted Account requestUser,
            @ApiParam(required = true) RidePassengerDTO ridePassengerDTO,
            @PathParam("rideId") String rideId) {

        Preconds.checkNotNull(ridePassengerDTO,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DTO_NULL));

        Ride ride = rideDAO.findByUid(rideId);
        Preconds.checkConditions(ride == null || (ride.getStatus() != null && ride.getStatus().equals(StgdrvData.RideStatusses.DELETED)),
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST);

        String eventId = ride.getFromEventId();
        if (eventId == null) {
            eventId = ride.getToEventId();
        }

//        Preconds.checkConditions(ride.getPrice() > 0 && ridePassengerDTO.getTransaction() == null,
//                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_NEED_TRANSACTION);

        Integer bookedSeats = rideController.getRideBookedSeats(ride);
        Integer bookingSeats = ridePassengerDTO.getSeats();
        if (bookingSeats == null) {
            bookingSeats = 1;
        }

        String accountUid = requestUser.getUid();
        if (ridePassengerDTO.getAccount() != null) {
            accountUid = ridePassengerDTO.getAccount().getId();
        }

        Account account = accountDAO.findByUid(accountUid);
        Preconds.checkNotNull(account,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_ID_NULL));

        if (!ride.getRidePassengers().isEmpty() && bookedSeats + bookingSeats > ride.getSeats())
            throw new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.RIDE_IS_FULL, StgdrvMessage.MessageError.RIDE_IS_FULL);

        Preconds.checkConditions(!ridePassengerDAO.findByAccountAndRide(account, ride).isEmpty(),
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.PASSENGER_CANNOT_REGISTER);

        RidePassengerId ridePassengerId = new RidePassengerId();
        ridePassengerId.setRideId(ride.getId());
        RidePassenger ridePassenger = new RidePassenger();
        ridePassenger.setId(ridePassengerId);
        ridePassenger.setUid(TokenUtils.generateUid());
        ridePassenger.setCreated(new Date());
        ridePassenger.setModified(new Date());
        ridePassenger.setAccountId(account.getId());
        ridePassenger.setSeats(bookingSeats);
        ridePassenger.setVisible(true);
        ridePassenger.setStatus(StgdrvData.RidePassengersStatusses.PENDING);

        if (ridePassengerDTO.getTransaction() != null) {
            ridePassengerDTO.getTransaction().setRideId(rideId);
            ridePassengerDTO.getTransaction().setEventId(eventId);

            Response transactionResponse = transactionResource.addTransaction(account, ridePassengerDTO.getTransaction());
            StgdrvResponseDTO transRes = (StgdrvResponseDTO) transactionResponse.getEntity();
            ridePassenger.setTransactionId(transRes.getId());

            ridePassenger.setStatus(StgdrvData.RidePassengersStatusses.ENABLED);
        }

        ridePassengerDAO.create(ridePassenger);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(ridePassenger.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.PASSENGER_CREATE);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{rideId}/passengers")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Get passengers of this ride",
            notes = "Get passengers of this ride",
            responseContainer = "List",
            response = RidePassengerDTO.class)
    public Response getPassengerOfRide(
            @Restricted(required = false) Account requestUser,
            @PathParam("rideId") String rideId,
            @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.PASSENGER_CREATE);

        PagedResults<RidePassengerDTO> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        Ride ride = rideDAO.findByUid(rideId);
        Preconds.checkNotNull(ride,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST));

        Event gEvent = null;
        if (ride.getEventid() != null) {
            Event event = eventDAO.findByUid(ride.getEventid());
            gEvent = event;
        }
        if (ride.getFromEventId() != null) {
            Event event = eventDAO.findByUid(ride.getFromEventId());
            gEvent = event;
        }
        if (ride.getToEventId() != null) {
            Event event = eventDAO.findByUid(ride.getToEventId());
            gEvent = event;
        }

        boolean isAdmin = accountController.isAdmin(requestUser);

        List<RidePassenger> ridePassengers = ridePassengerDAO.findByRide(ride, isAdmin);

        List<RidePassengerDTO> ridePassengerDTOS = ridePassengerMapper.ridePassengersToRidePassengerDtos(ridePassengers, gEvent);

        if (results != null) {
            results.setData(ridePassengerDTOS);
            results.setSize(ridePassengerDTOS.size());
            return Response.ok(results).build();
        }

        return Response.ok(ridePassengerDTOS).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{rideId}/passengers/{passengerId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete passenger to this ride",
            notes = "Delete passenger to this ride",
            response = StgdrvResponseDTO.class)
    public Response getPassenger(@PathParam("passengerId") String passengerId,
                                 @PathParam("rideId") String rideId) {

        Preconds.checkNotNull(passengerId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.PASSENGER_ID_NULL));

        Ride ride = rideDAO.findByUid(rideId);
        Preconds.checkNotNull(ride,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST));

        Account passenger = accountDAO.findByUid(passengerId);
        Preconds.checkNotNull(passenger,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST));

        RidePassengerId ridePassengerId = new RidePassengerId();
        ridePassengerId.setRideId(ride.getId());
        RidePassenger ridePassenger = ridePassengerDAO.findById(ridePassengerId);

        Preconds.checkNotNull(ridePassenger,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.PASSENGER_DOES_NOT_EXIST));


        return Response.ok(accountMapper.accountToAccountDto(passenger)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{rideId}/passengers/{passengerId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete passenger to this ride",
            notes = "Delete passenger to this ride",
            response = StgdrvResponseDTO.class)
    public Response deletePassengerToRide(@Restricted Account requestAccount,
                                          @PathParam("passengerId") String passengerId,
                                          @PathParam("rideId") String rideId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.PASSENGER_DELETE);

        Ride ride = rideDAO.findByUid(rideId);
        Preconds.checkNotNull(ride,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST));

        RidePassenger ridePassenger = ridePassengerDAO.findByUid(passengerId);
        Preconds.checkConditions(ridePassenger == null || ridePassenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED),
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.PASSENGER_DOES_NOT_EXIST);

        Account account = accountDAO.findById(ridePassenger.getAccountId());
        Preconds.checkNotNull(account,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.RIDE_DOES_NOT_EXIST));

        boolean isAdmin = accountController.isAdmin(requestAccount);

        if (!isAdmin) {
            Preconds.checkConditions(!requestAccount.getId().equals(account.getId()),
                    StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.INVALID_PERMISSION);
            Preconds.checkConditions(account.getUid() == ride.getAccountid(),
                    StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.PASSENGER_CANT_BE_REMOVED);
        }

        if (ridePassenger.getTransactionId() != null) {
            transactionResource.deleteTransaction(ridePassenger.getTransactionId());
        }

        ridePassenger.setStatus(StgdrvData.RidePassengersStatusses.DELETED);
        ridePassengerDAO.edit(ridePassenger);

        ridePassengerDAO.getCurrentSession().flush();

        return Response.ok(responseDto).build();
    }
}
