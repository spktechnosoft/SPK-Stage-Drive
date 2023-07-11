/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.commons.v1.drive;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 *
 * @author manuel
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriveDocsDTO {
    private String title;
    private String mimeType;
    private String description;
    private String kind;
    private List<DriveParentDTO> parents;

    public List<DriveParentDTO> getParents() {
        return parents;
    }

    public void setParents(List<DriveParentDTO> parents) {
        this.parents = parents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
