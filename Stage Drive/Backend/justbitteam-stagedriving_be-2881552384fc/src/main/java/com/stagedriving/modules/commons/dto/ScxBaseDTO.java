/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author simone
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScxBaseDTO {
    private String id;
    private String created;
    private String modified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    
}
