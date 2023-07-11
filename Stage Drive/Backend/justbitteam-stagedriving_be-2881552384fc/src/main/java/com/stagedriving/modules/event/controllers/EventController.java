package com.stagedriving.modules.event.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.commons.TokenUtils;
import com.justbit.sque.SqueController;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.event.workers.model.EventWorkerEvent;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Singleton
public class EventController {

    @Inject
    private EventHasInterestDAO eventHasInterestDAO;
    @Inject
    private EventHasBookingDAO eventHasBookingDAO;
    @Inject
    private EventHasActionDAO eventHasActionDAO;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private SqueController squeController;

    public boolean accountHasTicket(Integer accountId, String eventId) {
        boolean hasTicket = false;

        Event event = eventDAO.findByUid(eventId);

        List<EventHasInterest> eventHasInterests = eventHasInterestDAO.findWithTicketByEventAndAccount(event, accountId);
//        for (EventHasInterest eventHasInterest : eventHasInterests) {
//            if (eventHasInterest.getBookingTicketId() != null) {
//                hasTicket = true;
//                break;
//            }
//        }
        if (!eventHasInterests.isEmpty()) {
            hasTicket = true;
        }

        return hasTicket;
    }

    public void updateEventInterestAfterChanges(Integer accountId, String eventId) {

        Event event = eventDAO.findByUid(eventId);

        List<EventHasInterest> eventHasInterests = eventHasInterestDAO.findByEventAndAccount(event, accountId);
        EventHasInterest eventHasInterest = null;
        if (!eventHasInterests.isEmpty()) {
            eventHasInterest = eventHasInterests.get(0);
        } else {
            eventHasInterest = new EventHasInterest();
            eventHasInterest.setUid(TokenUtils.generateUid());
            eventHasInterest.setCreated(new Date());
            eventHasInterest.setModified(new Date());
            eventHasInterest.setEvent(event);
            eventHasInterest.setAccountId(accountId);
            event.getEventHasInterests().add(eventHasInterest);
        }

        List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByEventAndAccount(event, accountId);
        if (!eventHasBookings.isEmpty()) {
            EventHasBooking eventHasBooking = eventHasBookings.get(0);
            if (eventHasBooking.getBooking().getWithTicket() != null && eventHasBooking.getBooking().getWithTicket()) {
                eventHasInterest.setBookingTicketId(eventHasBooking.getBooking().getId());
            }
            eventHasInterest.setBookingId(eventHasBooking.getBooking().getId());
        } else {
            eventHasInterest.setBookingTicketId(null);
            eventHasInterest.setBookingId(null);
        }

        List<EventHasAction> likeActions = eventHasActionDAO.findByEventAndAccount(event, accountId, "like");
        if (!likeActions.isEmpty()) {
            EventHasAction likeAction = likeActions.get(0);
            eventHasInterest.setActionLikeId(likeAction.getId());
        } else {
            eventHasInterest.setActionLikeId(null);
        }

        List<EventHasAction> commentsActions = eventHasActionDAO.findByEventAndAccount(event, accountId, "comment");
        if (!commentsActions.isEmpty()) {
            EventHasAction commentAction = commentsActions.get(0);
            eventHasInterest.setActionCommentId(commentAction.getId());
        } else {
            eventHasInterest.setActionCommentId(null);
        }

        List<EventHasAction> ridesActions = eventHasActionDAO.findByEventAndAccount(event, accountId, "ride");
        if (!ridesActions.isEmpty()) {
            EventHasAction rideAction = ridesActions.get(0);
            eventHasInterest.setActionRideId(rideAction.getId());
        } else {
            eventHasInterest.setActionRideId(null);
        }

        eventHasInterestDAO.edit(eventHasInterest);

        updateEventDataAfterChanges(eventId);
    }

    public void updateEventDataAfterChanges(String eventId) {

        Event event = eventDAO.findByUid(eventId);

        int nrBooking = eventHasInterestDAO.countByEventAndMetric(event, false, true, false, false, false);
        int nrBTicket = eventHasInterestDAO.countByEventAndMetric(event, true, false, false, false, false);
        int nrLike = eventHasInterestDAO.countByEventAndMetric(event, false, false, true, false, false);
        int nrComment = eventHasActionDAO.countByEvent(event, "comment");
        int nrPassenger = eventHasInterestDAO.countByEventAndMetric(event, false, false, false, false, true);
        int nrRide = rideDAO.countByEventAvailable(event);

        event.setNride(nrRide);
        event.setNlike(nrLike);
        event.setNbooking(nrBooking);
        event.setNbookingticket(nrBTicket);
        event.setNcomment(nrComment);
        eventDAO.edit(event);

//        List<Ride> rides = rideDAO.findByFromEventId(eventId);
//        rides.addAll(rideDAO.findByToEventId(eventId));
//        for (Ride ride : rides) {
//            ride.setWithBookings(nrBooking > 0);
//            ride.setWithTickets(nrBTicket > 0);
//
//            rideDAO.edit(ride);
//        }
    }

    public void enqueueEventWorkerForRidePaggenger(RidePassenger ridePassenger) {
        EventWorkerEvent eventWorkerEvent = new EventWorkerEvent();
        eventWorkerEvent.setRidePassengerId(ridePassenger.getUid());
        try {
            squeController.enqueueScheduled(eventWorkerEvent, "eventinterests", 8000, DateTime.now().plusSeconds(6).toDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enqueueEventWorkerForEvent(int accountId, Event event) {
        EventWorkerEvent eventWorkerEvent = new EventWorkerEvent();
        eventWorkerEvent.setAccountId(accountId);
        eventWorkerEvent.setId(event.getUid());
        try {
            squeController.enqueueScheduled(eventWorkerEvent, "eventinterests", 8000, DateTime.now().plusSeconds(6).toDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
