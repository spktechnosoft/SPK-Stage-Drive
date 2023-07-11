package com.stagedriving.modules.user.workers;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountFriendshipDAO;
import com.stagedriving.modules.commons.ds.daos.AccountMetaDAO;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.user.workers.model.FriendshipMatchWorkerEvent;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

@Slf4j
public class FriendshipMatchWorker extends SqueServiceAbstract<FriendshipMatchWorkerEvent, Boolean> {

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private AccountFriendshipDAO friendshipDAO;
    @Inject
    private AccountMetaDAO metaDAO;

    @Inject
    public FriendshipMatchWorker(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(FriendshipMatchWorkerEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, FriendshipMatchWorkerEvent item) throws Exception {

        String metaUid = item.getId();

        AccountMeta meta = metaDAO.findByUid(metaUid);
        if (!meta.getMwrench().equals(StgdrvData.AccountMetas.PHONEBOOK)) {
            return "skipped";
        }

        Account account = meta.getAccount();
        log.info("Friendship update for " + account.getUid());

//        List<AccountMeta> metas = metaDAO.findByMwrenchByAccount(StgdrvData.MetaType.PHONEBOOK, accountId);
//        for (AccountMeta meta : metas) {
        Account source = accountDAO.findByUid(meta.getAccount().getUid());
        String[] numbers = meta.getMvalue().split(",");
        for (String phone : numbers) {
            phone = phone.trim();
            List<Account> accounts = accountDAO.findByMobile(phone);
            if (accounts != null && accounts.isEmpty()) {
                accounts = accountDAO.findByTelephone(phone);
            }
            if (accounts != null && accounts.size() > 0 /*&& accounts.size() == 1*/) {
                for (Account acc : accounts) {
                    Account target = acc;
                    if (!source.getUid().equalsIgnoreCase(target.getUid())) {
                        AccountFriendshipId friendshipId = new AccountFriendshipId(source.getId(), target.getId());
                        AccountFriendship friendship = friendshipDAO.findById(friendshipId);
                        if (friendship == null) {
                            log.info("Adding to friendship from " + source.getUid() + " to " + target.getUid());
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
                        }
                    }
                }
            }
        }
//        }

        accountDAO.getCurrentSession().flush();

        log.info("Updating cache column for friendship");
        StringBuilder friendsCache = new StringBuilder();
        List<AccountFriendship> friendships = account.getAccountFriendshipsForAccountIdFrom();
        if (friendships.size() > 0) {
            for (int i = 0; i < friendships.size(); i++) {
                AccountFriendship friendship = friendships.get(i);
                log.info("Caching friendship {}", friendship.getUid());

                if (friendship.getAccountByAccountIdTo() != null) {
                    friendsCache.append(friendship.getAccountByAccountIdTo().getId());
                    friendsCache.append(",");
                }
            }
            if (friendsCache.length() > 0) {
                friendsCache.deleteCharAt(friendsCache.length() - 1);
            }
        }

        log.info("New cache value is {}", friendsCache.toString());

        List<AccountMeta> oldMeta = metaDAO.findByMwrenchByAccount(StgdrvData.AccountMetas.FRIENDS, account.getUid());
        if (!oldMeta.isEmpty()) {
            meta = oldMeta.get(0);
        }
        AccountMetaId accountMetaId = new AccountMetaId();
        accountMetaId.setAccountId(account.getId());

        if (meta == null) {
            meta = new AccountMeta();
            meta.setId(accountMetaId);
            meta.setUid(TokenUtils.generateUid());
            meta.setCreated(new Date());
            meta.setAccount(account);

            account.getAccountMetas().add(meta);
        }

        meta.setModified(new Date());
        meta.setMwrench(StgdrvData.AccountMetas.FRIENDS);
        meta.setMvalue(friendsCache.toString());
        metaDAO.create(meta);
        accountDAO.edit(account);

        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {

    }
}
