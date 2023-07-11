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
public class CatalogDTO extends StgdrvBaseDTO {

    private Boolean visible;
    private Integer cardinality;
    private String category;
    private String name;
    private String description;
    private List<EventDTO> events = new ArrayList<EventDTO>(0);
    private List<ItemDTO> items = new ArrayList<ItemDTO>(0);
    private List<BrandDTO> brands = new ArrayList<BrandDTO>(0);
}
