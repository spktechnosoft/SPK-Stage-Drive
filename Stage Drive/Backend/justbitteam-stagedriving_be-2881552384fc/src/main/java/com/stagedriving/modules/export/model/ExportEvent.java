package com.stagedriving.modules.export.model;


import com.justbit.sque.SqueEventBusModel;
import com.stagedriving.commons.v1.resources.ExportDTO;
import lombok.Data;

/**
 * Created by simone on 23/03/14.
 */
@Data
public class ExportEvent extends SqueEventBusModel {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ExportEvent.class);

    private String id;
    private ExportDTO export;

}
