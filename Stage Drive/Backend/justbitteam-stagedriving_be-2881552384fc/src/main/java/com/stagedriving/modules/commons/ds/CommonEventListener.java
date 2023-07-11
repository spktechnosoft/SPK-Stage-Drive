package com.stagedriving.modules.commons.ds;

import com.stagedriving.modules.commons.queue.WorkerMessage;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class CommonEventListener extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener {


    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        Object obj = getValue(postUpdateEvent, "uid");

        if (obj instanceof String) {
            String id = (String) obj;
            log.info("Entity: " + postUpdateEvent.getEntity().getClass().getSimpleName());

            WorkerMessage msg = new WorkerMessage();
            msg.setId(id);
            msg.setType(postUpdateEvent.getEntity().getClass().getName());

            String key = postUpdateEvent.getEntity().getClass().getSimpleName() + ".updated";

//            RabbitMQBundle.rabbitMQBundle.getQueueFactory("models", null, null, key, WorkerMessage.class).provide().publishDelayed(msg, 3000);
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
        Object obj = getValue(postInsertEvent, "uid");

        if (obj instanceof String) {
            String id = (String) obj;
            log.info("Entity: " + postInsertEvent.getEntity().getClass().getSimpleName());

            WorkerMessage msg = new WorkerMessage();
            msg.setId(id);
            msg.setType(postInsertEvent.getEntity().getClass().getName());

            String key = postInsertEvent.getEntity().getClass().getSimpleName() + ".created";

//            RabbitMQBundle.rabbitMQBundle.getQueueFactory("models", null, null, key, WorkerMessage.class).provide().publishDelayed(msg, 3000);
        }
    }
}
