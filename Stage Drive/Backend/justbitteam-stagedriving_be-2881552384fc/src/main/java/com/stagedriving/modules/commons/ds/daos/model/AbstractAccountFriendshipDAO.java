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
 * Home object for domain model class AccountFriendship.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountFriendshipDAO extends AbstractDAO<AccountFriendship> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountFriendshipDAO.class);

	public AbstractAccountFriendshipDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountFriendship> findByAccountByAccountIdTo(Account accountByAccountIdTo) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByAccountByAccountIdTo");
		return list(q.setParameter("accountByAccountIdTo", accountByAccountIdTo).setCacheable(true));
	}
	public List<AccountFriendship> findByAccountByAccountIdFrom(Account accountByAccountIdFrom) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByAccountByAccountIdFrom");
		return list(q.setParameter("accountByAccountIdFrom", accountByAccountIdFrom).setCacheable(true));
	}
	public List<AccountFriendship> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<AccountFriendship> findByTowards(boolean towards) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByTowards");
		return list(q.setParameter("towards", towards).setCacheable(true));
	}
	public AccountFriendship findByUid(String uid) {
		return uniqueResult(namedQuery("AccountFriendship.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountFriendship> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<AccountFriendship> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountFriendship> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}


	public List<AccountFriendship> findByAccountByAccountIdToPaged(Account accountByAccountIdTo, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountFriendship.findByAccountByAccountIdTo");

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

		return list(q.setParameter("accountByAccountIdTo", accountByAccountIdTo).setCacheable(true));
	}

	public List<AccountFriendship> findByAccountByAccountIdToPaged(Account accountByAccountIdTo, Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByAccountByAccountIdTo");
        q.setParameter("accountByAccountIdTo", accountByAccountIdTo).setCacheable(true);

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

        List<AccountFriendship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountFriendship> findByAccountByAccountIdFromPaged(Account accountByAccountIdFrom, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountFriendship.findByAccountByAccountIdFrom");

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

		return list(q.setParameter("accountByAccountIdFrom", accountByAccountIdFrom).setCacheable(true));
	}

	public List<AccountFriendship> findByAccountByAccountIdFromPaged(Account accountByAccountIdFrom, Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByAccountByAccountIdFrom");
        q.setParameter("accountByAccountIdFrom", accountByAccountIdFrom).setCacheable(true);

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

        List<AccountFriendship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountFriendship> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountFriendship.findByVisible");

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

		return list(q.setParameter("visible", visible).setCacheable(true));
	}

	public List<AccountFriendship> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByVisible");
        q.setParameter("visible", visible).setCacheable(true);

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

        List<AccountFriendship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountFriendship> findByTowardsPaged(boolean towards, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountFriendship.findByTowards");

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

		return list(q.setParameter("towards", towards).setCacheable(true));
	}

	public List<AccountFriendship> findByTowardsPaged(boolean towards, Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByTowards");
        q.setParameter("towards", towards).setCacheable(true);

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

        List<AccountFriendship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountFriendship> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountFriendship.findByStatus");

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

		return list(q.setParameter("status", status).setCacheable(true));
	}

	public List<AccountFriendship> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByStatus");
        q.setParameter("status", status).setCacheable(true);

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

        List<AccountFriendship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountFriendship> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountFriendship.findByCreated");

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

	public List<AccountFriendship> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByCreated");
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

        List<AccountFriendship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountFriendship> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountFriendship.findByModified");

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

	public List<AccountFriendship> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findByModified");
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

        List<AccountFriendship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountFriendship> findAll() {
		return list(namedQuery("AccountFriendship.findAll").setCacheable(true));
	}

	public List<AccountFriendship> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findAll");
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

        List<AccountFriendship> res = list(q);

        return res;
    }

	public List<AccountFriendship> findAllPaged(Integer page, Integer limit, PagedResults<AccountFriendship> results) {
        org.hibernate.query.Query q = namedQuery("AccountFriendship.findAll");
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

        List<AccountFriendship> res = list(q);
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

	public void delete(AccountFriendship transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountFriendship transientInstance) {
        //log.debug("editing AccountFriendship instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountFriendship transientInstance) {
        //log.debug("creating AccountFriendship instance");
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
    
    public AccountFriendship findById( AccountFriendshipId id) {
        //log.debug("getting AccountFriendship instance with id: " + id);
        try {
            AccountFriendship instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

