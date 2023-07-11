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
@ApiModel(description = "Ride Passenger model")
public class RidePassengerDTO extends StgdrvBaseDTO {

    private String status;
    private String accountId;
    private RideDTO ride;
    private Integer seats;
    private TransactionDTO transaction;

    @NotNull
    @ApiModelProperty(required = true)
    private AccountDTO account;
}
