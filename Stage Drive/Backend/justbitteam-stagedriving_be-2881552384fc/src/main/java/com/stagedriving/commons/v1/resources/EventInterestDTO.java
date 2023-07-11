package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventInterestDTO extends StgdrvBaseDTO {

    private EventDTO event;
    private AccountDTO account;

    private String bookingId;
    private String bookingTicketId;
    private String actionLikeId;
    private String actionCommentId;
    private String actionRideId;

}
