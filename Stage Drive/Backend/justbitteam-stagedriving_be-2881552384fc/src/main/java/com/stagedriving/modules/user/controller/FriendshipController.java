package com.stagedriving.modules.user.controller;

import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountFriendshipDAO;
import com.stagedriving.modules.commons.ds.daos.AccountMetaDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountFriendship;
import com.stagedriving.modules.commons.ds.entities.AccountFriendshipId;
import com.stagedriving.modules.commons.ds.entities.AccountMeta;
import org.hibernate.*;
import org.hibernate.context.internal.ManagedSessionContext;

import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class FriendshipController {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(FriendshipController.class);

    @Inject
    AccountFriendshipDAO friendshipDAO;
    @Inject
    AccountDAO accountDAO;
    @Inject
    AccountMetaDAO metaDAO;

    private SessionFactory sessionFactory;

    @Inject
    public FriendshipController() {
    }

    public void addFriendship(Account fromAccount, Account toAccount, boolean both) {
        if (!isFriends(fromAccount, toAccount)) {
            addFriend(fromAccount, toAccount);
        }

        if (both) {
            if (!isFriends(toAccount, fromAccount)) {
                addFriend(toAccount, fromAccount);
            }
        }
    }

    public boolean isFriends(Account fromAccount, Account toAccount) {
        if (fromAccount != null && toAccount != null) {
            AccountFriendshipId friendshipId = new AccountFriendshipId(fromAccount.getId(), toAccount.getId());
            AccountFriendship friendship = friendshipDAO.findById(friendshipId);
            if (friendship != null) {
                return true;
            }
        }

        return false;
    }

    public AccountFriendship addFriend(Account fromAccount, Account toAccount) {
        AccountFriendshipId friendshipId = new AccountFriendshipId(fromAccount.getId(), toAccount.getId());
        AccountFriendship friendship = friendshipDAO.findById(friendshipId);
        if (friendship == null) {
            LOGGER.info("Adding to friendship from " + fromAccount.getUid() + " to " + toAccount.getUid());
            AccountFriendshipId accountNewFriendshipId = new AccountFriendshipId(fromAccount.getId(), toAccount.getId());
            AccountFriendship newFriendship = new AccountFriendship();
            newFriendship.setId(accountNewFriendshipId);
            newFriendship.setUid(TokenUtils.generateUid());
            newFriendship.setCreated(new Date());
            newFriendship.setModified(new Date());
            newFriendship.setStatus(StgdrvData.FriendshipStatus.ACCEPTED);
            newFriendship.setTowards(false);
            newFriendship.setVisible(true);
            friendshipDAO.create(newFriendship);

            fromAccount.getAccountFriendshipsForAccountIdFrom().add(newFriendship);
            fromAccount.getAccountFriendshipsForAccountIdTo().add(newFriendship);
            accountDAO.edit(fromAccount);

            toAccount.getAccountFriendshipsForAccountIdFrom().add(newFriendship);
            toAccount.getAccountFriendshipsForAccountIdTo().add(newFriendship);
            accountDAO.edit(toAccount);
        }

        return friendship;
    }

    public void addFriends(List<Account> users, Account user, boolean both) {
        if (user == null) {
            return;
        }
        if (users == null) {
            return;
        }

        for (Account friend : users) {
            if (friend.equals(user)) {
                continue;
            }
            addFriendship(user, friend, both);
        }
    }

    public boolean aggregateFriendship(String accountId) {

        final Session session = sessionFactory.openSession();
        try {
            session.setDefaultReadOnly(false);
            session.setCacheMode(CacheMode.NORMAL);
            session.setFlushMode(FlushMode.AUTO);
            ManagedSessionContext.bind(session);
            session.beginTransaction();
            try {
                List<AccountMeta> metas = metaDAO.findByMwrenchByAccount(StgdrvData.MetaType.PHONEBOOK, accountId);
                for (AccountMeta meta : metas) {
                    Account source = accountDAO.findByUid(meta.getAccount().getUid());
                    String[] numbers = meta.getMvalue().split(",");
                    for (String phone : numbers) {
                        List<Account> accounts = accountDAO.findByMobile(phone);
                        if (accounts != null && accounts.size() == 1) {
                            Account target = accountDAO.findByUid(accounts.get(0).getUid());
                            if (!source.getUid().equalsIgnoreCase(target.getUid())) {
                                AccountFriendshipId friendshipId = new AccountFriendshipId(source.getId(), target.getId());
                                AccountFriendship friendship = friendshipDAO.findById(friendshipId);
                                if (friendship == null) {
                                    AccountFriendshipId accountNewFriendshipId = new AccountFriendshipId(source.getId(), target.getId());
                                    AccountFriendship newFriendship = new AccountFriendship();
                                    newFriendship.setId(accountNewFriendshipId);
                                    newFriendship.setUid(TokenUtils.generateUid());
                                    newFriendship.setCreated(new Date());
                                    newFriendship.setModified(new Date());
                                    newFriendship.setStatus(StgdrvData.FriendshipStatus.ACCEPTED);
                                    newFriendship.setTowards(false);
                                    newFriendship.setVisible(true);
                                    friendshipDAO.create(newFriendship);

                                    source.getAccountFriendshipsForAccountIdFrom().add(newFriendship);
                                    source.getAccountFriendshipsForAccountIdTo().add(newFriendship);
                                    accountDAO.edit(source);

                                    target.getAccountFriendshipsForAccountIdFrom().add(newFriendship);
                                    target.getAccountFriendshipsForAccountIdTo().add(newFriendship);
                                    accountDAO.edit(target);

                                    session.flush();
                                }
                            }
                        }
                    }
                }

                final Transaction txn = session.getTransaction();
                if (txn != null && txn.isActive()) {
                    txn.commit();
                }

                return true;

            } catch (Exception e) {

                final Transaction txn = session.getTransaction();
                if (txn != null && txn.isActive()) {
                    txn.rollback();
                }
                return false;
            }
        } finally {
            session.close();
            ManagedSessionContext.unbind(sessionFactory);
        }
    }
}
