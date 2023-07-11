package com.stagedriving.modules.commons.queue;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.*;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.io.Closeable;
import java.io.IOException;

/**
 * A worker that runs a processing function on messages from a specific message queue.
 * Subclass this to create workers for specific tasks.
 *
 * Having this as a concrete class rather than passing the processing function directly to the
 * queue lets us make the link between queue and function explicit. It also allows the worker to be instantiated by
 * Guice/HK2 without TypeLiteral binding configuration craziness.
 *
 * @author Bo Gotthardt
 */
@Slf4j
public abstract class QueueWorker<T> implements Runnable {
    protected MessageQueue<T> queue;
    private Closeable cancel;

    @Inject
    private SessionFactory sessionFactory;
    protected Session session;

    protected boolean withoutTransaction;
    protected boolean withoutSession;
    protected boolean dontFinish;


    public QueueWorker() {
    }

    public void setup(MessageQueue<T> queue) {
        this.queue = queue;
    }

    protected String process(T message) throws Exception {
        return null;
    }

    private String doProcess(T message) {
        String result = null;
        String stacktrace = null;
        String res = null;

        if (!withoutSession && sessionFactory != null) {
            session = sessionFactory.openSession();
        }
        if (session != null) {
            session.setDefaultReadOnly(false);
            session.setCacheMode(CacheMode.NORMAL);
            session.setHibernateFlushMode(FlushMode.AUTO);
            ManagedSessionContext.bind(session);

            if (!withoutTransaction) {
                session.beginTransaction();
            }
        }
        try {

            res = process(message);

            if (session != null) {
                session.flush();
                if (!withoutTransaction) {
                    final Transaction txn = session.getTransaction();
                    if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                        txn.commit();
                    }
                }
                session.close();
                ManagedSessionContext.unbind(sessionFactory);
            }
        } catch (Exception e) {
            log.error("Oops", e);
            if (session != null) {
                final Transaction txn = session.getTransaction();
                if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                    txn.rollback();
                }
            }

            stacktrace = ExceptionUtils.getStackTrace(e);

        }

        return res;
    }

    @Override
    public void run() {
        cancel = queue.consume(this::doProcess);
    }

    public void cancel() {
        Preconditions.checkNotNull(cancel, "Cannot cancel before run.");
        try {
            cancel.close();
        } catch (IOException e) {
            throw new MessageQueueException("Unable to close.", e);
        }
    }
}
