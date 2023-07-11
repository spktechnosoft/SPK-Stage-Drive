package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.BookingDTO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.EventHasBooking;
import com.stagedriving.modules.commons.mapper.BookingMapperImpl;
import com.stagedriving.modules.event.controllers.EventController;

import java.util.ArrayList;
import java.util.List;

public class BookingMapperDecorator extends BookingMapperImpl {

    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private EventMapperDecorator eventMapper;
    @Inject
    private EventController eventController;

    public BookingDTO eventHasBookingToBookingDto(EventHasBooking booking, Account account) {

        BookingDTO bookingDTO = eventHasBookingToBookingDto(booking);

        if (booking.getEvent() != null) {
            bookingDTO.setEvent(eventMapper.eventToEventDto(booking.getEvent(), account));
            bookingDTO.getAccount().setHasTicket(bookingDTO.getWithTicket());
        }

        return bookingDTO;
    }

    public BookingDTO eventHasBookingToBookingDto(EventHasBooking booking) {

        BookingDTO bookingDTO = super.eventHasBookingToBookingDto(booking);

        if (booking.getAccountid() != null) {
            Account account = accountDAO.findById(booking.getAccountid());
            bookingDTO.setAccount(accountMapper.accountToAccountDto(account));
        }
        if (booking.getEvent() != null) {
            bookingDTO.setEvent(eventMapper.eventToEventDto(booking.getEvent()));
            bookingDTO.getAccount().setHasTicket(bookingDTO.getWithTicket());
        }

        return bookingDTO;
    }

    public List<BookingDTO> eventHasBookingsToBookingDtos(List<EventHasBooking> bookings, Account account) {
        if (bookings == null) {
            return null;
        }

        List<BookingDTO> list = new ArrayList<BookingDTO>(bookings.size());
        for (EventHasBooking eventHasBooking : bookings) {
            list.add(eventHasBookingToBookingDto(eventHasBooking, account));
        }

        return list;
    }

    public List<BookingDTO> eventHasBookingsToBookingDtos(List<EventHasBooking> bookings) {
        if (bookings == null) {
            return null;
        }

        List<BookingDTO> list = new ArrayList<BookingDTO>(bookings.size());
        for (EventHasBooking eventHasBooking : bookings) {
            list.add(eventHasBookingToBookingDto(eventHasBooking));
        }

        return list;
    }
}
