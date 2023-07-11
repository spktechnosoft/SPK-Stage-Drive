package com.stagedriving.modules.commons.payment.paypal;

import io.dropwizard.Configuration;

public interface PaypalConfigurationStrategy<T extends Configuration> {
    PaypalConfiguration getPaypalConfiguration(T var1);
}
