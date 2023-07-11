package com.stagedriving.modules.notification.sender;

import com.google.inject.Inject;
import com.justbit.mailer.MailerController;
import com.justbit.pusher.PusherEvent;
import com.justbit.pusher.PusherUtils;
import com.stagedriving.modules.commons.ds.daos.NotificationDAO;
import com.stagedriving.modules.commons.ds.daos.NotificationRecipientDAO;
import com.stagedriving.modules.commons.ds.entities.Notification;
import com.stagedriving.modules.commons.ds.entities.NotificationMeta;
import com.stagedriving.modules.commons.ds.entities.NotificationRecipient;
import com.stagedriving.modules.commons.queue.QueueWorker;
import com.stagedriving.modules.commons.queue.WorkerMessage;
import com.stagedriving.modules.notification.controllers.NotificationController;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class NotificationSenderWorker extends QueueWorker<WorkerMessage> {

    enum Params {

    }

    @Inject
    private NotificationDAO notificationDAO;
    @Inject
    private NotificationRecipientDAO notificationRecipientDAO;
    @Inject
    private MailerController mailerController;
    @Inject
    private PusherUtils pusherUtils;

    @Inject
    public NotificationSenderWorker() {
        super();

        this.withoutTransaction = true;
    }

    @Override
    protected String process(WorkerMessage message) throws Exception {
        String notificationId = message.getId();
        log.info("Sending notification " + notificationId);

        Notification notification = notificationDAO.findByUid(notificationId);

        for (NotificationRecipient notificationRecipient : notification.getNotificationRecipients()) {
            sendNotification(notificationRecipient);
        }

        notification.setStatus(NotificationController.NotificationStatusses.SENT.toString());

        notificationDAO.edit(notification);

        return "ok";
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
}
