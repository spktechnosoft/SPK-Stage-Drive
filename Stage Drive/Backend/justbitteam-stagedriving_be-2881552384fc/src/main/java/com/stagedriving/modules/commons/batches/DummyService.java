/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.batches;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.modules.commons.batches.model.DummyEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.logging.Logger;

/**
 * @author simone
 */
@Singleton
public class DummyService extends SqueServiceAbstract<DummyEvent, Boolean> {

    private static final Logger LOG = Logger.getLogger(DummyService.class.getName());

    @Inject
    public DummyService(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(DummyEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, DummyEvent item) throws Exception {

        Thread.sleep(10000);

        return null;
    }

    @Override
    protected void onPostExecute(Boolean item) {

    }
}
