package com.stagedriving.modules.notification.controllers.model;

import lombok.Data;

@Data
public class NotificationRecipientHolder {
    private String os;
    private String id;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotificationRecipientHolder(String os, String id) {
        this.os = os;
        this.id = id;
    }
}
