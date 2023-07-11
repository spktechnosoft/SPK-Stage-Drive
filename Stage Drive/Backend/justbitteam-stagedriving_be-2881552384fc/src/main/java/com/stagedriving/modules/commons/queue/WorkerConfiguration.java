package com.stagedriving.modules.commons.queue;

import lombok.Getter;

/**
 *
 * @author Bo Gotthardt
 */
@Getter
public class WorkerConfiguration {
    /** The fully qualified class name of the worker. */
    private Class<? extends QueueWorker> worker;
    /** How many threads to start with this worker. */
    private int threads = 1;
    private String queue = "commons";
    private String exchange = "commons";
    private String exchangeType = "direct";
    private String key = "";
}
