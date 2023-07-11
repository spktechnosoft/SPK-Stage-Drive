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
public class DriveResponseDTO {

    private String kind;
    private String id;
    private String etag;
    private String selfLink;
    private String alternateLink;
    private String iconLink;
    private String title;
    private String mimeType;
    private List<DriveParentDTO> parents;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String getAlternateLink() {
        return alternateLink;
    }

    public void setAlternateLink(String alternateLink) {
        this.alternateLink = alternateLink;
    }

    public String getIconLink() {
        return iconLink;
    }

    public void setIconLink(String iconLink) {
        this.iconLink = iconLink;
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

    public List<DriveParentDTO> getParents() {
        return parents;
    }

    public void setParents(List<DriveParentDTO> parents) {
        this.parents = parents;
    }
}
