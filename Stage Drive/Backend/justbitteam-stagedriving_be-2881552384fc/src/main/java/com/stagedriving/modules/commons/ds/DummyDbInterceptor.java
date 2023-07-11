package com.stagedriving.modules.commons.ds;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by simone on 05/02/17.
 */
public class DummyDbInterceptor extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        return super.onSave(entity, id, state, propertyNames, types);
    }

    @Override
    public void postFlush(Iterator entities) {
        super.postFlush(entities);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        if ( tx.getStatus().isOneOf(TransactionStatus.COMMITTED) ) {
            System.out.println("Save ");
        }
    }
}
