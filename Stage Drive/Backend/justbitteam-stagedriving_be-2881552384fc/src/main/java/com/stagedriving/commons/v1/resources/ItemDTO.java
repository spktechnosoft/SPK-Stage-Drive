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
public class ItemDTO extends StgdrvBaseDTO {

    private String name;
    private String description;
    private String tag;
    private String picture;
    private String status;
    private Boolean visible;
    private Boolean male;
    private Boolean female;
    private Boolean children;
    private Boolean outfit;
    private Boolean published;
    private Boolean like;
    private Boolean bookmark;
    private Integer ncomment;
    private Integer nlike;
    private EventDTO event;
    private List<ItemCategoryDTO> categories = new ArrayList<ItemCategoryDTO>(0);
    private List<CatalogDTO> catalogs = new ArrayList<CatalogDTO>(0);
    private List<BrandDTO> brands = new ArrayList<BrandDTO>(0);
    private List<ColorDTO> colors = new ArrayList<ColorDTO>(0);
}
