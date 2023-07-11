package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO extends StgdrvBaseDTO {

    private String accountIdFrom;
    private String accountIdTo;
    private AccountDTO accountFrom;
    private AccountDTO accountTo;
    @NotNull
    @ApiModelProperty(required = true)
    private String rideId;
    private String ridePassengerId;
    @NotNull
    @ApiModelProperty(required = true)
    private String eventId;
    private EventDTO event;
    private RideDTO ride;
    private RidePassengerDTO ridePassenger;
    private String status;
    private String statusMessage;
    private String provider;
    private String providerFee;
//    @NotNull
//    @ApiModelProperty(required = true)
    private String providerToken;
    private String providerOrderId;
    @NotNull
    @ApiModelProperty(required = true)
    private Double amount;
    private Double fee;
    private Double totalAmount;
    private String currency;
    private Double refundedAmount;
    private DateTime refundedAt;
    private DateTime payedAt;
}
