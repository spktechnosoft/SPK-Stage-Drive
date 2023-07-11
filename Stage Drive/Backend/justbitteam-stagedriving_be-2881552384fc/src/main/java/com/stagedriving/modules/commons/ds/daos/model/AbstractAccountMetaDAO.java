package com.stagedriving.modules.commons.ds.daos.model;
// Generated 12-giu-2020 12.45.52 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.entities.*;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Home object for domain model class AccountMeta.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountMetaDAO extends AbstractDAO<AccountMeta> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountMetaDAO.class);

	public AbstractAccountMetaDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountMeta> findByAccount(Account account) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByAccount");
		return list(q.setParameter("account", account).setCacheable(true));
	}
	public AccountMeta findByUid(String uid) {
		return uniqueResult(namedQuery("AccountMeta.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountMeta> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountMeta> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountMeta> findByMwrench(String mwrench) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByMwrench");
		return list(q.setParameter("mwrench", mwrench).setCacheable(true));
	}
	public List<AccountMeta> findByMvalue(String mvalue) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByMvalue");
		return list(q.setParameter("mvalue", mvalue).setCacheable(true));
	}


	public List<AccountMeta> findByAccountPaged(Account account, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountMeta.findByAccount");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("account", account).setCacheable(true));
	}

	public List<AccountMeta> findByAccountPaged(Account account, Integer page, Integer limit, PagedResults<AccountMeta> results) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByAccount");
        q.setParameter("account", account).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<AccountMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountMeta> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountMeta.findByCreated");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("created", created).setCacheable(true));
	}

	public List<AccountMeta> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountMeta> results) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByCreated");
        q.setParameter("created", created).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<AccountMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountMeta> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountMeta.findByModified");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("modified", modified).setCacheable(true));
	}

	public List<AccountMeta> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountMeta> results) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByModified");
        q.setParameter("modified", modified).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<AccountMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountMeta> findByMwrenchPaged(String mwrench, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountMeta.findByMwrench");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("mwrench", mwrench).setCacheable(true));
	}

	public List<AccountMeta> findByMwrenchPaged(String mwrench, Integer page, Integer limit, PagedResults<AccountMeta> results) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByMwrench");
        q.setParameter("mwrench", mwrench).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<AccountMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountMeta> findByMvaluePaged(String mvalue, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountMeta.findByMvalue");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("mvalue", mvalue).setCacheable(true));
	}

	public List<AccountMeta> findByMvaluePaged(String mvalue, Integer page, Integer limit, PagedResults<AccountMeta> results) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findByMvalue");
        q.setParameter("mvalue", mvalue).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<AccountMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountMeta> findAll() {
		return list(namedQuery("AccountMeta.findAll").setCacheable(true));
	}

	public List<AccountMeta> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findAll");
        q.setCacheable(true);

        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<AccountMeta> res = list(q);

        return res;
    }

	public List<AccountMeta> findAllPaged(Integer page, Integer limit, PagedResults<AccountMeta> results) {
        org.hibernate.query.Query q = namedQuery("AccountMeta.findAll");
        q.setCacheable(true);

        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<AccountMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }


	public Session getCurrentSession() {
        return currentSession();
    }

	public void beginTransaction() {
        currentSession().beginTransaction();
    }

    public void endTransaction() {
        final Transaction txn = currentSession().getTransaction();
            if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                txn.commit();
            }
    }

	public void delete(AccountMeta transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountMeta transientInstance) {
        //log.debug("editing AccountMeta instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountMeta transientInstance) {
        //log.debug("creating AccountMeta instance");
        try {
            persist(transientInstance);
            //log.debug("create successful");
        }
        catch (RuntimeException re) {
            log.error("create failed", re);
            throw re;
        }
    }

    public int fillResults(PagedResults results, Query query, String page, String limit) {
        if (results != null) {
            ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
            scrollableResults.last();
            results.setSize(scrollableResults.getRowNumber() + 1);

            if (page != null) {
                results.setPage(Integer.valueOf(page));
            }
            if (limit != null) {
                results.setLimit(Integer.valueOf(limit));
            }

            return results.getSize();
        }

        return -1;
    }

    public int fillResults(PagedResults results, Query query, Integer page, Integer limit) {
        if (results != null) {
            ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
            scrollableResults.last();
            results.setSize(scrollableResults.getRowNumber() + 1);

            if (page != null) {
                results.setPage(page);
            }
            if (limit != null) {
                results.setLimit(limit);
            }

            return results.getSize();
        }

        return -1;
    }
    
    public AccountMeta findById( AccountMetaId id) {
        //log.debug("getting AccountMeta instance with id: " + id);
        try {
            AccountMeta instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

