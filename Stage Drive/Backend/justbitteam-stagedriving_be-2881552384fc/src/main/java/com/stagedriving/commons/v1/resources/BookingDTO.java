package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO extends StgdrvBaseDTO {

    private EventDTO event;
    private AccountDTO account;

    private String status;
    private Boolean withTicket;
    private List<EventDTO> events = new ArrayList<EventDTO>(0);
}
