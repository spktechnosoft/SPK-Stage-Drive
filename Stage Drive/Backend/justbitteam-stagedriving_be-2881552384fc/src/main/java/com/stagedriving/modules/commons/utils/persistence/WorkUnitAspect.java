package com.stagedriving.modules.commons.utils.persistence;

import com.stagedriving.Service;
import io.dropwizard.hibernate.HibernateBundle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.Map;

/**
 * An aspect providing operations around a method with the {@link WorkUnit} annotation.
 * It opens a Hibernate session and optionally a transaction.
 * <p>It should be created for every invocation of the method.</p>
 */
class WorkUnitAspect {

    private final Map<String, SessionFactory> sessionFactories;
    private CallbackManager callbackManager;

    public WorkUnitAspect(Map<String, SessionFactory> sessionFactories) {
        this.sessionFactories = sessionFactories;
        this.callbackManager = Service.getGuice().getInjector().getInstance(CallbackManager.class);
    }

    // Context variables
    private WorkUnit unitOfWork;
    private Session session;
    private SessionFactory sessionFactory;

    public void beforeStart(WorkUnit unitOfWork) {
        if (unitOfWork == null) {
            return;
        }
        this.unitOfWork = unitOfWork;

        sessionFactory = sessionFactories.get(unitOfWork.value());
        if (sessionFactory == null) {
            // If the user didn't specify the name of a session factory,
            // and we have only one registered, we can assume that it's the right one.
            if (unitOfWork.value().equals(HibernateBundle.DEFAULT_NAME) && sessionFactories.size() == 1) {
                sessionFactory = sessionFactories.values().iterator().next();
            } else {
                throw new IllegalArgumentException("Unregistered Hibernate bundle: '" + unitOfWork.value() + "'");
            }
        }
        session = sessionFactory.openSession();
        try {
            configureSession();
            ManagedSessionContext.bind(session);
            beginTransaction();
        } catch (Throwable th) {
            session.close();
            session = null;
            ManagedSessionContext.unbind(sessionFactory);
            throw th;
        }
    }

    public void afterEnd() {
        if (session == null) {
            return;
        }

        try {
            commitTransaction();

            if (unitOfWork.callback()) {
                callbackManager.processCallbacks();
            }
        } catch (Exception e) {
            rollbackTransaction();
            throw e;
        } finally {
            session.close();
            session = null;
            ManagedSessionContext.unbind(sessionFactory);
        }

    }

    public void onError() {
        if (session == null) {
            return;
        }

        try {
            rollbackTransaction();
        } finally {
            session.close();
            session = null;
            ManagedSessionContext.unbind(sessionFactory);
        }
    }

    private void configureSession() {
        session.setDefaultReadOnly(unitOfWork.readOnly());
        session.setCacheMode(unitOfWork.cacheMode());
        session.setFlushMode(unitOfWork.flushMode());
    }

    private void beginTransaction() {
        if (!unitOfWork.transactional()) {
            return;
        }
        session.beginTransaction();
    }

    private void rollbackTransaction() {
        /*if (!unitOfWork.transactional()) {
            return;
        }*/
        final Transaction txn = session.getTransaction();
        if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
            txn.rollback();
        }
    }

    private void commitTransaction() {
        if (!unitOfWork.transactional()) {
            return;
        }
        final Transaction txn = session.getTransaction();
        if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
            txn.commit();
        }
    }
}