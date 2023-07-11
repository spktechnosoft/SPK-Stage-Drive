package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.EventInterestDTO;
import com.stagedriving.modules.commons.ds.entities.EventHasInterest;
import com.stagedriving.modules.commons.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Project stagedriving api
 * Author: man
 * Date: 16/10/18
 * Time: 18:36
 */
@Mapper
public interface EventInterestMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    EventInterestDTO eventInterestToEventInterestDto(EventHasInterest interest);

    List<EventInterestDTO> eventInterestsToEventInterestDtos(List<EventHasInterest> interests);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    EventHasInterest eventInterestDtoToEventInterest(EventInterestDTO eventInterestDTO);

    List<EventHasInterest> eventInterestDtoToEventInterests(List<EventInterestDTO> eventInterestDTOS);
}