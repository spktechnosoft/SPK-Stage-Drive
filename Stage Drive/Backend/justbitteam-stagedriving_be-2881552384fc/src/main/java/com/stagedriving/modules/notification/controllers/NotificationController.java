package com.stagedriving.modules.notification.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.commons.TokenUtils;
import com.justbit.sque.SqueController;
import com.justbit.sque.ds.entities.SqueJob;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.NotificationDAO;
import com.stagedriving.modules.commons.ds.daos.NotificationRecipientDAO;
import com.stagedriving.modules.commons.ds.entities.Notification;
import com.stagedriving.modules.commons.ds.entities.NotificationMeta;
import com.stagedriving.modules.commons.ds.entities.NotificationRecipient;
import com.stagedriving.modules.notification.controllers.model.NotificationRecipientHolder;
import com.stagedriving.modules.notification.controllers.model.NotificationSchedulerEvent;
import com.stagedriving.modules.notification.views.EmailNotificationView;
import com.stagedriving.modules.notification.views.PushNotificationView;
import io.dropwizard.views.View;
import io.dropwizard.views.freemarker.FreemarkerViewRenderer;
import org.joda.time.DateTime;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Singleton
public class NotificationController {

    private static final String QUEUE = "notifications";

    public enum NotificationTypes {
        ALL,
        EMAIL,
        FCM
    }

    public enum NotificationRecipientTypes {
        EMAIL,
        FCM
    }

    public enum NotificationRecipientStatusses {
        PENDING,
        SENT,
        DELIVERED,
        FAILED
    }

    public enum NotificationStatusses {
        PENDING,
        SENT,
        FAILED
    }

    @Inject
    private SqueController squeController;
    @Inject
    private NotificationDAO notificationDAO;
    @Inject
    private NotificationRecipientDAO notificationRecipientDAO;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private FreemarkerViewRenderer renderer;

    public String getHTMLFromView(View v, String language) throws Exception {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        if (language == null) {
            language = Locale.ITALIAN.getLanguage();//ScxData.Languages.ITALIAN
        }

        Locale lang = new Locale(language);

       if (v.getTemplateName().endsWith(".ftl")) {
            renderer.render(v, lang, output);
        } else {
            throw new Exception("missing template");
        }

        return output.toString("utf-8");
    }

    public Notification sendEmailNotification(String from, List<String> recipients, EmailNotificationView emailNotificationView) throws Exception {
        return sendEmailNotification(from, recipients, emailNotificationView, null);
    }

    public Notification sendEmailNotification(String from, List<String> recipients, EmailNotificationView emailNotificationView, Date when) throws Exception {
        Notification notification = new Notification();
        notification.setUid(TokenUtils.generateUid());
        notification.setSender(from);
        notification.setType(String.valueOf(NotificationTypes.EMAIL));
        notification.setDescription(emailNotificationView.getSubject());
        notification.setContent(getHTMLFromView(emailNotificationView, null));
        notification.setStatus(NotificationStatusses.PENDING.toString());
        notification.setScope("transactional");
        notification.setWhenSend(when);
        notification.setCreated(new Date());

        notificationDAO.create(notification);

        for (String recipientAddress : recipients) {
            NotificationRecipient recipient = new NotificationRecipient();
            recipient.setUid(TokenUtils.generateUid());
            recipient.setCreated(new Date());
            recipient.setModified(new Date());
            recipient.setDest(recipientAddress);
            recipient.setType(String.valueOf(NotificationRecipientTypes.EMAIL));
            recipient.setStatus(NotificationRecipientStatusses.PENDING.toString());
            recipient.setNotification(notification);
            notification.getNotificationRecipients().add(recipient);

            notificationRecipientDAO.create(recipient);
        }

        NotificationSchedulerEvent schedulerEvent = new NotificationSchedulerEvent();
        schedulerEvent.setId(notification.getUid());
        SqueJob job = null;
        if (when != null) {
            job = squeController.enqueueScheduled(schedulerEvent, QUEUE, null, when);
        } else {
            job = squeController.enqueueScheduled(schedulerEvent, QUEUE, null, DateTime.now().plusSeconds(6).toDate());
        }

        notification.setJobId(job.getId());

        notification.setModified(new Date());
        notificationDAO.edit(notification);

        return notification;
    }

//    public Notification sendFcmNotification(List<String> recipients, String title, String body, Map<String, String> data) throws Exception {
//        Notification notification = new Notification();
//        notification.setUid(TokenUtils.generateUid());
//        notification.setSender("SD");
//        notification.setType(String.valueOf(NotificationTypes.FCM));
//        notification.setDescription(title);
//        notification.setContent("");
//        notification.setStatus(NotificationStatusses.PENDING.toString());
//        notification.setScope("transactional");
//        notification.setCreated(new Date());
//
//        notificationDAO.create(notification);
//
//        for (String recipientAddress : recipients) {
//            NotificationRecipient recipient = new NotificationRecipient();
//            recipient.setUid(TokenUtils.generateUid());
//            recipient.setCreated(new Date());
//            recipient.setModified(new Date());
//            recipient.setDest(recipientAddress);
//            recipient.setType(String.valueOf(NotificationRecipientTypes.FCM));
//            recipient.setStatus(NotificationRecipientStatusses.PENDING.toString());
//            recipient.setNotification(notification);
//            notification.getNotificationRecipients().add(recipient);
//
//            notificationRecipientDAO.create(recipient);
//        }
//
//        notification.setModified(new Date());
//        notificationDAO.edit(notification);
//
//        return notification;
//    }

    public Notification sendPushNotification(List<NotificationRecipientHolder> recipients, PushNotificationView pushNotificationView, Date when) throws Exception {
        Notification notification = new Notification();
        notification.setUid(TokenUtils.generateUid());
        notification.setSender("SD");
        notification.setType(String.valueOf(NotificationTypes.FCM));
        notification.setName(pushNotificationView.getTitle());
        notification.setDescription(getHTMLFromView(pushNotificationView, null));
        notification.setContent("");
        notification.setStatus(NotificationStatusses.PENDING.toString());
        notification.setScope("transactional");
        notification.setWhenSend(when);
        notification.setCreated(new Date());

        if (pushNotificationView.getData() != null) {
            for (Map.Entry<String, String> entry : pushNotificationView.getData().entrySet())
            {
                NotificationMeta notificationMeta = new NotificationMeta();
                notificationMeta.setMetaKey(entry.getKey());
                notificationMeta.setMetaValue(entry.getValue());
                notificationMeta.setCreated(new Date());
                notificationMeta.setModified(new Date());
                notificationMeta.setUid(TokenUtils.generateUid());
                notificationMeta.setNotification(notification);

                notification.getNotificationMetas().add(notificationMeta);
            }
        }

        notificationDAO.create(notification);

        for (NotificationRecipientHolder recipientHolder : recipients) {
            NotificationRecipient recipient = new NotificationRecipient();
            recipient.setUid(TokenUtils.generateUid());
            recipient.setCreated(new Date());
            recipient.setModified(new Date());
            recipient.setDest(recipientHolder.getId());
            recipient.setType(String.valueOf(NotificationRecipientTypes.FCM));
            recipient.setOs(recipientHolder.getOs());
            recipient.setStatus(NotificationRecipientStatusses.PENDING.toString());
            recipient.setNotification(notification);
            notification.getNotificationRecipients().add(recipient);

            notificationRecipientDAO.create(recipient);
        }

        NotificationSchedulerEvent schedulerEvent = new NotificationSchedulerEvent();
        schedulerEvent.setId(notification.getUid());
        SqueJob job = null;
        if (when != null) {
            job = squeController.enqueueScheduled(schedulerEvent, QUEUE, null, when);
        } else {
            job = squeController.enqueueScheduled(schedulerEvent, QUEUE, null, DateTime.now().plusSeconds(6).toDate());
        }

        notification.setJobId(job.getId());

        notification.setModified(new Date());
        notificationDAO.edit(notification);

        return notification;
    }

    /*private void enqueueNotification(Notification notification) {
        WorkerMessage msg = new WorkerMessage();
        msg.setId(notification.getUid());
        RabbitMQBundle.rabbitMQBundle.getQueue("emails", WorkerMessage.class).publish(msg);
    }*/
}
