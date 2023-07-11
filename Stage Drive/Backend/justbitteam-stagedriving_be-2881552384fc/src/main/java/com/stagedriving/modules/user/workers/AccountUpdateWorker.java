package com.stagedriving.modules.user.workers;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.queue.QueueWorker;
import com.stagedriving.modules.commons.queue.WorkerMessage;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.user.controller.AccountController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountUpdateWorker extends QueueWorker<WorkerMessage> {


    @Inject
    private AccountDAO accountDAO;
    @Inject
    private NotificationController notificationController;
    @Inject
    private AccountController accountGroupController;

    @Inject
    public AccountUpdateWorker() {
        super();
    }

    @Override
    protected String process(WorkerMessage message) throws Exception {
        String accountId = message.getId();
        Account account = accountDAO.findByUid(accountId);
        log.info("Account updated for "+account.getEmail());

//        accountGroupController.checkForRole(account);

        return "ok";
    }
}
