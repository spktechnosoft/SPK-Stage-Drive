package com.stagedriving.modules.commons.dispatcher.model;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class GlobalEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(GlobalEvent.class);

    private String id;
    private String type;
    private String tag;

}
