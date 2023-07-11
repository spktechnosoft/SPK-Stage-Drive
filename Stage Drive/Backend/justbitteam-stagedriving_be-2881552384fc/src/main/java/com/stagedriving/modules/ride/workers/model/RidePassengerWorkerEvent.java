package com.stagedriving.modules.ride.workers.model;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class RidePassengerWorkerEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RidePassengerWorkerEvent.class);

    private String id;

}
