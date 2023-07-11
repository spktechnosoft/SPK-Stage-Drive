package com.stagedriving.modules.commons.payment;

import io.dropwizard.Configuration;

public interface BraintreeConfigurationStrategy<T extends Configuration> {
    BraintreeConfiguration getBranitreeConfiguration(T var1);
}
