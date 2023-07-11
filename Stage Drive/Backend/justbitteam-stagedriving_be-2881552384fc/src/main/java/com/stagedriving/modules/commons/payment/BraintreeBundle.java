package com.stagedriving.modules.commons.payment;

import com.braintreegateway.BraintreeGateway;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.Getter;

@Getter
public abstract class BraintreeBundle<T extends Configuration> implements ConfiguredBundle<T>, BraintreeConfigurationStrategy<T> {

    private BraintreeConfiguration configuration;
    private BraintreeGateway gateway;

    public BraintreeBundle() {

    }

    @Override
    public void run(T configuration, Environment environment) throws Exception {
        this.configuration = this.getBranitreeConfiguration(configuration);

        if (this.configuration != null) {
            com.braintreegateway.Environment env = com.braintreegateway.Environment.DEVELOPMENT;
            if (this.configuration.getEnvironment().equals("sandbox")) {
                env = com.braintreegateway.Environment.SANDBOX;
            } else if (this.configuration.getEnvironment().equals("production")) {
                env = com.braintreegateway.Environment.PRODUCTION;
            }

            gateway = new BraintreeGateway(
                    env,
                    this.configuration.getMerchantId(),
                    this.configuration.getPublicKey(),
                    this.configuration.getPrivateKey()
            );
        }
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        //instance = this;
    }

    public BraintreeGateway getBraintreeGateway() {
        return this.gateway;
    }
}
