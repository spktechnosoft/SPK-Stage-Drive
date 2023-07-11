package com.stagedriving.modules.event.batches.model;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class RecommendedEventBatchEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RecommendedEventBatchEvent.class);

    private String id;

}
