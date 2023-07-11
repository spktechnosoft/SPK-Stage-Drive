package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "Event model")
public class EventDTO extends StgdrvBaseDTO {

    private String id;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String name;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String description;

    @NotNull
    @ApiModelProperty(required = true)
    private CoordinateDTO coordinate;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String address;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String country;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String city;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String town;

    @NotEmpty
    @ApiModelProperty(required = true)
    private String zipcode;

    @NotNull
    @ApiModelProperty(required = true)
    private DateTime start;

    @NotNull
    @ApiModelProperty(required = true)
    private DateTime finish;

    @NotNull
    @ApiModelProperty(required = true)
    private String organizer;

    private Double distance;

    private String parking;
    private String website;

    private Integer capacity;
    private String status;

    private String category;
    private Boolean visible;
    private String code;
    private String level;
    private String mobile;
    private String phone;
    private Integer ncheckin;
    private Integer nlike;
    private Integer nride;
    private Integer ncomment;
    private Integer nbooking;
    private Integer nbookingticket;

    private List<CheckinDTO> checkins;
    private List<BookingDTO> bookings;
    private List<ImageDTO> images;
    private List<ActionDTO> actions;
    private List<ActionDTO> likes;
    private List<ActionDTO> comments;
    private List<EventInterestDTO> interests;
}
