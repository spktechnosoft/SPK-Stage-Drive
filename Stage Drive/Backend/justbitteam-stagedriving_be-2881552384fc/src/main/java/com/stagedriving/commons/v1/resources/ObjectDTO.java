/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author simone
 */
@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectDTO extends StgdrvBaseDTO {
    private String content;
    private String bucket;
    private String mimeType;
    private int size;
}
