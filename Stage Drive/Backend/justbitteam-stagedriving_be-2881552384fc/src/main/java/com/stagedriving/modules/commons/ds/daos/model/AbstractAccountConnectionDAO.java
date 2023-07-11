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
 * Home object for domain model class AccountConnection.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountConnectionDAO extends AbstractDAO<AccountConnection> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountConnectionDAO.class);

	public AbstractAccountConnectionDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountConnection> findByAccount(Account account) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByAccount");
		return list(q.setParameter("account", account).setCacheable(true));
	}
	public AccountConnection findByUid(String uid) {
		return uniqueResult(namedQuery("AccountConnection.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountConnection> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountConnection> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountConnection> findByProvider(String provider) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByProvider");
		return list(q.setParameter("provider", provider).setCacheable(true));
	}
	public List<AccountConnection> findByToken(String token) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByToken");
		return list(q.setParameter("token", token).setCacheable(true));
	}
	public List<AccountConnection> findByExpires(Date expires) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByExpires");
		return list(q.setParameter("expires", expires).setCacheable(true));
	}
	public List<AccountConnection> findByRefresh(String refresh) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByRefresh");
		return list(q.setParameter("refresh", refresh).setCacheable(true));
	}
	public List<AccountConnection> findByCode(String code) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByCode");
		return list(q.setParameter("code", code).setCacheable(true));
	}


	public List<AccountConnection> findByAccountPaged(Account account, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByAccount");

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

	public List<AccountConnection> findByAccountPaged(Account account, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByAccount");
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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountConnection> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByCreated");

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

	public List<AccountConnection> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByCreated");
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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountConnection> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByModified");

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

	public List<AccountConnection> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByModified");
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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountConnection> findByProviderPaged(String provider, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByProvider");

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

		return list(q.setParameter("provider", provider).setCacheable(true));
	}

	public List<AccountConnection> findByProviderPaged(String provider, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByProvider");
        q.setParameter("provider", provider).setCacheable(true);

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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountConnection> findByTokenPaged(String token, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByToken");

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

		return list(q.setParameter("token", token).setCacheable(true));
	}

	public List<AccountConnection> findByTokenPaged(String token, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByToken");
        q.setParameter("token", token).setCacheable(true);

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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountConnection> findByExpiresPaged(Date expires, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByExpires");

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

		return list(q.setParameter("expires", expires).setCacheable(true));
	}

	public List<AccountConnection> findByExpiresPaged(Date expires, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByExpires");
        q.setParameter("expires", expires).setCacheable(true);

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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountConnection> findByRefreshPaged(String refresh, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByRefresh");

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

		return list(q.setParameter("refresh", refresh).setCacheable(true));
	}

	public List<AccountConnection> findByRefreshPaged(String refresh, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByRefresh");
        q.setParameter("refresh", refresh).setCacheable(true);

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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountConnection> findByCodePaged(String code, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountConnection.findByCode");

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

		return list(q.setParameter("code", code).setCacheable(true));
	}

	public List<AccountConnection> findByCodePaged(String code, Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findByCode");
        q.setParameter("code", code).setCacheable(true);

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

        List<AccountConnection> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountConnection> findAll() {
		return list(namedQuery("AccountConnection.findAll").setCacheable(true));
	}

	public List<AccountConnection> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findAll");
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

        List<AccountConnection> res = list(q);

        return res;
    }

	public List<AccountConnection> findAllPaged(Integer page, Integer limit, PagedResults<AccountConnection> results) {
        org.hibernate.query.Query q = namedQuery("AccountConnection.findAll");
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

        List<AccountConnection> res = list(q);
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

	public void delete(AccountConnection transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountConnection transientInstance) {
        //log.debug("editing AccountConnection instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountConnection transientInstance) {
        //log.debug("creating AccountConnection instance");
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
    
    public AccountConnection findById( String id) {
        //log.debug("getting AccountConnection instance with id: " + id);
        try {
            AccountConnection instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

