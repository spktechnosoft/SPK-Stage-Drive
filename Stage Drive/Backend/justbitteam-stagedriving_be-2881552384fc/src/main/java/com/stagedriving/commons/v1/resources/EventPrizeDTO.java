package com.stagedriving.commons.v1.resources;

import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventPrizeDTO extends StgdrvBaseDTO {

    private EventDTO event;
    private String category;
    private Integer accountid;
    private Boolean visible;
    private Boolean consumed;
}
