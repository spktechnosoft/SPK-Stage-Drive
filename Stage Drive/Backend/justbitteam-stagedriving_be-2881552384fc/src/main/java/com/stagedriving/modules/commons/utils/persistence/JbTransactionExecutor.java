package com.stagedriving.modules.commons.utils.persistence;

import com.google.inject.Inject;
import org.hibernate.*;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Created with IntelliJ IDEA.
 * User: simone
 * Date: 26/07/13
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class JbTransactionExecutor {

    @Inject
    private SessionFactory sessionFactory;

    public interface Operation {
        public Object run() throws Exception;
    }

    public Object doTransaction(Operation operation) throws Exception {
        Object ret = null;
        final Session session = sessionFactory.openSession();
        try {
            session.setDefaultReadOnly(false);
            session.setCacheMode(CacheMode.NORMAL);
            session.setFlushMode(FlushMode.AUTO);
            ManagedSessionContext.bind(session);
            session.beginTransaction();
            try {

                ret = operation.run();

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

            return ret;
        }
    }

}
