package com.stagedriving.modules.user.workers;

import com.codahale.metrics.MetricRegistry;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.queue.QueueWorker;
import com.stagedriving.modules.commons.queue.WorkerMessage;
import com.stagedriving.modules.commons.utils.MetricsHelper;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.user.controller.AccountController;
import com.stagedriving.modules.user.views.NewAccountEmailNotificationView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccountCreationWorker extends QueueWorker<WorkerMessage> {


    @Inject
    private AccountDAO accountDAO;
    @Inject
    private NotificationController notificationController;
    @Inject
    private AccountController accountGroupController;
    @Inject
    private MetricsHelper metricsHelper;

    @Inject
    public AccountCreationWorker(MetricRegistry metrics) {
        super();


    }

    @Override
    protected String process(WorkerMessage message) throws Exception {
        String accountId = message.getId();
        Account account = accountDAO.findByUid(accountId);
        log.info("Account created for "+account.getEmail());

//        ResetOnSnapshotCounter maleGender = metricsHelper.resetOnSnapshotCounter(Account.class, "gender", "male");
//        ResetOnSnapshotCounter femaleGender = metricsHelper.resetOnSnapshotCounter(Account.class, "gender", "female");
//
//        if (account.getGender() != null) {
//            if (account.getGender().equals(StgdrvData.AccountGenders.MALE)) {
//                maleGender.add(1);
//            } else if (account.getGender().equals(StgdrvData.AccountGenders.FEMALE)) {
//                femaleGender.add(1);
//            }
//        }

        NewAccountEmailNotificationView emailNotificationView = new NewAccountEmailNotificationView();
        emailNotificationView.setSubject("Benvenuto a bordo di Stage Driving!");
        emailNotificationView.setUser(account);
        notificationController.sendEmailNotification("me", ImmutableList.of(account.getEmail()), emailNotificationView);

//        NewAccountPushNotificationView pushNotificationView = new NewAccountPushNotificationView();
//        pushNotificationView.setTitle("Nuovo account");
//        Map<String, String> data = ImmutableMap.of("data", "value");
//        pushNotificationView.setData(data);
//        notificationController.sendPushNotification(ImmutableList.of(account.getEmail()), pushNotificationView);

        return "ok";
    }
}
