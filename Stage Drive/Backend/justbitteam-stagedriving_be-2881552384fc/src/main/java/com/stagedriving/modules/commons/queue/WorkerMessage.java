package com.stagedriving.modules.commons.queue;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Data
public class WorkerMessage {

    private String id;
    private String type;
    private Map<String, String> data;

}
