package com.stagedriving.modules.user.controller;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.conf.AppConfiguration;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class ConnectionController {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ConnectionController.class);

    @Inject
    private AppConfiguration configuration;

    @Inject
    public ConnectionController(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    public boolean checkConnectionTokenOnProvider(String id, String provider, String token) {
        return true;
    }
}
