package com.stagedriving.modules.notification.views;

import io.dropwizard.views.View;
import lombok.Data;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by simone on 13/10/14.
 */
@Data
public abstract class PushNotificationView extends View {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PushNotificationView.class);

    private String title;
    protected Map<String, String> data;

    protected PushNotificationView(String template) {
        super(template+".ftl", Charset.forName("utf-8"));


    }

    public void fillData(String id, String category, String uri) {
        data = new HashMap<>();
        data.put("id", id);
        data.put("category", category);
        if (uri != null) {
            data.put("uri", uri);
        }
    }
}
