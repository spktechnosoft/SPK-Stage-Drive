/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.dispatcher;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.dispatcher.model.GlobalEvent;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.event.views.*;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.notification.controllers.model.NotificationRecipientHolder;
import com.stagedriving.modules.ride.views.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author simone
 */
@Singleton
@Slf4j
public class GlobalEventService extends SqueServiceAbstract<GlobalEvent, Boolean> {

    @Inject
    private SqueController squeController;
    @Inject
    private JedisPool jedisPool;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private RidePassengerDAO ridePassengerDAO;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private BookingDAO bookingDAO;
    @Inject
    private NotificationController notificationController;

    @Inject
    public GlobalEventService(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(GlobalEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, GlobalEvent item) throws Exception {

        if (item.getType().equals(GlobalEventController.GlobalEventTypes.EVENT_STARTED)) {
            // Send push notification to all users interested on event

            Event event = eventDAO.findByUid(item.getId());
            if (!StgdrvData.EventStatus.PUBLISHED.equals(event.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invisible event "+event.getUid());
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (EventHasBooking eventHasBooking : event.getEventHasBookings()) {
                int accountId = eventHasBooking.getAccountid();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            EventStartedPushNotificationView eventStartedPushNotificationView = new EventStartedPushNotificationView();
            eventStartedPushNotificationView.setTitle("Sta iniziando " + event.getName());
            eventStartedPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.EVENT, event.getEventImages().get(0).getImage(), event.getUid());

            notificationController.sendPushNotification(devices, eventStartedPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.EVENT_FINISHED)) {
            // Send push notification to all users interested on event

            Event event = eventDAO.findByUid(item.getId());
            if (!StgdrvData.EventStatus.PUBLISHED.equals(event.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invisible event "+event.getUid());
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (EventHasBooking eventHasBooking : event.getEventHasBookings()) {
                int accountId = eventHasBooking.getAccountid();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            EventFinishedPushNotificationView eventFinishedPushNotificationView = new EventFinishedPushNotificationView();
            eventFinishedPushNotificationView.setTitle("L'evento " + event.getName() + " sta terminando");
            eventFinishedPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.EVENT, event.getEventImages().get(0).getImage(), event.getUid());

            notificationController.sendPushNotification(devices, eventFinishedPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.EVENT_CANCELLED)) {
            // Send push notification to all users interested on event

            Event event = eventDAO.findByUid(item.getId());
            if (!StgdrvData.EventStatus.PUBLISHED.equals(event.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invisible event "+event.getUid());
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (EventHasBooking eventHasBooking : event.getEventHasBookings()) {
                int accountId = eventHasBooking.getAccountid();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            EventCancelledPushNotificationView eventCancelledPushNotificationView = new EventCancelledPushNotificationView();
            eventCancelledPushNotificationView.setTitle(event.getName());
            eventCancelledPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.EVENT, event.getEventImages().get(0).getImage(), event.getUid());

            notificationController.sendPushNotification(devices, eventCancelledPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.EVENT_NEW_RIDE)) {
            // Send push notification to all users interested on event

            Ride ride = rideDAO.findByUid(item.getId());
            if (ride == null) {
                squeController.logWarning(item.getJobId(), "Skipping ride id "+item.getId());
                return null;
            }
            if (StgdrvData.RideStatusses.DELETED.equals(ride.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invalid ride "+ride.getUid());
                return null;
            }

            Event event = eventDAO.findByUid(ride.getEventid());
            if (ride.getFromEventId() != null) {
                event = eventDAO.findByUid(ride.getFromEventId());
            } else if (ride.getToEventId() != null) {
                event = eventDAO.findByUid(ride.getToEventId());
            }
            if (event == null) {
                squeController.logInfo(item.getJobId(), "Skipping ride "+ride.getUid());
                return null;
            }

            if (!StgdrvData.EventStatus.PUBLISHED.equals(event.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invisible event "+event.getUid());
                return null;
            }
            if (event.getFinish().before(new Date())) {
                squeController.logInfo(item.getJobId(), "Skipping finished event "+event.getUid());
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (EventHasBooking eventHasBooking : event.getEventHasBookings()) {
                int accountId = eventHasBooking.getAccountid();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            EventNewRidePushNotificationView eventCancelledPushNotificationView = new EventNewRidePushNotificationView();
            eventCancelledPushNotificationView.setTitle("Nuovo passaggio pubblicato");
            eventCancelledPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.EVENT, event.getEventImages().get(0).getImage(), event.getUid(), ride.getUid());

            notificationController.sendPushNotification(devices, eventCancelledPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.RIDE_STARTED)) {
            // Send push notification to all users of ride

            Ride ride = rideDAO.findByUid(item.getId());
            if (ride == null) {
                squeController.logWarning(item.getJobId(), "Skipping ride id "+item.getId());
                return null;
            }
            if (StgdrvData.RideStatusses.DELETED.equals(ride.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invalid ride "+ride.getUid());
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (RidePassenger ridePassenger : ride.getRidePassengers()) {
                if (ridePassenger.getStatus() != null && ridePassenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                    continue;
                }
                int accountId = ridePassenger.getAccountId();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            Account driver = accountDAO.findByUid(ride.getAccountid());
            if (driver != null) {
                List<AccountDevice> deviceList = driver.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

//            Event event = eventDAO.findByUid(ride.getEventid());

            RideStartedPushNotificationView rideStartedPushNotificationView = new RideStartedPushNotificationView();
            rideStartedPushNotificationView.setTitle("E' ora di partire");
            rideStartedPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.RIDE, null, ride.getUid());

            notificationController.sendPushNotification(devices, rideStartedPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.RIDE_FINISHED)) {
            // Send push notification to all users of ride

            Ride ride = rideDAO.findByUid(item.getId());
            if (ride == null) {
                squeController.logWarning(item.getJobId(), "Skipping ride id "+item.getId());
                return null;
            }
            if (StgdrvData.RideStatusses.DELETED.equals(ride.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invalid ride "+ride.getUid());
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (RidePassenger ridePassenger : ride.getRidePassengers()) {
                if (ridePassenger.getStatus() != null && ridePassenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                    continue;
                }
                int accountId = ridePassenger.getAccountId();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            Account driver = accountDAO.findByUid(ride.getAccountid());
            if (driver != null) {
                List<AccountDevice> deviceList = driver.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

//            Event event = eventDAO.findByUid(ride.getEventid());

            RideFinishedPushNotificationView rideFinishedPushNotificationView = new RideFinishedPushNotificationView();
            rideFinishedPushNotificationView.setTitle("Arrivati! Ti sei divertito?");
            rideFinishedPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.RIDE, null, ride.getUid());

            notificationController.sendPushNotification(devices, rideFinishedPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.RIDE_CANCELLED)) {
            // Send push notification to all users of ride

            Ride ride = rideDAO.findByUid(item.getId());
            if (!StgdrvData.RideStatusses.DELETED.equals(ride.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invalid ride "+ride.getUid());
                return null;
            }

            Event event = null;
            if (ride.getFromEventId() != null) {
                event = eventDAO.findByUid(ride.getFromEventId());
            } else if (ride.getToEventId() != null) {
                event = eventDAO.findByUid(ride.getToEventId());
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (RidePassenger ridePassenger : ride.getRidePassengers()) {
                if (ridePassenger.getStatus() != null && ridePassenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                    continue;
                }
                int accountId = ridePassenger.getAccountId();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            Account driver = accountDAO.findByUid(ride.getAccountid());
//            if (driver != null) {
//                List<AccountDevice> deviceList = driver.getAccountDevices();
//                for (AccountDevice accountDevice : deviceList) {
//                    if (accountDevice.getDeviceid() != null) {
//                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
//                    }
//                }
//            }

            RideCancelledPushNotificationView rideCancelledPushNotificationView = new RideCancelledPushNotificationView();
            rideCancelledPushNotificationView.setTitle("Passaggio cancellato");
            rideCancelledPushNotificationView.setName(driver.getFirstname()+" "+driver.getLastname());
            rideCancelledPushNotificationView.setEventName(event.getName());
            rideCancelledPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.RIDE, null, null);

            notificationController.sendPushNotification(devices, rideCancelledPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.RIDE_NEW_PASSENGER)) {
            // Send push notification to all users of ride

            RidePassenger ridePassenger = ridePassengerDAO.findByUid(item.getId());

            List<NotificationRecipientHolder> devices = new ArrayList<>();

            List<RidePassenger> ridePassengers = ridePassenger.getRide().getRidePassengers();
            for (RidePassenger ridePass : ridePassengers) {
                if (ridePass == ridePassenger) {
                    continue;
                }
                if (ridePass.getStatus() != null && ridePass.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                    continue;
                }
                Account riderAccount = accountDAO.findById(ridePass.getAccountId());
                List<AccountDevice> deviceList = riderAccount.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            Account driver = accountDAO.findByUid(ridePassenger.getRide().getAccountid());
            if (driver != null) {
                List<AccountDevice> deviceList2 = driver.getAccountDevices();
                for (AccountDevice accountDevice : deviceList2) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            RideNewPassengerPushNotificationView rideNewPassengerPushNotificationView = new RideNewPassengerPushNotificationView();
            rideNewPassengerPushNotificationView.setTitle("Passaggio");
            rideNewPassengerPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.RIDE, null, ridePassenger.getRide().getUid());

            notificationController.sendPushNotification(devices, rideNewPassengerPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.RIDE_CANCEL_PASSENGER)) {
            // Send push notification to all users of ride

            RidePassenger ridePassenger = ridePassengerDAO.findByUid(item.getId());

            List<NotificationRecipientHolder> devices = new ArrayList<>();

            List<RidePassenger> ridePassengers = ridePassenger.getRide().getRidePassengers();
            for (RidePassenger ridePass : ridePassengers) {
                if (ridePass == ridePassenger) {
                    continue;
                }
                if (ridePass.getStatus() != null && ridePass.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                    continue;
                }
                Account riderAccount = accountDAO.findById(ridePass.getAccountId());
                List<AccountDevice> deviceList = riderAccount.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            Account driver = accountDAO.findByUid(ridePassenger.getRide().getAccountid());
            if (driver != null) {
                List<AccountDevice> deviceList2 = driver.getAccountDevices();
                for (AccountDevice accountDevice : deviceList2) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            RideCancelPassengerPushNotificationView rideCancelPassengerPushNotificationView = new RideCancelPassengerPushNotificationView();
            rideCancelPassengerPushNotificationView.setTitle("Passaggio");
            rideCancelPassengerPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.RIDE, null, ridePassenger.getRide().getUid());

            notificationController.sendPushNotification(devices, rideCancelPassengerPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.EVENT_FRIEND_JOIN)) {
            // Send push notification to all friends of user

            Booking booking = bookingDAO.findByUid(item.getId());

            if (booking == null || booking.getEventHasBookings().isEmpty()) {
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();

            EventHasBooking eventHasBooking = booking.getEventHasBookings().get(0);
            int accountId = eventHasBooking.getAccountid();
            Account account = accountDAO.findById(accountId);

//            List<AccountDevice> deviceList = account.getAccountDevices();
//            for (AccountDevice accountDevice : deviceList) {
//                if (accountDevice.getDeviceid() != null) {
//                    devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
//                }
//            }

            List<AccountFriendship> friendships = account.getAccountFriendshipsForAccountIdFrom();
            for (AccountFriendship friendship : friendships) {
                List<AccountDevice> deviceList2 = friendship.getAccountByAccountIdTo().getAccountDevices();
                for (AccountDevice accountDevice : deviceList2) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            EventFriendJoinPushNotificationView eventFriendJoinPushNotificationView = new EventFriendJoinPushNotificationView();
            eventFriendJoinPushNotificationView.setTitle(eventHasBooking.getEvent().getName());
            eventFriendJoinPushNotificationView.setName(account.getFirstname()+" "+account.getLastname());
            eventFriendJoinPushNotificationView.setEvent(eventHasBooking.getEvent().getName());
            eventFriendJoinPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.EVENT, null, eventHasBooking.getEvent().getUid());

            notificationController.sendPushNotification(devices, eventFriendJoinPushNotificationView, null);
        } else if (item.getType().equals(GlobalEventController.GlobalEventTypes.EVENT_NEW_ORG_COMMENT)) {
            // Send push notification to all users of event

            Event event = eventDAO.findByUid(item.getId());
            if (!StgdrvData.EventStatus.PUBLISHED.equals(event.getStatus())) {
                squeController.logInfo(item.getJobId(), "Skipping invisible event "+event.getUid());
                return null;
            }

            List<NotificationRecipientHolder> devices = new ArrayList<>();
            for (EventHasInterest eventHasInterest : event.getEventHasInterests()) {
                int accountId = eventHasInterest.getAccountId();
                Account account = accountDAO.findById(accountId);

                List<AccountDevice> deviceList = account.getAccountDevices();
                for (AccountDevice accountDevice : deviceList) {
                    if (accountDevice.getDeviceid() != null) {
                        devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                    }
                }
            }

            EventNewOrgCommentPushNotificationView eventNewOrgCommentPushNotificationView = new EventNewOrgCommentPushNotificationView();
            eventNewOrgCommentPushNotificationView.setTitle(event.getName());
            eventNewOrgCommentPushNotificationView.fillData(item.getType(), GlobalEventController.GlobalEventCategories.EVENT, event.getEventImages().get(0).getImage(), event.getUid());

            notificationController.sendPushNotification(devices, eventNewOrgCommentPushNotificationView, null);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean item) {

    }
}
