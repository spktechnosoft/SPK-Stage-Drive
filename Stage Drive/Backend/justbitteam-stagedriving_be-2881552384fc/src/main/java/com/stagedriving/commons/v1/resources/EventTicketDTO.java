package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventTicketDTO extends StgdrvBaseDTO {

    private Boolean visible;
    private String status;
    private String name;
    private String description;
    private List<AccountDTO> buyers = new ArrayList<AccountDTO>(0);
    private List<TicketImageDTO> images = new ArrayList<TicketImageDTO>(0);
}
