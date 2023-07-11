package com.stagedriving.modules.user.controller;

import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountGroupDAO;
import com.stagedriving.modules.commons.ds.daos.AccountHasGroupDAO;
import com.stagedriving.modules.commons.ds.entities.*;

import java.util.Date;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class AccountController {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AccountController.class);

    @Inject
    private AccountHasGroupDAO accountHasGroupDAO;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private AccountGroupDAO accountGroupDAO;

    public boolean isAdmin(Account account) {
        return accountHasRole(account, StgdrvData.AccountGroups.ADMIN);
    }

    public boolean isOrganizer(Account account) {
        return accountHasRole(account, StgdrvData.AccountGroups.ORGANIZER);
    }

    public boolean isDriver(Account account) {
        return accountHasRole(account, StgdrvData.AccountGroups.DRIVER);
    }

    public boolean accountHasRole(Account account, String role) {
        boolean hasGroup = false;
        if (account != null && account.getAccountHasGroups() != null) {
            for (AccountHasGroup accountHasGroup : account.getAccountHasGroups()) {
                if (accountHasGroup != null && accountHasGroup.getAccountGroup() != null) {
                    if (accountHasGroup.getAccountGroup().getName().equalsIgnoreCase(role)) {
                        hasGroup = true;
                        break;
                    }
                }
            }
        }

        return hasGroup;
    }

    public Account getAccount(Account callingAccount, Account requestedAccount) {

        if (callingAccount != null && requestedAccount == null) {
            requestedAccount = callingAccount;
        }
//        else if (callingAccount != null && accountHasRole(callingAccount, StgdrvData.AccountGroups.ADMIN)) {
//
//        }

        return requestedAccount;
    }

    public void addRoleToAccount(String groupName, Account account) {
        if (account == null) {
            return;
        }

        boolean addGroup = true;
        if (account.getAccountHasGroups() != null) {
            for (AccountHasGroup accountHasGroup : account.getAccountHasGroups()) {
                if (accountHasGroup != null && accountHasGroup.getAccountGroup() != null) {
                    if (accountHasGroup.getAccountGroup().getName().equalsIgnoreCase(groupName)) {
                        addGroup = false;
                        break;
                    }
                }
            }
        }

        if (addGroup) {
            LOGGER.info("Adding group '" + groupName + "' to " + account.getUid());

            AccountGroup accountGroup = accountGroupDAO.findByName(groupName);

            AccountHasGroupId accountHasGroupId = new AccountHasGroupId();
            accountHasGroupId.setAccountId(account.getId());
            accountHasGroupId.setGroupId(accountGroup.getId());
            AccountHasGroup accountHasGroup = new AccountHasGroup();
            accountHasGroup.setId(accountHasGroupId);
            accountHasGroup.setAccount(account);
            accountHasGroup.setAccountGroup(accountGroup);
            accountHasGroup.setVisible(true);
            accountHasGroup.setCreated(new Date());
            accountHasGroup.setModified(new Date());

            accountHasGroupDAO.edit(accountHasGroup);

            account.getAccountHasGroups().add(accountHasGroup);
            accountGroup.getAccountHasGroups().add(accountHasGroup);

            accountGroupDAO.edit(accountGroup);
            accountDAO.edit(account);


            accountGroupDAO.getCurrentSession().flush();
        }
    }

    public void removeRoleFromAccount(String groupName, Account account) {
        if (account == null) {
            return;
        }

        AccountHasGroup accountHasGroupToRem = null;
        if (account.getAccountHasGroups() != null) {
            for (AccountHasGroup accountHasGroup : account.getAccountHasGroups()) {
                if (accountHasGroup != null && accountHasGroup.getAccountGroup() != null) {
                    if (accountHasGroup.getAccountGroup().getName().equalsIgnoreCase(groupName)) {
                        accountHasGroupToRem = accountHasGroup;
                        break;
                    }
                }
            }
        }

        if (accountHasGroupToRem != null) {
            LOGGER.info("Removing group '" + groupName + "' from " + account.getUid());

            accountHasGroupDAO.delete(accountHasGroupToRem);
        }
    }

    public void checkForRole(Account account) {
        if (account == null) {
            return;
        }

        Boolean hasPhoneConnection = false;
        for (AccountConnection accountConnection : account.getAccountConnections()) {
            if (accountConnection.getProvider().equalsIgnoreCase(StgdrvData.Provider.PHONE)) {
                hasPhoneConnection = true;
                break;
            }
        }
        if (account.getTelephone() != null) {
            hasPhoneConnection = true;
        }
        if (account.getMobile() != null) {
            hasPhoneConnection = true;
        }

        Boolean hasVehicle = false;
        if (account.getAccountVehicles() != null && !account.getAccountVehicles().isEmpty()) {
            hasVehicle = true;
        }

        Boolean hasCompanyInfo = false;
        if (account.getCompanyRef() != null && account.getCompanyName() != null
                && account.getCompanyAddress() != null && account.getCompanyCity() != null
                && account.getCompanyCountry() != null && account.getCompanyVatId() != null
                && account.getCompanyZipcode() != null) {
            hasCompanyInfo = true;
        }

        if (hasPhoneConnection && hasVehicle) {
            addRoleToAccount(StgdrvData.AccountGroups.DRIVER, account);
        }
        if (hasCompanyInfo) {
            addRoleToAccount(StgdrvData.AccountGroups.ORGANIZER, account);
        }
    }

}
