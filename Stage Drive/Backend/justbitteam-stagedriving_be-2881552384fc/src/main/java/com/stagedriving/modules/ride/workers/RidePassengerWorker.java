package com.stagedriving.modules.ride.workers;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasBooking;
import com.stagedriving.modules.commons.ds.entities.RidePassenger;
import com.stagedriving.modules.event.controllers.EventController;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.ride.workers.model.RidePassengerWorkerEvent;
import com.stagedriving.modules.user.controller.FriendshipController;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RidePassengerWorker extends SqueServiceAbstract<RidePassengerWorkerEvent, Boolean> {

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private RidePassengerDAO ridePassengerDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private NotificationController notificationController;
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
    public RidePassengerWorker(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(RidePassengerWorkerEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, RidePassengerWorkerEvent item) throws Exception {
        RidePassenger ridePassenger = ridePassengerDAO.findByUid(item.getId());
        log.info("Processing ride passenger "+ridePassenger.getUid());

        Event event = null;
        if (ridePassenger.getRide().getFromEventId() != null) {
            event = eventDAO.findByUid(ridePassenger.getRide().getFromEventId());
        } else if (ridePassenger.getRide().getToEventId() != null) {
            event = eventDAO.findByUid(ridePassenger.getRide().getToEventId());
        }

        Account driver = accountDAO.findByUid(ridePassenger.getRide().getAccountid());
        Account passgr = accountDAO.findById(ridePassenger.getAccountId());

        if (event != null) {

            Boolean withTickets = false;
            Boolean withBookings = false;

            for (RidePassenger passenger : ridePassenger.getRide().getRidePassengers()) {
                if (passenger.getStatus() != null && passenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                    continue;
                }
                List<EventHasBooking> eventHasBookings = eventHasBookingDAO.findByEventAndAccount(event, passenger.getAccountId());
                if (!eventHasBookings.isEmpty()) {
                    EventHasBooking eventHasBooking = eventHasBookings.get(0);
                    if (eventHasBooking.getBooking().getWithTicket() != null && eventHasBooking.getBooking().getWithTicket()) {
                        withTickets = true;
                    }
                    withBookings = true;
                }
            }

            ridePassenger.getRide().setWithTickets(withTickets);
            ridePassenger.getRide().setWithBookings(withBookings);

            ridePassengerDAO.edit(ridePassenger);

            eventController.updateEventDataAfterChanges(event.getUid());

            List<Account> users = new ArrayList<>();
            users.add(driver);
            for (RidePassenger rp : ridePassenger.getRide().getRidePassengers()) {
                Account user = accountDAO.findById(rp.getAccountId());
                if (user != null) {
                    users.add(user);
                }
            }
            friendshipController.addFriends(users, passgr, true);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

    }
}
