package com.stagedriving.modules.export.views;

import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.notification.views.EmailNotificationView;
import lombok.Data;

/**
 * Created by simone on 13/10/14.
 */
@Data
public class ExportReadyEmailNotificationView extends EmailNotificationView {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExportReadyEmailNotificationView.class);

    private String download;
    private Account user;

    public ExportReadyEmailNotificationView() {
        super("ExportReadyEmailNotificationView");


    }
}
