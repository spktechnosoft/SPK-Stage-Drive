package com.stagedriving.commons.v1.resources;

import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemCategoryDTO extends StgdrvBaseDTO {
    private String name;
    private String description;
    private String tag;
    private Integer position;
}
