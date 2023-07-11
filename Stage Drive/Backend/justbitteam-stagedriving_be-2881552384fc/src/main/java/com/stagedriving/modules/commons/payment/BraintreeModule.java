package com.stagedriving.modules.commons.payment;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.justbit.jedis.JedisBundle;
import io.dropwizard.Configuration;
import redis.clients.jedis.JedisPool;


public class BraintreeModule extends AbstractModule {
    private BraintreeBundle<? extends Configuration> braintreeBundle;

    public BraintreeModule(BraintreeBundle<? extends Configuration> braintreeBundle) {
        this.braintreeBundle = braintreeBundle;
    }

    protected void configure() {
    }
}