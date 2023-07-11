/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.utils.persistence;

import com.google.inject.Inject;
import org.hibernate.*;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 *
 * @author simone
 */
public abstract class ScxTransactionAbstract<R> {

    @Inject
    private SessionFactory sessionFactory;
    
    public R run() throws Exception {
        final Session session = sessionFactory.openSession();
        R ret;
        try {
            session.setDefaultReadOnly(false);
            session.setCacheMode(CacheMode.NORMAL);
            session.setFlushMode(FlushMode.AUTO);
            ManagedSessionContext.bind(session);
            session.beginTransaction();
            try {

                ret = this.doTransaction(session);

                final Transaction txn = session.getTransaction();
                if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                    txn.commit();
                }
            } catch (Exception e) {
                final Transaction txn = session.getTransaction();
                if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                    txn.rollback();
                }

                throw e;
            }
        } finally {
            session.close();
            ManagedSessionContext.unbind(sessionFactory);
        }

        return ret;

    }

    protected abstract R doTransaction(Session session);
}
