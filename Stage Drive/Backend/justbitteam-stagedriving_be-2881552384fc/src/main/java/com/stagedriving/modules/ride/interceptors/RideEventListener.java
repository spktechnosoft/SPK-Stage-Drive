package com.stagedriving.modules.ride.interceptors;

import com.google.inject.Inject;
import com.justbit.sque.SqueController;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.RidePassenger;
import com.stagedriving.modules.ride.workers.model.RidePassengerWorkerEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class RideEventListener extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener {

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private SqueController squeController;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        process(postInsertEvent.getEntity(), postInsertEvent, null);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        process(postUpdateEvent.getEntity(), null, postUpdateEvent);
    }

    @SneakyThrows
    private void process(Object object, PostInsertEvent postInsertEvent, PostUpdateEvent postUpdateEvent) {
        if (object instanceof RidePassenger && (postInsertEvent != null || postUpdateEvent != null)) {
            RidePassenger ridePassenger = (RidePassenger) object;

            RidePassengerWorkerEvent ridePassengerWorkerEvent = new RidePassengerWorkerEvent();
            ridePassengerWorkerEvent.setId(ridePassenger.getUid());
            squeController.enqueue(ridePassengerWorkerEvent, "commons", 3600);
        }
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
}
