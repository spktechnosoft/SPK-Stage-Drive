package com.stagedriving.modules.notification.sender;

import com.google.inject.Inject;
import com.justbit.mailer.MailerListener;
import com.justbit.mailer.model.MailerEvent;
import com.stagedriving.modules.commons.ds.daos.NotificationDAO;
import com.stagedriving.modules.commons.ds.daos.NotificationRecipientDAO;
import com.stagedriving.modules.commons.ds.entities.NotificationRecipient;
import com.stagedriving.modules.notification.controllers.NotificationController;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.*;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.Date;

@Slf4j
public class EmailFeedbackListener implements MailerListener {

    @Inject
    private NotificationDAO notificationDAO;
    @Inject
    private NotificationRecipientDAO notificationRecipientDAO;
    @Inject
    private SessionFactory sessionFactory;

    private Session beginSession() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        final Session session = sessionFactory.openSession();
        session.setDefaultReadOnly(false);
        session.setCacheMode(CacheMode.NORMAL);
        session.setFlushMode(FlushMode.AUTO);
        ManagedSessionContext.bind(session);
//        session.clear();
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
    public Boolean canSendMessage(MailerEvent message) {
//        ScxEmailBlacklist emailBlacklist = emailBlacklistDAO.findByEmail(message.getTo());
//        if (emailBlacklist != null) {
//
//            if (emailBlacklist.getType().equals(SesNotification.Types.BOUNCE)) {
//                this.messageBounced(message, "Email address already bounced");
//                return false;
//            }
//
//            if (emailBlacklist.getType().equals(SesNotification.Types.COMPLAINT)) {
//                this.messageComplained(message, "Email address already complained");
//                return false;
//            }
//
//        }
//
//        ScxNotification notification = notificationDAO.findByUid(message.getId());
//        if (notification != null && notification.getDst() != null) {
//
//            if (!notification.getStatus().equals(ScxData.NotificationStatus.WAIT)) {
//                LOGGER.warn("Duplicate", "The email " + message.getId() + " was already sent, skipping...");
//                return false;
//            }
//        }

        return true;
    }

    @Override
    public void messageSending(MailerEvent message) {
//        Session session = null;
//        try {
//            session = beginSession();
//            if (message != null && message.getId() != null) {
//
//            }
//            endSession(session);
//        } catch (Exception ex) {
//            rollback(session);
//        }
    }

    @Override
    public void messageSent(MailerEvent message, boolean resent) {
        //Session session = null;
        try {
            //session = beginSession();

            if (message != null && message.getId() != null) {
                NotificationRecipient notificationRecipient = notificationRecipientDAO.findByUid(message.getId());
                if (notificationRecipient != null && notificationRecipient.getDest() != null) {
                    log.info("Email Notification sent to " + notificationRecipient.getDest());

                    notificationRecipient.setExtId(message.getExtId());
                    notificationRecipient.setStatus(NotificationController.NotificationRecipientStatusses.SENT.toString());
                    notificationRecipient.setModified(new Date());

//                    if (message.getExtId() != null) {
//                        ScxNotificationExtMapping extMapping = new ScxNotificationExtMapping();
//                        extMapping.setUid(message.getExtId());
//                        extMapping.setScxNotification(notification);
//                        notification.getScxNotificationExtMappings().add(extMapping);
//
//                        notificationExtMappingDAO.edit(extMapping);
//                    }

                    notificationRecipientDAO.edit(notificationRecipient);
                } else {
                    log.warn("Notification " + message.getId() + " not found!");
                }
            }
            //endSession(session);
        } catch (Exception ex) {
            log.error("Oops!", ex);
         //   rollback(session);
        }
    }

    @Override
    public void messageSendFailed(MailerEvent message, Throwable e) {
//Session session = null;
        try {
            //session = beginSession();

            if (message != null && message.getId() != null) {
                NotificationRecipient notificationRecipient = notificationRecipientDAO.findByUid(message.getId());
                if (notificationRecipient != null && notificationRecipient.getDest() != null) {
                    log.info("Unable to send notification to " + notificationRecipient.getDest());

                    notificationRecipient.setExtId(message.getExtId());
                    notificationRecipient.setStatus(NotificationController.NotificationRecipientStatusses.FAILED.toString());
                    notificationRecipient.setStatusDescription(e.getMessage());
                    notificationRecipient.setModified(new Date());

//                    if (message.getExtId() != null) {
//                        ScxNotificationExtMapping extMapping = new ScxNotificationExtMapping();
//                        extMapping.setUid(message.getExtId());
//                        extMapping.setScxNotification(notification);
//                        notification.getScxNotificationExtMappings().add(extMapping);
//
//                        notificationExtMappingDAO.edit(extMapping);
//                    }

                    notificationRecipientDAO.edit(notificationRecipient);
                } else {
                    log.warn("Notification " + message.getId() + " not found!");
                }
            }
            //endSession(session);
        } catch (Exception ex) {
            log.error("Oops!", ex);
            //   rollback(session);
        }
    }

    @Override
    public void messageBounced(MailerEvent message, String bounceType) {
//        Session session = null;
//        try {
//            session = beginSession();
//            if (message != null && message.getExtId() != null) {
//
//                List<ScxNotificationExtMapping> notification = notificationExtMappingDAO.findByUid(message.getExtId());
//                if (!notification.isEmpty()) {
//                    for (ScxNotificationExtMapping notificationExtMapping : notification) {
//                        ScxNotification notification = notificationExtMapping.getScxNotification();
//                        if (notification != null && notification.getDst() != null) {
//                            LOGGER.info("Email Notification bounced to " + notification.getDst());
//
//                            notification.setStatus(ScxData.NotificationStatus.BOUNCED);
//                            notification.setStatusDescription(bounceType);
//                            notification.setModified(new Date());
//
//                            if (bounceType != null && bounceType.equals("Permanent")) {
//
//                                if (notification.getScxAccount() != null && notification.getScxAccount().getEmail() != null) {
//                                    List<ScxAccount> accounts = accountDAO.findByEmail(notification.getScxAccount().getEmail());
//                                    for (ScxAccount account : accounts) {
//                                        if (account != null) {
//                                            account.setSubscriptionStatus(ScxData.SubscriptionStatuses.BOUNCE);
//                                            accountDAO.edit(account);
//                                        }
//                                    }
//                                }
//
//                                ScxEmailBlacklist email = emailBlacklistDAO.findByEmail(notification.getDst());
//
//                                if (email == null) {
//                                    email = new ScxEmailBlacklist();
//
//                                    email.setEmail(notification.getDst());
//                                    email.setType(ScxData.NotificationStatus.BOUNCED);
//                                    email.setCounter(0);
//                                }
//
//                                email.setCreationTime(DateUtils.dateToString(new Date()));
//                                email.setCounter(email.getCounter() + 1);
//                                if (email.getStatus() == null || (!email.getStatus().equals("Permanent"))) {
//                                    email.setStatus(bounceType);
//                                }
//
//                                emailBlacklistDAO.save(email);
//                            }
//
//                            notificationDAO.edit(notification);
//                        }
//                    }
//                }
//            }
//            endSession(session);
//        } catch (Exception ex) {
//            LOGGER.error("Oops!", ex);
//            rollback(session);
//        }
    }

    @Override
    public void messageComplained(MailerEvent message, String complaintType) {
//        Session session = null;
//        try {
//            session = beginSession();
//            if (message != null && message.getExtId() != null) {
//
//                List<ScxNotificationExtMapping> notification = notificationExtMappingDAO.findByUid(message.getExtId());
//                if (!notification.isEmpty()) {
//                    for (ScxNotificationExtMapping notificationExtMapping : notification) {
//                        ScxNotification notification = notificationExtMapping.getScxNotification();
//                        if (notification != null && notification.getDst() != null) {
//                            LOGGER.info("Email Notification complained to send to " + notification.getDst());
//
//                            notification.setStatus(ScxData.NotificationStatus.COMPLAINED);
//                            notification.setStatusDescription(complaintType);
//                            notification.setModified(new Date());
//
//                            ScxEmailBlacklist email = emailBlacklistDAO.findByEmail(notification.getDst());
//
//                            if (email == null) {
//                                email = new ScxEmailBlacklist();
//
//                                email.setEmail(notification.getDst());
//                                email.setType(ScxData.NotificationStatus.COMPLAINED);
//                                email.setCounter(0);
//                            }
//
//                            if (notification.getScxAccount() != null && notification.getScxAccount().getEmail() != null) {
//                                List<ScxAccount> accounts = accountDAO.findByEmail(notification.getScxAccount().getEmail());
//                                for (ScxAccount account : accounts) {
//                                    if (account != null) {
//                                        account.setSubscriptionStatus(ScxData.SubscriptionStatuses.COMPLAIN);
//                                        accountDAO.edit(account);
//                                    }
//                                }
//                            }
//
//                            email.setCreationTime(DateUtils.dateToString(new Date()));
//                            email.setCounter(email.getCounter() + 1);
//                            email.setStatus(complaintType);
//
//                            emailBlacklistDAO.save(email);
//
//                            notificationDAO.edit(notification);
//                        }
//                    }
//                }
//
//            }
//            endSession(session);
//        } catch (Exception ex) {
//            LOGGER.error("Oops!", ex);
//            rollback(session);
//        }
    }

    @Override
    public void messageDelivered(MailerEvent message) {
        Session session = null;
        try {
            session = beginSession();
            if (message != null && message.getExtId() != null) {
                NotificationRecipient notificationRecipient = notificationRecipientDAO.findByExtId(message.getExtId());
                if (notificationRecipient != null && notificationRecipient.getDest() != null) {
                    log.info("Email Notification sent to " + notificationRecipient.getDest());

                    notificationRecipient.setExtId(message.getExtId());
                    notificationRecipient.setStatus(NotificationController.NotificationRecipientStatusses.DELIVERED.toString());
                    notificationRecipient.setModified(new Date());

//                    if (message.getExtId() != null) {
//                        ScxNotificationExtMapping extMapping = new ScxNotificationExtMapping();
//                        extMapping.setUid(message.getExtId());
//                        extMapping.setScxNotification(notification);
//                        notification.getScxNotificationExtMappings().add(extMapping);
//
//                        notificationExtMappingDAO.edit(extMapping);
//                    }

                    notificationRecipientDAO.edit(notificationRecipient);
                } else {
                    log.warn("Notification " + message.getExtId() + " not found!");
                }
            }
            endSession(session);
        } catch (Exception ex) {
            log.error("Oops!", ex);
            rollback(session);
        }
    }

    @Override
    public void messageReceived(MailerEvent message) {

    }

    @Override
    public void cacheLengthExceeded(int newCacheLength) {
        log.warn("CacheLengthExceeded " + newCacheLength);
    }

    @Override
    public void notificationsResent(int resendCount) {
        log.warn("NotificationsResent " + resendCount);
    }
}
