package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "App config bundle model")
public class BundleDTO extends StgdrvBaseDTO {

    private String name;
    private String version;
    private String status;
    private String androidv;
    private String iosv;

    @NotEmpty
    @ApiModelProperty(required = true)
    private List<CatalogDTO> catalogs = new ArrayList<CatalogDTO>(0);

    @NotEmpty
    @ApiModelProperty(required = true)
    private Map<String, CatalogDTO> data;
}