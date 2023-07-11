package com.stagedriving.modules.commons.service.model;

import com.google.common.eventbus.EventBus;
import com.google.inject.Singleton;

/**
 * Created by man on 25/04/16.
 */
@Singleton
public class StgdrvSyncEventBus {
    public static final String EB_NAME = StgdrvSyncEventBus.class.toString();
    private EventBus eventBus;

    public StgdrvSyncEventBus() {
        eventBus = new EventBus(EB_NAME);
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void registerReceiver(Object receiver) {
        this.eventBus.register(receiver);
    }

    public void post(Object event) {
        this.eventBus.post(event);
    }
}
