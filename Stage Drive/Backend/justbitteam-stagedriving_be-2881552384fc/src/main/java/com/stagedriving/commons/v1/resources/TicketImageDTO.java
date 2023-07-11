package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketImageDTO extends StgdrvBaseDTO {

    private String smallUri;
    private String normalUri;
    private String largeUri;
    private Boolean visible;
    private String category;
    private EventDTO event;
}
