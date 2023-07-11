package com.stagedriving.modules.event.workers;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.mapper.decorators.BookingMapperDecorator;
import com.stagedriving.modules.event.controllers.EventController;
import com.stagedriving.modules.event.workers.model.EventWorkerEvent;
import com.stagedriving.modules.notification.controllers.NotificationController;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

@Slf4j
public class EventWorker extends SqueServiceAbstract<EventWorkerEvent, Boolean> {


    @Inject
    private AccountDAO accountDAO;
    @Inject
    private RidePassengerDAO ridePassengerDAO;
    @Inject
    private NotificationController notificationController;
    @Inject
    private EventController eventController;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private EventHasActionDAO eventHasActionDAO;
    @Inject
    private EventHasBookingDAO eventHasBookingDAO;
    @Inject
    private BookingMapperDecorator bookingMapper;
    @Inject
    private BookingDAO bookingDAO;

    @Inject
    public EventWorker(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        this.withoutTransaction = false;
        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(EventWorkerEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, EventWorkerEvent eventWorkerEvent) throws Exception {

        if (eventWorkerEvent.getRidePassengerId() != null) {
            String ridePassengerId = eventWorkerEvent.getRidePassengerId();
            RidePassenger ridePassenger = ridePassengerDAO.findByUid(ridePassengerId);
            log.info("New ride passenger " + ridePassenger.getUid());

            String eventId = ridePassenger.getRide().getEventid();
            if (eventId == null) {
                eventId = ridePassenger.getRide().getFromEventId();
            }
            if (eventId == null) {
                eventId = ridePassenger.getRide().getToEventId();
            }
            Event event = eventDAO.findByUid(eventId);

            if (ridePassenger.getStatus() != null && ridePassenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                List<EventHasAction> rideActions = eventHasActionDAO.findByEventAndAccount(event, ridePassenger.getAccountId(), "ride");
                if (!rideActions.isEmpty()) {
                    EventHasAction eventHasAction = rideActions.get(0);
                    eventHasActionDAO.delete(eventHasAction);
                }
            } else {
                // Add ride action
                EventHasAction eventHasAction = new EventHasAction();
                eventHasAction.setUid(TokenUtils.generateUid());
                eventHasAction.setCreated(new Date());
                eventHasAction.setEvent(event);
                eventHasAction.setTaxonomy("ride");
                eventHasAction.setVisible(true);
                eventHasAction.setAccountid(ridePassenger.getAccountId());
                event.getEventHasActions().add(eventHasAction);

                eventHasActionDAO.edit(eventHasAction);
            }
            eventDAO.edit(event);


            // Add booking
            // Check if booking already exists for user
            List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByEventAndAccount(event, ridePassenger.getAccountId());
            if (eventHasBookings.isEmpty()) {
                Booking booking = new Booking();
                booking.setUid(TokenUtils.generateUid());
                booking.setWithTicket(false);
                booking.setCreated(new Date());
                booking.setVisible(true);
                bookingDAO.create(booking);

                EventHasBooking eventHasBooking = new EventHasBooking();
                eventHasBooking.setId(new EventHasBookingId(booking.getId(), event.getId()));
                eventHasBooking.setCreated(new Date());
                eventHasBooking.setModified(new Date());
                eventHasBooking.setBooking(booking);
                eventHasBooking.setAccountid(ridePassenger.getAccountId());
                eventHasBooking.setEvent(event);
                eventHasBookingDAO.edit(eventHasBooking);

                booking.getEventHasBookings().add(eventHasBooking);
                booking.setModified(new Date());
                bookingDAO.edit(booking);
            }

            eventController.updateEventInterestAfterChanges(ridePassenger.getAccountId(), eventId);
        } else if (eventWorkerEvent.getId() != null && eventWorkerEvent.getAccountId() != 0) {
            eventController.updateEventInterestAfterChanges(eventWorkerEvent.getAccountId(), eventWorkerEvent.getId());
        }

        return "ok";
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

    }
}
