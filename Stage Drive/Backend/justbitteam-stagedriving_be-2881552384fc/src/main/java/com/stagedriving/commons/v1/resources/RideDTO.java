package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Ride model")
public class RideDTO extends StgdrvBaseDTO {

    @Valid
    @ApiModelProperty(required = true)
    private CoordinateDTO fromCoordinate;

    @Valid
    @ApiModelProperty(required = true)
    private CoordinateDTO toCoordinate;

    @NotEmpty
    @ApiModelProperty(required = true)
    private Integer seats;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String goingDepartureDate;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String goingArrivalDate;

    @Valid
    @ApiModelProperty(required = true)
    private RidePriceDTO price;

    private Double totalPrice;
    private String currency;

    private String returnDepartureDate;
    private String returnArrivalDate;

    private String eventId;
    private String status;
    private Boolean visible;
    private String accountId;

    private String fromEventId;
    private String toEventId;

    private EventDTO fromEvent;
    private EventDTO toEvent;

    private Boolean withTickets;
    private Boolean withBookings;
    private Boolean withFriends;

    private Boolean hasReturn;

    private Integer availableSeats;
    private Integer bookedSeats;

    private RidePassengerDTO userPassenger;

    private EventDTO event;
    private AccountDTO account;

    private List<ActionDTO> actions = new ArrayList<ActionDTO>(0);
    private List<RidePassengerDTO> passengers = new ArrayList<RidePassengerDTO>(0);
    private List<RidePassengerDTO> friends = new ArrayList<RidePassengerDTO>(0);
}
