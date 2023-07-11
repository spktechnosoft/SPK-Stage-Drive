package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.model.AbstractAccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class Account.
 *
 * @author Hibernate Tools
 */

@Singleton
public class AccountDAO extends AbstractAccountDAO {

    private final Logger log = new LoggerContext().getLogger(AccountDAO.class);

    @Inject
    public AccountDAO(/*@Named("datastore1") */SessionFactory session) {
        super(session);
    }

    public List<Account> findAllFiltered(String q, String role, String idLike, String emailLike, String firstnameLike, String lastnameLike, String genderLike, String groupsLike, String favCategoryLike, String favStyleLike, String sort, String order, Integer page, Integer limit, PagedResults<Account> results) {

        List<Account> accounts = null;

        StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Account acc ");

        if (groupsLike != null) {
            queryBuilder.append(", AccountHasGroup grp ");
        }

        queryBuilder.append(" WHERE 1 = 1 AND acc.status <> 'inactive' ");
        if (q != null) {
            q = q.replaceAll("-deleted", "");
            queryBuilder.append(" AND (LOWER(acc.email) LIKE LOWER(:query) OR LOWER(acc.firstname) LIKE LOWER(:query) OR LOWER(acc.lastname) LIKE LOWER(:query)) ");
        }
        if (role != null) {
            queryBuilder.append(" AND acc.role = :role ");
        }

        if (idLike != null) {
            queryBuilder.append(" AND (LOWER(acc.uid) LIKE LOWER(:idLike)) ");
        }
        if (emailLike != null) {
            queryBuilder.append(" AND (LOWER(acc.email) LIKE LOWER(:emailLike)) ");
        }
        if (firstnameLike != null) {
            queryBuilder.append(" AND (LOWER(acc.firstname) LIKE LOWER(:firstnameLike)) ");
        }
        if (lastnameLike != null) {
            queryBuilder.append(" AND (LOWER(acc.lastname) LIKE LOWER(:lastnameLike)) ");
        }
        if (favCategoryLike != null) {
            queryBuilder.append(" AND (LOWER(acc.favCategory) LIKE LOWER(:favCategoryLike)) ");
        }
        if (favStyleLike != null) {
            queryBuilder.append(" AND (LOWER(acc.favStyle) LIKE LOWER(:favStyleLike)) ");
        }
        if (genderLike != null) {
            queryBuilder.append(" AND (LOWER(acc.gender) = LOWER(:genderLike)) ");
        }
        if (groupsLike != null) {
            queryBuilder.append(" AND (acc = grp.account AND grp.accountGroup.name LIKE LOWER(:groupsLike)) ");
        }

        if (sort != null) {
            queryBuilder.append(" ORDER BY acc."+sort+" ");
        }
        if (order != null) {
            queryBuilder.append(order);
        }

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());

            if (q != null) {
                query.setParameter("query", "%"+q+"%");
            }
            if (role != null) {
                query.setParameter("role", role);
            }
            if (idLike != null) {
                query.setParameter("idLike", "%"+idLike+"%");
            }
            if (emailLike != null) {
                query.setParameter("emailLike", "%"+emailLike+"%");
            }
            if (firstnameLike != null) {
                query.setParameter("firstnameLike", "%"+firstnameLike+"%");
            }
            if (lastnameLike != null) {
                query.setParameter("lastnameLike", "%"+lastnameLike+"%");
            }
            if (genderLike != null) {
                query.setParameter("genderLike", genderLike);
            }
            if (favCategoryLike != null) {
                query.setParameter("favCategoryLike", favCategoryLike);
            }
            if (favStyleLike != null) {
                query.setParameter("favStyleLike", favStyleLike);
            }
            if (groupsLike != null) {
                query.setParameter("groupsLike", "%"+groupsLike+"%");
            }

            if (page == null) {
                page = 0;
            }
            if (limit == null) {
                limit = 20;
            }

            if (results != null) {
                fillResults(results, query, page, limit);
            }

            query.setFirstResult(page * limit);
            query.setMaxResults(limit);

            query.setCacheable(true);
            accounts = query.list();

        } catch (Exception ex) {
            log.error("Oops", ex);
        } finally {
            return accounts;
        }
    }

    public Account findByEmailByPassword(String email, String password) {

        Account account = null;

        StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Account acc ");

        queryBuilder.append(" WHERE acc.email = :email ");
        queryBuilder.append(" AND acc.password = :password ");

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());
            query.setParameter("email", email);
            query.setParameter("password", password);
            query.setCacheable(true);
            account = (Account) query.uniqueResult();
        } catch (Exception ex) {
        } finally {
            return account;
        }
    }

    public List<Account> findByRoleByVisible(String role, boolean visible) {

        List<Account> accounts = null;

        StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Account acc ");

        queryBuilder.append(" WHERE acc.role = :role ");
        queryBuilder.append(" AND acc.visible = :visible ");

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());
            query.setParameter("role", role);
            query.setParameter("visible", visible);
            query.setCacheable(true);
            accounts = (List<Account>) query.list();
        } catch (Exception ex) {
        } finally {
            return accounts;
        }
    }

    public List<Account> findByRoleByStatus(String role, String status) {

        List<Account> accounts = null;

        StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Account acc ");

        queryBuilder.append(" WHERE acc.role = :role ");
        queryBuilder.append(" AND acc.status = :status ");

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());
            query.setParameter("role", role);
            query.setParameter("status", status);
            query.setCacheable(true);
            accounts = (List<Account>) query.list();
        } catch (Exception ex) {
        } finally {
            return accounts;
        }
    }

}

