package com.stagedriving.modules.commons.dispatcher;

import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.joda.time.DateTime;

@Slf4j
public class GlobalEventListener extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener {

    @Inject
    private GlobalEventController globalEventController;

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        process(postUpdateEvent.getEntity(), null, postUpdateEvent);
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return true;
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent event) {

    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent event) {

    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        process(postInsertEvent.getEntity(), postInsertEvent, null);
    }

    private void process(Object object, PostInsertEvent postInsertEvent, PostUpdateEvent postUpdateEvent) {
        try {
            if (object instanceof Event) {
                Event event = (Event) object;

                if (event.getStatus().equals(StgdrvData.EventStatus.DELETED)) {
                    globalEventController.unscheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_STARTED);
                    globalEventController.unscheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_FINISHED);
                    globalEventController.scheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_CANCELLED, null);
                } else {
                    globalEventController.scheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_STARTED, new DateTime(event.getStart()).minusHours(2).toDate());
                    globalEventController.scheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_FINISHED, new DateTime(event.getFinish()).minusMinutes(30).toDate());
                    globalEventController.unscheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_CANCELLED);
                }
            } else if (object instanceof Ride/* && checkChanges(postUpdateEvent, "status", "goingDepartureDate", "goingArrivalDate", "returnDepartureDate", "returnArrivalDate")*/) {
                Ride ride = (Ride) object;

                if (ride.getStatus() != null && ride.getStatus().equals(StgdrvData.EventStatus.DELETED)) {
                    globalEventController.unscheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.RIDE_STARTED);
                    globalEventController.unscheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.RIDE_FINISHED);
                    globalEventController.unscheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.EVENT_NEW_RIDE);
                    globalEventController.scheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.RIDE_CANCELLED, DateTime.now().plusSeconds(60).toDate());
                } else {
                    globalEventController.unscheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.RIDE_CANCELLED);
                    globalEventController.scheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.RIDE_STARTED, new DateTime(ride.getGoingDepartureDate()).minusHours(1).toDate());

                    globalEventController.unscheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.EVENT_NEW_RIDE);
                    globalEventController.scheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.EVENT_NEW_RIDE, DateTime.now().plusMinutes(5).toDate());
                    if (ride.getReturnArrivalDate() != null) {
                        globalEventController.scheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.RIDE_FINISHED, new DateTime(ride.getReturnArrivalDate()).plusHours(1).toDate());
                    } else {
                        globalEventController.scheduleEvent(ride.getUid(), GlobalEventController.GlobalEventTypes.RIDE_FINISHED, new DateTime(ride.getGoingArrivalDate()).plusHours(1).toDate());
                    }

                }
            } else if (object instanceof RidePassenger) {
                RidePassenger ridePassenger = (RidePassenger) object;

                if (ridePassenger.getStatus() != null && ridePassenger.getStatus().equals(StgdrvData.EventStatus.DELETED)) {
                    globalEventController.unscheduleEvent(ridePassenger.getUid(), GlobalEventController.GlobalEventTypes.RIDE_NEW_PASSENGER);
                    globalEventController.scheduleEvent(ridePassenger.getUid(), GlobalEventController.GlobalEventTypes.RIDE_CANCEL_PASSENGER, DateTime.now().plusSeconds(60).toDate());
                } else {
                    globalEventController.unscheduleEvent(ridePassenger.getUid(), GlobalEventController.GlobalEventTypes.RIDE_CANCEL_PASSENGER);
                    globalEventController.scheduleEvent(ridePassenger.getUid(), GlobalEventController.GlobalEventTypes.RIDE_NEW_PASSENGER, DateTime.now().plusSeconds(60).toDate());
                }
            } else if (object instanceof EventHasAction) {
                EventHasAction eventAction = (EventHasAction) object;

                if (eventAction.getTaxonomy().equals("comment")) {

                    Event event = eventAction.getEvent();

                    if (eventAction.getAccountid() == event.getAccountid()) {
                        // If is organizer send notification to all users

                        if (eventAction.getStatus() != null && eventAction.getStatus().equals(StgdrvData.EventStatus.DELETED)) {
                            globalEventController.unscheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_NEW_ORG_COMMENT);
                        } else {
                            globalEventController.scheduleEvent(event.getUid(), GlobalEventController.GlobalEventTypes.EVENT_NEW_ORG_COMMENT, null);
                        }
                    }
                }
            } else if (object instanceof EventHasBooking) {
                EventHasBooking eventBooking = (EventHasBooking) object;

                if (eventBooking.getBooking().getVisible() != null && eventBooking.getBooking().getVisible()) {

                    Event event = eventBooking.getEvent();
                    globalEventController.scheduleEvent(eventBooking.getBooking().getUid(), GlobalEventController.GlobalEventTypes.EVENT_FRIEND_JOIN,  DateTime.now().plusSeconds(60).toDate());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Oops", e);
        }
    }
}
