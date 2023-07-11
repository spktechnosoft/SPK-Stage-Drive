package com.stagedriving.modules.event.workers.model;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class EventWorkerEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EventWorkerEvent.class);

    private String id;
    private int accountId;
    private String ridePassengerId;

}
