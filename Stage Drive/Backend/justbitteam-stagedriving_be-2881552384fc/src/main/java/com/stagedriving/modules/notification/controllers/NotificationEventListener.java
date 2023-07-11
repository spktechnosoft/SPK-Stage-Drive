package com.stagedriving.modules.notification.controllers;

import com.google.inject.Inject;
import com.justbit.sque.SqueController;
import com.justbit.sque.ds.entities.SqueJob;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.daos.NotificationDAO;
import com.stagedriving.modules.commons.ds.entities.Notification;
import com.stagedriving.modules.notification.controllers.model.NotificationSchedulerEvent;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;

import java.util.Date;

@Slf4j
public class NotificationEventListener extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener {

    private static final String QUEUE = "notifications";

    @Inject
    private SqueController squeController;

    @Inject
    private NotificationDAO notificationDAO;

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        if (postUpdateEvent.getEntity() instanceof Notification) {
            Notification notification = (Notification) postUpdateEvent.getEntity();
            process(notification);
        }
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
        if (postInsertEvent.getEntity() instanceof Notification) {
            Notification notification = (Notification) postInsertEvent.getEntity();
            process(notification);
        }
    }

    private void process(Notification notification) {
        try {
            String id = notification.getUid();

            Date when = notification.getWhenSend();
            String jobId = notification.getJobId();
            if (jobId != null) {
                squeController.unschedule(jobId);
            }

            NotificationSchedulerEvent schedulerEvent = new NotificationSchedulerEvent();
            schedulerEvent.setId(id);

            SqueJob job = null;
            if (when != null) {
                job = squeController.enqueueScheduled(schedulerEvent, QUEUE, null, when);
            } else {
                job = squeController.enqueue(schedulerEvent, QUEUE, null);
            }

            notification.setJobId(job.getId());
            notificationDAO.edit(notification);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Oops", e);
        }
    }
}
