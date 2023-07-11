package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.CheckinDTO;
import com.stagedriving.modules.commons.ds.entities.Checkin;
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
public interface CheckinMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "events", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    CheckinDTO checkinToCheckinDto(Checkin checkin);

    List<CheckinDTO> checkinsToCheckinDtos(List<Checkin> checkins);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "eventHasCheckins", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Checkin checkinDtoToCheckin(CheckinDTO checkinDTO);

    List<Checkin> checkinDtosToCheckins(List<CheckinDTO> checkinDTOs);
}