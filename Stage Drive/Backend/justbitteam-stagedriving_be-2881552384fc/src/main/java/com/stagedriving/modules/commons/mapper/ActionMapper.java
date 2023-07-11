package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.ActionDTO;
import com.stagedriving.commons.v1.resources.CommentDTO;
import com.stagedriving.commons.v1.resources.LikeDTO;
import com.stagedriving.modules.commons.ds.entities.EventHasAction;
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
@Mapper(uses = {AccountMapper.class})
public interface ActionMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ActionDTO eventHasActionToAtionDto(EventHasAction eventHasAction);

    List<ActionDTO> eventHasActionsToAtionDtos(List<EventHasAction> eventHasActions);

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(source = "content", target = "content"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    CommentDTO eventHasActionToCommentDto(EventHasAction eventHasAction);

    List<CommentDTO> eventHasActionsToCommentDtos(List<EventHasAction> eventHasActions);

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    LikeDTO eventHasActionToLikeDto(EventHasAction eventHasAction);

    List<LikeDTO> eventHasActionsToLikeDtos(List<EventHasAction> eventHasActions);

//    @Mappings({
//            @Mapping(source = "uid", target = "id"),
//            @Mapping(target = "catalogs", ignore = true),
//            @Mapping(target = "checkins", ignore = true),
//            @Mapping(target = "fellowships", ignore = true),
//            @Mapping(target = "bookings", ignore = true),
//            @Mapping(target = "images", ignore = true),
//            @Mapping(target = "stuffs", ignore = true)
//    })
//    EventDTO eventToEventDto(Event event);
//
//    @Mappings({
//            @Mapping(source = "uid", target = "id"),
//            @Mapping(target = "images", ignore = true),
//            @Mapping(target = "devices", ignore = true),
//            @Mapping(target = "metas", ignore = true),
//            @Mapping(target = "connections", ignore = true),
//            @Mapping(target = "groups", ignore = true)
//    })
//    AccountDTO accountToAccountDto(Account account);
}