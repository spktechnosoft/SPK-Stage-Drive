package com.stagedriving.modules.commons.ds;

import io.dropwizard.hibernate.SessionFactoryFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by simone on 05/02/17.
 */
public class JbSessionFactoryFactory extends SessionFactoryFactory {

    @Override
    protected void configure(Configuration configuration, ServiceRegistry registry) {
//        ScxNotificationEventListener listener = Service.getGuice().getInjector().getInstance(ScxNotificationEventListener.class);
//        EventListenerRegistry reg = registry.getService(EventListenerRegistry.class);
//        reg.getEventListenerGroup(EventType.POST_COMMIT_INSERT).appendListener(listener);


//        configuration.setInterceptor(new DummyDbInterceptor());
    }
}
