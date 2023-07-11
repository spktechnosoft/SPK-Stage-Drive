package com.stagedriving.commons.v1.resources;

import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemFamilyDTO extends StgdrvBaseDTO {
    private String name;
    private String description;
    private String smallUri;
    private String normalUri;
    private String largeUri;
    private String tag;
    private Integer position;

    private List<ItemCategoryDTO> categories = new ArrayList<ItemCategoryDTO>(0);
}
