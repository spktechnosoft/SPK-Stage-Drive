package com.stagedriving.modules.notification.sender;

import com.google.inject.Inject;
import com.justbit.pusher.PusherEvent;
import com.justbit.pusher.PusherListener;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountDeviceDAO;
import com.stagedriving.modules.commons.ds.daos.NotificationDAO;
import com.stagedriving.modules.commons.ds.daos.NotificationRecipientDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountDevice;
import com.stagedriving.modules.commons.ds.entities.NotificationRecipient;
import com.stagedriving.modules.notification.controllers.NotificationController;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.*;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.Date;
import java.util.List;

@Slf4j
public class PushFeedbackListener implements PusherListener {

    @Inject
    private NotificationDAO notificationDAO;
    @Inject
    private NotificationRecipientDAO notificationRecipientDAO;
    @Inject
    private AccountDeviceDAO accountDeviceDAO;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private SessionFactory sessionFactory;

    private Session beginSession() {
        final Session session = sessionFactory.openSession();
        session.setDefaultReadOnly(false);
        session.setCacheMode(CacheMode.NORMAL);
        session.setFlushMode(FlushMode.AUTO);
        ManagedSessionContext.bind(session);
        session.beginTransaction();

        return session;
    }

    private void endSession(Session session) {
        final Transaction txn = session.getTransaction();
        if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
            txn.commit();
        }

        session.close();
        ManagedSessionContext.unbind(sessionFactory);
    }

    private void rollback(Session session) {
        final Transaction txn = session.getTransaction();
        if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
            txn.rollback();
        }

        session.close();
        ManagedSessionContext.unbind(sessionFactory);
    }

    @Override
    public void messageSent(PusherEvent pusherEvent, boolean b) {
        try {

            if (pusherEvent != null && pusherEvent.getId() != null) {
                NotificationRecipient notificationRecipient = notificationRecipientDAO.findByUid(pusherEvent.getId());
                if (notificationRecipient != null && notificationRecipient.getDest() != null) {
                    log.info("Push Notification sent to " + notificationRecipient.getDest());

//                    notificationRecipient.setExtId(pusherEvent.getExtId());
                    notificationRecipient.setStatus(NotificationController.NotificationRecipientStatusses.SENT.toString());
                    notificationRecipient.setModified(new Date());

                    notificationRecipientDAO.edit(notificationRecipient);
                } else {
                    log.warn("Notification " + pusherEvent.getId() + " not found!");
                }
            }
        } catch (Exception ex) {
            log.error("Oops!", ex);
        }
    }

    @Override
    public void messageSendFailed(PusherEvent pusherEvent, Throwable throwable, Object o) {
        try {

            if (pusherEvent != null && pusherEvent.getId() != null) {
                NotificationRecipient notificationRecipient = notificationRecipientDAO.findByUid(pusherEvent.getId());
                if (notificationRecipient != null && notificationRecipient.getDest() != null) {
                    log.info("Push Notification failed to " + notificationRecipient.getDest());

//                    notificationRecipient.setExtId(pusherEvent.getExtId());
                    notificationRecipient.setStatus(NotificationController.NotificationRecipientStatusses.FAILED.toString());
                    notificationRecipient.setStatusDescription(throwable.getMessage());
                    notificationRecipient.setModified(new Date());

                    notificationRecipientDAO.edit(notificationRecipient);

                    // Delete corresponding device
                    List<AccountDevice> deviceList = accountDeviceDAO.findByDeviceid(notificationRecipient.getDest());
                    for (int i=0; i<deviceList.size(); i++) {
                        AccountDevice device = deviceList.get(i);
                        log.info("Disabling unreachable device "+device.getDeviceid()+" of user "+device.getAccount().getEmail());

                        Account account = device.getAccount();
                        account.getAccountDevices().remove(device);

                        accountDeviceDAO.delete(device);
                        accountDAO.edit(account);
                    }

                } else {
                    log.warn("Notification " + pusherEvent.getId() + " not found!");
                }
            }
        } catch (Exception ex) {
            log.error("Oops!", ex);
        }
    }

    @Override
    public void connectionClosed(PusherEvent pusherEvent) {

    }

    @Override
    public void cacheLengthExceeded(int i) {

    }

    @Override
    public void notificationsResent(int i) {

    }
}
