/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.notification.controllers;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.mailer.MailerController;
import com.justbit.pusher.PusherEvent;
import com.justbit.pusher.PusherUtils;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.modules.commons.ds.daos.NotificationDAO;
import com.stagedriving.modules.commons.ds.daos.NotificationRecipientDAO;
import com.stagedriving.modules.commons.ds.entities.Notification;
import com.stagedriving.modules.commons.ds.entities.NotificationMeta;
import com.stagedriving.modules.commons.ds.entities.NotificationRecipient;
import com.stagedriving.modules.notification.controllers.model.NotificationSchedulerEvent;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author simone
 */
@Slf4j
@Singleton
public class NotificationShedulerService extends SqueServiceAbstract<NotificationSchedulerEvent, Boolean> {

    @Inject
    private NotificationDAO notificationDAO;
    @Inject
    private NotificationRecipientDAO notificationRecipientDAO;
    @Inject
    private MailerController mailerController;
    @Inject
    private PusherUtils pusherUtils;

    @Inject
    public NotificationShedulerService(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(NotificationSchedulerEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, NotificationSchedulerEvent item) throws Exception {

        Notification notification = notificationDAO.findByUid(item.getId());
        if (notification == null) {
            return null;
        }

        log.info("Sending notification " + notification.getUid());

        for (NotificationRecipient notificationRecipient : notification.getNotificationRecipients()) {
            sendNotification(notificationRecipient);
        }

        notification.setStatus(NotificationController.NotificationStatusses.SENT.toString());

        notificationDAO.edit(notification);

//        WorkerMessage msg = new WorkerMessage();
//        msg.setId(notification.getUid());
//        RabbitMQBundle.rabbitMQBundle.getQueueFactory("notifs", null, null, "Notifs", WorkerMessage.class).provide().publish(msg);

        return null;
    }

    private void sendNotification(NotificationRecipient notificationRecipient) {
        if (notificationRecipient.getType().equals(NotificationController.NotificationRecipientTypes.EMAIL.toString())) {
            // Send email
            log.info("Sending email notification to "+notificationRecipient.getDest());
            if (notificationRecipient.getNotification().getDescription() == null) {
                notificationRecipient.setStatus(NotificationController.NotificationRecipientStatusses.FAILED.toString());
                notificationRecipientDAO.edit(notificationRecipient);
                return;
            }
            mailerController.sendEmail(notificationRecipient.getUid(), notificationRecipient.getDest(), notificationRecipient.getNotification().getContent(), notificationRecipient.getNotification().getDescription());
            notificationRecipientDAO.edit(notificationRecipient);
        } else if (notificationRecipient.getType().equals(NotificationController.NotificationRecipientTypes.FCM.toString())) {
            // Send fcm
            log.info("Sending fcm notification to "+notificationRecipient.getDest());
            PusherEvent pusherEvent = new PusherEvent();
            pusherEvent.setOsType(notificationRecipient.getOs());
            if (pusherEvent.getOsType() == null) {
                pusherEvent.setOsType("ios");
            }
            pusherEvent.setId(notificationRecipient.getUid());
            pusherEvent.setTitle(notificationRecipient.getNotification().getName());
            pusherEvent.setBody(notificationRecipient.getNotification().getDescription());
            pusherEvent.setTo(notificationRecipient.getDest());
            Map<String, String> data = new HashMap<>();
            for (NotificationMeta notificationMeta : notificationRecipient.getNotification().getNotificationMetas()) {
                data.put(notificationMeta.getMetaKey(), notificationMeta.getMetaValue());
            }
            pusherEvent.setMeta(data);
            pusherUtils.sendFcmPushTo(pusherEvent);
        }
    }

    @Override
    protected void onPostExecute(Boolean item) {

    }
}
