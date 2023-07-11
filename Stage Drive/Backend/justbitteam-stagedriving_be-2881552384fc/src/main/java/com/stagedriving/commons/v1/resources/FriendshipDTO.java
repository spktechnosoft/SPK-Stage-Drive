package com.stagedriving.commons.v1.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendshipDTO extends StgdrvBaseDTO {

    private AccountDTO to;
    private AccountDTO from;
    private Boolean visible;
    private Boolean towards;
    private String uid;
    private String status;
}
