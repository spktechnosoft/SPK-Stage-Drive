package com.stagedriving.modules.commons.service.init;

/**
 * Created by man on 25/04/16.
 */
public class InitEvent {
    public enum Task {
        CATALOG,
        GROUP,
        BRAND,
        COLOR,
        TRUNCATE
    }

    private Task operation;

    public InitEvent() {
    }

    public Task getOperation() {
        return operation;
    }

    public void setOperation(Task operation) {
        this.operation = operation;
    }
}