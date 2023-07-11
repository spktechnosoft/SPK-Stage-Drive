package com.stagedriving.modules.metrics;


import com.justbit.sque.SqueEventBusModel;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class MetricBatchModel extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MetricBatchModel.class);

    private String id;

}
