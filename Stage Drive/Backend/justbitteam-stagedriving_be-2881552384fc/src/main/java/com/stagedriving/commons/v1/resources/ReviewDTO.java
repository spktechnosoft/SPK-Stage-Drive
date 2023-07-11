package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Account review model")
public class ReviewDTO extends StgdrvBaseDTO {

    @ApiModelProperty(required = false)
    private String title;

    @ApiModelProperty(required = false)
    private String content;

    @NotNull
    @ApiModelProperty(required = true)
    private Double star;

    private AccountDTO account;
    private AccountDTO author;
}

