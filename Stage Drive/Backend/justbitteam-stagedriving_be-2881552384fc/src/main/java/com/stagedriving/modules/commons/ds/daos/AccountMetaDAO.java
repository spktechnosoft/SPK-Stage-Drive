package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractAccountMetaDAO;
import com.stagedriving.modules.commons.ds.entities.AccountMeta;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class AccountMeta.
 *
 * @author Hibernate Tools
 */

@Singleton
public class AccountMetaDAO extends AbstractAccountMetaDAO {

    private final Logger log = new LoggerContext().getLogger(AccountMetaDAO.class);

    @Inject
    public AccountMetaDAO(/*@Named("datastore1") */SessionFactory session) {
        super(session);
    }

    public List<AccountMeta> findByMwrenchByAccount(String mwrench, String accountId) {

        List<AccountMeta> metas = null;

        StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM AccountMeta acc ");

        //Query conditions
        queryBuilder.append("WHERE acc.mwrench = :mwrench AND acc.account.uid = :accountId");

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());
            query.setParameter("mwrench", mwrench);
            query.setParameter("accountId", accountId);
            query.setCacheable(true);
            metas = (List<AccountMeta>) query.list();
        } catch (Exception ex) {
        } finally {
            return metas;
        }
    }

}

