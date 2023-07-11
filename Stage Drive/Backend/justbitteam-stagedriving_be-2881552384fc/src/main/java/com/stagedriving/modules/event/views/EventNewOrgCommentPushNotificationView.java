package com.stagedriving.modules.event.views;

import com.stagedriving.modules.notification.views.PushNotificationView;
import lombok.Data;

/**
 * Created by simone on 13/10/14.
 */
@Data
public class EventNewOrgCommentPushNotificationView extends PushNotificationView {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EventNewOrgCommentPushNotificationView.class);

    public EventNewOrgCommentPushNotificationView() {
        super("EventNewOrgCommentPushNotificationView");

    }

    public void fillData(String id, String category, String uri, String eventId) {
        super.fillData(id, category, uri);

        this.data.put("eventId", eventId);
    }
}
