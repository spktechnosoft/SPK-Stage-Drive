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
public class BrandDTO extends StgdrvBaseDTO {

    private String status;
    private Boolean visible;
    private String name;
    private String description;
    private String uri;
    private Boolean base;
    private List<ItemDTO> items = new ArrayList<ItemDTO>(0);
}