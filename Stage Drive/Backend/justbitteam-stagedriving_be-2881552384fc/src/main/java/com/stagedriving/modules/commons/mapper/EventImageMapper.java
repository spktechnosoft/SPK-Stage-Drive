package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.ImageDTO;
import com.stagedriving.modules.commons.ds.entities.EventImage;
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
public interface EventImageMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(source = "image", target = "uri"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ImageDTO eventImageToImageDto(EventImage eventImage);

    List<ImageDTO> eventImagesToImageDtos(List<EventImage> eventImages);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "event", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    EventImage imageDtoToEventImage(ImageDTO imageDTO);

    List<EventImage> imageDtosToEventImages(List<ImageDTO> imageDTOs);
}