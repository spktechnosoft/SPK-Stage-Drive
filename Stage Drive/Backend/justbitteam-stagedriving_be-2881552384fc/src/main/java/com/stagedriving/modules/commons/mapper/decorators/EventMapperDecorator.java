package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.EventDTO;
import com.stagedriving.modules.commons.ds.daos.EventHasActionDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasBookingDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasInterestDAO;
import com.stagedriving.modules.commons.ds.daos.RideDAO;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.mapper.ActionMapperImpl;
import com.stagedriving.modules.commons.mapper.EventMapperImpl;
import com.stagedriving.modules.commons.mapper.RideMapperImpl;
import com.stagedriving.modules.commons.utils.location.GeoLocationUtils;

import java.util.ArrayList;
import java.util.List;

public class EventMapperDecorator extends EventMapperImpl {

    @Inject
    private EventHasInterestDAO eventHasInterestDAO;
    @Inject
    private EventHasBookingDAO eventHasBookingDAO;
    @Inject
    private EventHasActionDAO eventHasActionDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private BookingMapperDecorator bookingMapper;
    @Inject
    private ActionMapperImpl actionMapper;
    @Inject
    private RideMapperImpl rideMapper;


    public EventDTO eventToEventDto(Event event, Account account) {
        return eventToEventDto(event, account, null, null);
    }

    public EventDTO eventToEventDto(Event event, Account account, Double latitude, Double longitude) {
        EventDTO dto = super.eventToEventDto(event);

        if (account != null) {
            List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByEventAndAccount(event, account.getId());
            List<EventHasAction> eventHasComments = eventHasActionDAO.findByEventAndAccount(event, account.getId(), "comment");
            List<EventHasAction> eventHasLikes = eventHasActionDAO.findByEventAndAccount(event, account.getId(), "like");
//            List<Ride> rides = rideDAO.findByFilter(null, null, null, null, null, null, null, null, event.getUid(), null , null, null, null, null, null , null, null, null, null, null, null , null, null, null, null, null, null, null);

            dto.setBookings(bookingMapper.eventHasBookingsToBookingDtos(eventHasBookings));
            dto.setLikes(actionMapper.eventHasActionsToAtionDtos(eventHasLikes));
            dto.setComments(actionMapper.eventHasActionsToAtionDtos(eventHasComments));
//            dto.setNride(rides.size());
        }

        if (latitude != null && longitude != null) {
            dto.setDistance(GeoLocationUtils.getDistance(latitude, longitude, event.getLatitude(), event.getLongitude(), "km"));
        }

        return dto;
    }

    public ArrayList<EventDTO> eventsToEventDtos(List<Event> events, Account account) {
        return eventsToEventDtos(events, account, null, null);
    }

    public ArrayList<EventDTO> eventsToEventDtos(List<Event> events, Account account, Double latitude, Double longitude) {
        if ( events == null ) {
            return null;
        }

        ArrayList<EventDTO> list = new ArrayList<EventDTO>( events.size() );
        for ( Event event : events ) {
            list.add( eventToEventDto( event, account, latitude, longitude ) );
        }

        return list;
    }
}
