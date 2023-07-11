package com.stagedriving.commons.v1.resources;

import com.stagedriving.commons.v1.base.StgdrvBaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticDTO extends StgdrvBaseDTO {

    public Integer totalaccount;
    public Integer activeaccount;
    public Integer inactiveaccount;
    public Integer bannedaccount;

    public Integer totaldealer;
    public Integer activedealer;
    public Integer inactivedealer;
    public Integer banneddealer;

    public Integer totalbrand;
    public Integer confirmedbrand;
    public Integer unconfirmedbrand;
    public Integer pendingbrand;

    public Integer images;
    public Integer likes;
    public Integer comments;
    public Integer bookmarks;
}
