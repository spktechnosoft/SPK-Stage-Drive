package com.stagedriving.modules.event.interceptors;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.EventHasAction;
import com.stagedriving.modules.commons.ds.entities.RidePassenger;
import com.stagedriving.modules.commons.mapper.decorators.BookingMapperDecorator;
import com.stagedriving.modules.event.controllers.EventController;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.user.controller.FriendshipController;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class EventsEventListener extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener, PostDeleteEventListener {

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private AccountReviewDAO accountReviewDAO;
    @Inject
    private NotificationController notificationController;
    @Inject
    private AccountFriendshipDAO friendshipDAO;
    @Inject
    private AccountMetaDAO metaDAO;
    @Inject
    private RidePassengerDAO ridePassengerDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private EventController eventController;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private BookingDAO bookingDAO;
    @Inject
    private EventHasBookingDAO eventHasBookingDAO;
    @Inject
    private FriendshipController friendshipController;
    @Inject
    private EventHasActionDAO eventHasActionDAO;
    @Inject
    private BookingMapperDecorator bookingMapper;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        process(postInsertEvent.getEntity(), postInsertEvent, null, null);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        process(postUpdateEvent.getEntity(), null, postUpdateEvent, null);
    }

    @SneakyThrows
    private void process(Object object, PostInsertEvent postInsertEvent, PostUpdateEvent postUpdateEvent, PostDeleteEvent postDeleteEvent) {


        if (object instanceof RidePassenger && (postInsertEvent != null || postUpdateEvent != null || postDeleteEvent != null)) {
            eventController.enqueueEventWorkerForRidePaggenger((RidePassenger) object);
        } else if (object instanceof EventHasAction && (postInsertEvent != null || postUpdateEvent != null || postDeleteEvent != null)) {
            EventHasAction eventHasAction = (EventHasAction) object;
            eventController.enqueueEventWorkerForEvent(eventHasAction.getAccountid(), eventHasAction.getEvent());
        }
//            RidePassenger ridePassenger = (RidePassenger) object;
//            ridePassenger = ridePassengerDAO.findByUid(ridePassenger.getUid());
//
//            log.info("New ride passenger "+ridePassenger.getUid());
//
//            String eventId = ridePassenger.getRide().getEventid();
//            if (eventId == null) {
//                eventId = ridePassenger.getRide().getFromEventId();
//            }
//            if (eventId == null) {
//                eventId = ridePassenger.getRide().getToEventId();
//            }
//            Event event = eventDAO.findByUid(eventId);
//
//            if (ridePassenger.getStatus() != null && ridePassenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
//                List<EventHasAction> rideActions = eventHasActionDAO.findByEventAndAccount(event, ridePassenger.getAccountId(), "ride");
//                if (!rideActions.isEmpty()) {
//                    EventHasAction eventHasAction = rideActions.get(0);
//                    eventHasActionDAO.delete(eventHasAction);
//                }
//            } else {
//                // Add ride action
//                EventHasAction eventHasAction = new EventHasAction();
//                eventHasAction.setUid(TokenUtils.generateUid());
//                eventHasAction.setCreated(new Date());
//                eventHasAction.setEvent(event);
//                eventHasAction.setTaxonomy("ride");
//                eventHasAction.setVisible(true);
//                eventHasAction.setAccountid(ridePassenger.getAccountId());
//                event.getEventHasActions().add(eventHasAction);
//
//                eventHasActionDAO.edit(eventHasAction);
//            }
//            eventDAO.edit(event);
//
//
//            // Add booking
//            // Check if booking already exists for user
//            List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByEventAndAccount(event, ridePassenger.getAccountId());
//            if (eventHasBookings.isEmpty()) {
//                Booking booking = new Booking();
//                booking.setUid(TokenUtils.generateUid());
//                booking.setWithTicket(false);
//                booking.setCreated(new Date());
//                booking.setVisible(true);
//                bookingDAO.create(booking);
//
//                EventHasBooking eventHasBooking = new EventHasBooking();
//                eventHasBooking.setId(new EventHasBookingId(booking.getId(), event.getId()));
//                eventHasBooking.setCreated(new Date());
//                eventHasBooking.setModified(new Date());
//                eventHasBooking.setBooking(booking);
//                eventHasBooking.setAccountid(ridePassenger.getAccountId());
//                eventHasBooking.setEvent(event);
//                eventHasBookingDAO.edit(eventHasBooking);
//
//                booking.getEventHasBookings().add(eventHasBooking);
//                booking.setModified(new Date());
//                bookingDAO.edit(booking);
//            }
//
//            eventController.updateEventInterestAfterChanges(ridePassenger.getAccountId(), eventId);
//        }
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent postInsertEvent) {
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return true;
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent postUpdateEvent) {
    }

    @Override
    public void onPostDelete(PostDeleteEvent event) {
        process(event.getEntity(), null, null, event);
    }
}
