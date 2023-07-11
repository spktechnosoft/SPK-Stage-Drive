package com.stagedriving.modules.event.views;

import com.stagedriving.modules.notification.views.PushNotificationView;
import lombok.Data;

/**
 * Created by simone on 13/10/14.
 */
@Data
public class EventNewRidePushNotificationView extends PushNotificationView {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EventNewRidePushNotificationView.class);

    private String name;
    private String eventName;

    public EventNewRidePushNotificationView() {
        super("EventNewRidePushNotificationView");

    }

    public void fillData(String id, String category, String uri, String eventId, String rideId) {
        super.fillData(id, category, uri);

        this.data.put("eventId", eventId);
        this.data.put("rideId", rideId);
    }
}
