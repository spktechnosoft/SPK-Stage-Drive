package com.stagedriving.commons.v1.resources;

import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActionDTO extends StgdrvBaseDTO {

    private String smallUri;
    private String normalUri;
    private String largeUri;
    private String status;
    private String pass;
    private AccountDTO account;
    private EventDTO event;
}
