/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.hubspot.dropwizard.guice.GuiceBundle;
import com.stagedriving.Service;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.SessionFactory;

/**
 * @author simone
 */
public class AppModule extends AbstractModule {

    private HibernateBundle<AppConfiguration> hibernate;

    public AppModule(HibernateBundle<AppConfiguration> hibernate) {
        this.hibernate = hibernate;
    }

    @Override
    protected void configure() {
//        install(MetricsInstrumentationModule.builder().withMetricRegistry(new MetricRegistry()).build());
    }

    @Provides
    public SessionFactory sessionFactory() {
        return hibernate.getSessionFactory();
    }

    @Provides
    @Named("session")
    public SessionFactory sessionFactoryNamed() {
        return hibernate.getSessionFactory();
    }

    //    @Provides
//    @Singleton
//    @Named("com.stagedriving.modules.commons.mapper.ActionMapperImpl_")
//    public ActionMapper providesActionMapper() {
//        ActionMapper mapper = new ActionMapperImpl_();
//        return mapper;
//    }
//    @Provides
//    @Singleton
//    public MetricRegistry provideRegistry(Environment environment) {
//        return environment.metrics();
//    }


    @Provides
    public GuiceBundle guice() {
        return Service.getGuice();
    }

}
