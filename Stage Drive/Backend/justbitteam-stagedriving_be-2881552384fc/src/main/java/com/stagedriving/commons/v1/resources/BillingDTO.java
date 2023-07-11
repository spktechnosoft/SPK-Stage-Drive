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

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Account billing methods model")
public class BillingDTO extends StgdrvBaseDTO {

    @NotEmpty
    @ApiModelProperty(required = true)
    private String provider;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String iban;

    private String coordinate;
    private String status;
    private String swift;
    private String note;
    private AccountDTO account;
}
