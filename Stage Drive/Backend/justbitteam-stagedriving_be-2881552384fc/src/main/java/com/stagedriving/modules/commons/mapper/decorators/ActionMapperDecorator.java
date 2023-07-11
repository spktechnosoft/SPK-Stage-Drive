package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.CommentDTO;
import com.stagedriving.commons.v1.resources.LikeDTO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasAction;
import com.stagedriving.modules.commons.mapper.*;
import com.stagedriving.modules.event.controllers.EventController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ActionMapperDecorator extends ActionMapperImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionMapperDecorator.class);
    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private EventMapperDecorator eventMapper;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private EventController eventController;

    public CommentDTO eventHasActionToCommentDto(EventHasAction eventHasAction) {

        CommentDTO commentDTO = super.eventHasActionToCommentDto(eventHasAction);
        Account account = accountDAO.findById(eventHasAction.getAccountid());
        commentDTO.setAccount(accountMapper.accountToAccountDto(account));
        commentDTO.getAccount().setHasTicket(eventController.accountHasTicket(eventHasAction.getAccountid(), eventHasAction.getEvent().getUid()));

        return commentDTO;
    }

    @Override
    public List<CommentDTO> eventHasActionsToCommentDtos(List<EventHasAction> eventHasActions) {
        if ( eventHasActions == null ) {
            return null;
        }

        List<CommentDTO> list = new ArrayList<CommentDTO>( eventHasActions.size() );
        for ( EventHasAction eventHasAction : eventHasActions ) {
            list.add( eventHasActionToCommentDto( eventHasAction ) );
        }

        return list;
    }

    public LikeDTO eventHasActionToLikeDto(EventHasAction eventHasAction) {
        LikeDTO likeDTO = super.eventHasActionToLikeDto(eventHasAction);
        Account account = accountDAO.findById(eventHasAction.getAccountid());
        likeDTO.setAccount(accountMapper.accountToAccountDto(account));
        likeDTO.getAccount().setHasTicket(eventController.accountHasTicket(eventHasAction.getAccountid(), eventHasAction.getEvent().getUid()));

        Event event = eventHasAction.getEvent();
        likeDTO.setEvent(eventMapper.eventToEventDto(event));

        return likeDTO;
    }

    public List<LikeDTO> eventHasActionsToLikeDtos(List<EventHasAction> eventHasActions) {
        if ( eventHasActions == null ) {
            return null;
        }

        List<LikeDTO> list = new ArrayList<LikeDTO>( eventHasActions.size() );
        for ( EventHasAction eventHasAction : eventHasActions ) {
            list.add( eventHasActionToLikeDto( eventHasAction ) );
        }

        return list;
    }

    public LikeDTO eventHasActionToLikeDto(EventHasAction eventHasAction, Account account) {
        LikeDTO likeDTO = eventHasActionToLikeDto(eventHasAction);

        if (account != null && eventHasAction.getEvent() != null) {
            likeDTO.setEvent(eventMapper.eventToEventDto(eventHasAction.getEvent(), account));
        }

        return likeDTO;
    }

    public List<LikeDTO> eventHasActionsToLikeDtos(List<EventHasAction> eventHasActions, Account account) {
        if ( eventHasActions == null ) {
            return null;
        }

        List<LikeDTO> list = new ArrayList<LikeDTO>( eventHasActions.size() );
        for ( EventHasAction eventHasAction : eventHasActions ) {
            list.add( eventHasActionToLikeDto( eventHasAction, account ) );
        }

        return list;
    }
}
