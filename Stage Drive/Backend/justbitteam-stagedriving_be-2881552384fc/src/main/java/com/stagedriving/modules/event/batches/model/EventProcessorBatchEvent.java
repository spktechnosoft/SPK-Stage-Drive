package com.stagedriving.modules.event.batches.model;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class EventProcessorBatchEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EventProcessorBatchEvent.class);

    private String id;

}
