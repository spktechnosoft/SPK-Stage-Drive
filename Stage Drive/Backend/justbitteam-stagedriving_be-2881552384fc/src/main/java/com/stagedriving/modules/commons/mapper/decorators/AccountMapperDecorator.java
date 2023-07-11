package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountGroup;
import com.stagedriving.modules.commons.ds.entities.AccountHasGroup;
import com.stagedriving.modules.commons.mapper.AccountGroupMapperImpl;
import com.stagedriving.modules.commons.mapper.AccountMapperImpl;

import java.util.ArrayList;
import java.util.List;

public class AccountMapperDecorator extends AccountMapperImpl {

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private VehicleMapperDecorator vehicleMapper;
    @Inject
    private AccountGroupMapperImpl accountGroupMapper;

    public AccountDTO accountToAccountDtoLight(Account account) {
        AccountDTO accountDTO = super.accountToAccountDto(account);

//        accountDTO.setVehicles(vehicleMapper.accountVehiclesToVehicleDtos(account.getAccountVehicles()));
        accountDTO.setDevices(null);
        accountDTO.setConnections(null);

        return accountDTO;
    }

    public AccountDTO accountToAccountDto(Account account) {
        AccountDTO accountDTO = super.accountToAccountDto(account);

        accountDTO.setVehicles(vehicleMapper.accountVehiclesToVehicleDtos(account.getAccountVehicles()));

        List<AccountGroup> groups = new ArrayList<>();
        for (AccountHasGroup accountHasGroup : account.getAccountHasGroups()) {
            groups.add(accountHasGroup.getAccountGroup());
        }
        accountDTO.setGroups(accountGroupMapper.groupsToGroupDtos(groups));

        return accountDTO;
    }

    public List<AccountDTO> accountsToAccountDtos(List<Account> accounts) {
        return accountsToAccountDtos(accounts);
    }
}
