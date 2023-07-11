/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.batches;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.event.batches.model.EventProcessorBatchEvent;
import com.stagedriving.modules.event.controllers.EventController;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author simone
 */
@Singleton
@Slf4j
public class EventProcessorBatchService extends SqueServiceAbstract<EventProcessorBatchEvent, Boolean> {

    @Inject
    private SqueController squeController;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private EventController eventController;

    @Inject
    public EventProcessorBatchService(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        this.withoutTransaction = true;
        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(EventProcessorBatchEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, EventProcessorBatchEvent item) throws Exception {

        squeController.logInfo(item.getJobId(),"Processing events");

        int page = 0;
        int limit = 10;
        List<Event> events = eventDAO.findByFilters(true, page, limit, null, null, null, null, null, null, null, -1, null, null, null, null, null, null, null, null);
        while (events.size() > 0) {

            for (Event event : events) {
                processEvent(event, item);
            }

            page++;
            events = eventDAO.findByFilters(true, page, limit, null, null, null, null, null, null, null, -1, null, null, null, null, null, null, null, null);
        }

        return null;
    }

    private void processEvent(Event event, EventProcessorBatchEvent item) {
        squeController.logInfo(item.getJobId(),"Updating event "+event.getUid()+" data");
        eventController.updateEventDataAfterChanges(event.getUid());
    }

    @Override
    protected void onPostExecute(Boolean item) {

    }
}
