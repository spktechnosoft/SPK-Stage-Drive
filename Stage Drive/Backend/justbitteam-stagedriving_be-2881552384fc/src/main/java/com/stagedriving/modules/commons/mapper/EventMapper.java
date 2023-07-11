package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.BookingDTO;
import com.stagedriving.commons.v1.resources.EventDTO;
import com.stagedriving.commons.v1.resources.ImageDTO;
import com.stagedriving.modules.commons.ds.entities.Booking;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventImage;
import com.stagedriving.modules.commons.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Project stagedriving api
 * Author: man
 * Date: 16/10/18
 * Time: 18:36
 */
@Mapper
public interface EventMapper {

    @Mappings({
            @Mapping(source = "event.uid", target = "id"),
            @Mapping(target = "bookings", ignore = true),
            @Mapping(target = "checkins", ignore = true),
            @Mapping(target = "actions", ignore = true),
            @Mapping(target = "likes", ignore = true),
            @Mapping(target = "comments", ignore = true),
            @Mapping(target = "interests", ignore = true),
            @Mapping(target = "address", source = "event.address"),
            @Mapping(target = "country", source = "event.country"),
            @Mapping(target = "town", source = "event.town"),
            @Mapping(target = "city", source = "event.city"),
            @Mapping(target = "zipcode", source = "event.zipcode"),
            @Mapping(target = "images", source = "event.eventImages"),
            @Mapping(target = "status", source = "event.status"),
            @Mapping(target = "visible", ignore = true),
            @Mapping(target = "mobile", ignore = true),
            @Mapping(target = "coordinate.latitude", source = "event.latitude"),
            @Mapping(target = "coordinate.longitude", source = "event.longitude"),
            @Mapping(target = "created", source = "event.created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", source = "event.modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    EventDTO eventToEventDto(Event event);

    List<EventDTO> eventsToEventDtos(List<Event> events);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "eventHasCheckins", ignore = true),
            @Mapping(target = "eventHasFellowships", ignore = true),
            @Mapping(target = "eventHasBookings", ignore = true),
            @Mapping(target = "eventImages", ignore = true),
            @Mapping(target = "eventStuffs", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true),
            @Mapping(source = "coordinate.latitude", target = "latitude"),
            @Mapping(source = "coordinate.longitude", target = "longitude")
    })
    Event eventDtoToEvent(EventDTO eventDTO);

    List<Event> eventDtosToEvents(List<EventDTO> eventDTOs);


    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "events", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BookingDTO bookingToBookingDto(Booking booking);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "eventHasBookings", ignore = true)
    })
    Booking bookingDtoToBooking(BookingDTO bookingDTO);


    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(source = "image", target = "uri"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ImageDTO eventImageToImageDto(EventImage eventImage);
    List<ImageDTO> eventImagesToImageDtos(List<EventImage> eventImages);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "event", ignore = true),
    })
    EventImage imageDtoToEventImage(ImageDTO imageDTO);
    List<EventImage> imageDtosToEventImages(List<ImageDTO> imageDTOs);

}