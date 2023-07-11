package com.stagedriving.commons.v1.resources;

import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedDTO extends StgdrvBaseDTO {

    Boolean bookmark;
    Boolean like;
    Integer ncomment;
    Integer nlike;
    String content;
    EventDTO event;
    ItemDTO item;
    private List<CommentDTO> comments = new ArrayList<CommentDTO>(0);
}
