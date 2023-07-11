package com.stagedriving.modules.notification.views;

import io.dropwizard.views.View;
import lombok.Data;

import java.nio.charset.Charset;

/**
 * Created by simone on 13/10/14.
 */
@Data
public abstract class EmailNotificationView extends View {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailNotificationView.class);

    private String viewUrl;
    private String subject;

    protected EmailNotificationView(String template) {
        super(template+".ftl", Charset.forName("utf-8"));


    }
}
