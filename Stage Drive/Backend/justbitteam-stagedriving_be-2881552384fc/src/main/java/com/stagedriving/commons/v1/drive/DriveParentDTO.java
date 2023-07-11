/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.commons.v1.drive;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author manuel
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriveParentDTO {
    private String id;
    private String kind;
    private String selfLink;
    private String parentLink;
    private boolean isRoot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getParentLink() {
        return parentLink;
    }

    public void setParentLink(String parentLink) {
        this.parentLink = parentLink;
    }


}
