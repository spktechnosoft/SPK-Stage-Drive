package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.ColorDTO;
import com.stagedriving.modules.commons.ds.entities.Color;
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
public interface ColorMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ColorDTO colorToColorDto(Color color);

    List<ColorDTO> colorsToColorDtos(List<Color> colors);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Color colorDtoToColor(ColorDTO colorDTO);

    List<Color> colorDtosToColors(List<ColorDTO> colorDTOs);
}