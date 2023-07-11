package com.stagedriving.modules.commons.utils;

import net.jodah.expiringmap.ExpiringMap;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SessionUtils {

    public static Map<String, String> sessions = ExpiringMap.builder()
            .maxSize(20000)
            .expiration(30, TimeUnit.SECONDS)
            .build();

}
