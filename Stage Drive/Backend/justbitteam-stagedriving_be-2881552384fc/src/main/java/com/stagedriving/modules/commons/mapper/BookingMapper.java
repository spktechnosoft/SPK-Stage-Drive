package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.BookingDTO;
import com.stagedriving.modules.commons.ds.entities.Booking;
import com.stagedriving.modules.commons.ds.entities.EventHasBooking;
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
public interface BookingMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "events", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BookingDTO bookingToBookingDto(Booking booking);

    List<BookingDTO> bookingsToBookingDtos(List<Booking> bookings);

    @Mappings({
            @Mapping(source = "booking.uid", target = "id"),
            @Mapping(source = "booking.status", target = "status"),
            @Mapping(source = "booking.withTicket", target = "withTicket"),
            @Mapping(target = "events", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BookingDTO eventHasBookingToBookingDto(EventHasBooking booking);

    List<BookingDTO> eventHasBookingsToBookingDtos(List<EventHasBooking> bookings);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "eventHasBookings", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true),
            @Mapping(target = "withTicket", source = "withTicket")
    })
    Booking bookingDtoToBooking(BookingDTO bookingDTO);

    List<Booking> bookingDtosToBookings(List<BookingDTO> bookingDTOs);
}