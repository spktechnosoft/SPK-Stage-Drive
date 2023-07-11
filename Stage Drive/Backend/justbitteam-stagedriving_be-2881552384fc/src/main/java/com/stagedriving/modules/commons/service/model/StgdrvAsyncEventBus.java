package com.stagedriving.modules.commons.service.model;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.Singleton;

import java.util.concurrent.Executors;

/**
 * Created by man on 25/04/16.
 */
@Singleton
public class StgdrvAsyncEventBus {

    public static final String EB_NAME = StgdrvAsyncEventBus.class.toString();

    private EventBus eventBus;

    public StgdrvAsyncEventBus() {
        eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
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
