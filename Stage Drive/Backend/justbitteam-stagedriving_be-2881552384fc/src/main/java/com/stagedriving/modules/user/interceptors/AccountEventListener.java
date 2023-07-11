package com.stagedriving.modules.user.interceptors;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.justbit.sque.SqueController;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountFriendshipDAO;
import com.stagedriving.modules.commons.ds.daos.AccountMetaDAO;
import com.stagedriving.modules.commons.ds.daos.AccountReviewDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountMeta;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.user.views.NewAccountEmailNotificationView;
import com.stagedriving.modules.user.workers.model.FriendshipMatchWorkerEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class AccountEventListener extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener {

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private AccountReviewDAO accountReviewDAO;
    @Inject
    private NotificationController notificationController;
    @Inject
    private AccountFriendshipDAO friendshipDAO;
    @Inject
    private AccountMetaDAO metaDAO;
    @Inject
    private SqueController squeController;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        process(postInsertEvent.getEntity(), postInsertEvent, null);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        process(postUpdateEvent.getEntity(), null, postUpdateEvent);
    }

    @SneakyThrows
    private void process(Object object, PostInsertEvent postInsertEvent, PostUpdateEvent postUpdateEvent) {
        if (object instanceof Account && postInsertEvent != null) {
            Account account = (Account) object;
            log.info("Account created for " + account.getEmail());

            NewAccountEmailNotificationView emailNotificationView = new NewAccountEmailNotificationView();
            emailNotificationView.setSubject("Benvenuto a bordo di Stage Driving!");
            emailNotificationView.setUser(account);
            notificationController.sendEmailNotification("me", ImmutableList.of(account.getEmail()), emailNotificationView);
        } else if (object instanceof AccountMeta && (postInsertEvent != null || postUpdateEvent != null)) {
            AccountMeta meta = (AccountMeta) object;

            if (!meta.getMwrench().equals(StgdrvData.AccountMetas.PHONEBOOK)) {
                return;
            }

            FriendshipMatchWorkerEvent friendshipMatchWorkerEvent = new FriendshipMatchWorkerEvent();
            friendshipMatchWorkerEvent.setId(meta.getUid());
            squeController.enqueue(friendshipMatchWorkerEvent, "commons", 3600);
        }
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent postInsertEvent) {
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return true;
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent postUpdateEvent) {
    }
}
