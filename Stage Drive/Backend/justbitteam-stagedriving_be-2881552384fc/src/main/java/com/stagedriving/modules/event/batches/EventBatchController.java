package com.stagedriving.modules.event.batches;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.sque.SqueController;
import com.stagedriving.modules.event.batches.model.EventProcessorBatchEvent;
import com.stagedriving.modules.event.batches.model.RecommendedEventBatchEvent;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class EventBatchController {


    @Inject
    private SqueController squeController;

    public void scheduleBatch() throws Exception {

        try {
            squeController.flushQueue("batches-scheduled");

            EventProcessorBatchEvent eventBatchEvent = new EventProcessorBatchEvent();
            squeController.enqueueScheduled(eventBatchEvent, "batches", null, "*/30 * * * *");

            RecommendedEventBatchEvent recommendedEventBatchEvent = new RecommendedEventBatchEvent();
            squeController.enqueueScheduled(recommendedEventBatchEvent, "batches", null, "0 9 */7 * *");
        } catch (Exception ex) {
            log.error("Error", ex);
        }
    }

}
