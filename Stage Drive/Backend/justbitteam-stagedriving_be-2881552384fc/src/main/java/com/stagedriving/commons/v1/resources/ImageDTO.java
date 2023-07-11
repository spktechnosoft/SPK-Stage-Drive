package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Image model")
public class ImageDTO extends StgdrvBaseDTO {

    @NotEmpty
    @ApiModelProperty(required = true)
    private String uri;

    private String content;
    private String smallUri;
    private String normalUri;
    private String largeUri;

    private String category;
}
