package com.stagedriving.modules.export;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.sque.SqueController;
import com.stagedriving.commons.v1.resources.ExportDTO;
import com.stagedriving.modules.export.model.ExportEvent;

@Singleton
public class ExportController {

    @Inject
    private SqueController squeController;

    public void requestExport(ExportDTO exportDTO) throws Exception {
        ExportEvent exportEvent = new ExportEvent();
        exportEvent.setExport(exportDTO);
        squeController.enqueue(exportEvent, "exports", 3600, null);
    }
}
