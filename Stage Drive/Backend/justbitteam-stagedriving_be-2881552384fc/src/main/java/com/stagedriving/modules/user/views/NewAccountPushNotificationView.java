package com.stagedriving.modules.user.views;

import com.stagedriving.modules.notification.views.PushNotificationView;
import lombok.Data;

/**
 * Created by simone on 13/10/14.
 */
@Data
public class NewAccountPushNotificationView extends PushNotificationView {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NewAccountPushNotificationView.class);

    public NewAccountPushNotificationView() {
        super("NewAccountPushNotificationView");


    }
}
