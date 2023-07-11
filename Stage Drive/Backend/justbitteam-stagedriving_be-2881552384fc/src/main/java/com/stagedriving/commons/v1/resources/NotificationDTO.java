package com.stagedriving.commons.v1.resources;

import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationDTO extends StgdrvBaseDTO {

    private String id;
    private String name;
    private String description;
    private String status;
    private String type;
    private Boolean sync;
    private List<String> tos = new ArrayList<String>(0);
    private List<String> froms = new ArrayList<String>(0);
    private List<NotificationMetaDTO> meta = new ArrayList<NotificationMetaDTO>(0);
}
