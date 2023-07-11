package com.stagedriving.modules.user.workers.model;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class FriendshipMatchWorkerEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FriendshipMatchWorkerEvent.class);

    private String id;

}
