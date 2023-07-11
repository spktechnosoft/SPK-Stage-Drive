package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.EventInterestDTO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.EventHasInterest;
import com.stagedriving.modules.commons.mapper.EventInterestMapperImpl;
import com.stagedriving.modules.commons.utils.DateUtils;

public class EventInterestMapperDecorator extends EventInterestMapperImpl {

    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private AccountDAO accountDAO;

    public EventInterestDTO eventInterestToEventInterestDto(EventHasInterest interest) {
        super.eventInterestToEventInterestDto(interest);
        EventInterestDTO eventInterestDTO = new EventInterestDTO();
        eventInterestDTO.setId(interest.getUid());
        eventInterestDTO.setCreated(DateUtils.dateToString(interest.getCreated()));
        eventInterestDTO.setModified(DateUtils.dateToString(interest.getModified()));

        Account account = accountDAO.findById(interest.getAccountId());
        eventInterestDTO.setAccount(accountMapper.accountToAccountDto(account));

        return eventInterestDTO;
    }
}
