package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.FellowshipDTO;
import com.stagedriving.modules.commons.ds.entities.Fellowship;
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
public interface FellowshipMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "events", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    FellowshipDTO fellowshipToFellowshipDto(Fellowship fellowship);

    List<FellowshipDTO> fellowshipsToFellowshipDtos(List<Fellowship> fellowships);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Fellowship fellowshipDtoToFellowship(FellowshipDTO fellowshipDTO);

    List<Fellowship> fellowshipDtosToFellowships(List<FellowshipDTO> fellowshipDTOs);
}