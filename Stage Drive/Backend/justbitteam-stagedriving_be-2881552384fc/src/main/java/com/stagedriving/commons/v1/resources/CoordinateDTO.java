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
@ApiModel(description = "Coordinate model")
public class CoordinateDTO extends StgdrvBaseDTO {

    @NotEmpty
    @ApiModelProperty(required = true)
    private Double latitude;

    @NotEmpty
    @ApiModelProperty(required = true)
    private Double longitude;

    private Double altitude;
    private Double accuracy;

    private String address;
}
