package com.stagedriving.modules.ride.views;

import com.stagedriving.modules.notification.views.PushNotificationView;
import lombok.Data;

/**
 * Created by simone on 13/10/14.
 */
@Data
public class RideCancelPassengerPushNotificationView extends PushNotificationView {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RideCancelPassengerPushNotificationView.class);

    public RideCancelPassengerPushNotificationView() {
        super("RideCancelPassengerPushNotificationView");

    }

    public void fillData(String id, String category, String uri, String rideId) {
        super.fillData(id, category, uri);

        this.data.put("rideId", rideId);
    }
}
