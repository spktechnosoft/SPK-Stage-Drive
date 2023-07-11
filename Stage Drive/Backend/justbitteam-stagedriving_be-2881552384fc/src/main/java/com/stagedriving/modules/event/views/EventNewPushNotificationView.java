package com.stagedriving.modules.event.views;

import com.stagedriving.modules.notification.views.PushNotificationView;
import lombok.Data;

/**
 * Created by simone on 13/10/14.
 */
@Data
public class EventNewPushNotificationView extends PushNotificationView {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EventNewPushNotificationView.class);

    private String name;
    private String eventName;

    public EventNewPushNotificationView() {
        super("EventNewPushNotificationView");

    }

    public void fillData(String id, String category, String uri, String eventId) {
        super.fillData(id, category, uri);

        this.data.put("eventId", eventId);
    }
}
