package com.stagedriving.modules.notification.controllers.model;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class NotificationSchedulerEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NotificationSchedulerEvent.class);

    private String id;

}
