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
 * Home object for domain model class AccountHasGroup.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountHasGroupDAO extends AbstractDAO<AccountHasGroup> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountHasGroupDAO.class);

	public AbstractAccountHasGroupDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountHasGroup> findByAccount(Account account) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByAccount");
		return list(q.setParameter("account", account).setCacheable(true));
	}
	public List<AccountHasGroup> findByAccountGroup(AccountGroup accountGroup) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByAccountGroup");
		return list(q.setParameter("accountGroup", accountGroup).setCacheable(true));
	}
	public List<AccountHasGroup> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountHasGroup> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountHasGroup> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<AccountHasGroup> findByAccountPaged(Account account, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByAccount");

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

	public List<AccountHasGroup> findByAccountPaged(Account account, Integer page, Integer limit, PagedResults<AccountHasGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByAccount");
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

        List<AccountHasGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountHasGroup> findByAccountGroupPaged(AccountGroup accountGroup, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByAccountGroup");

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

		return list(q.setParameter("accountGroup", accountGroup).setCacheable(true));
	}

	public List<AccountHasGroup> findByAccountGroupPaged(AccountGroup accountGroup, Integer page, Integer limit, PagedResults<AccountHasGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByAccountGroup");
        q.setParameter("accountGroup", accountGroup).setCacheable(true);

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

        List<AccountHasGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountHasGroup> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByCreated");

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

	public List<AccountHasGroup> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountHasGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByCreated");
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

        List<AccountHasGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountHasGroup> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByModified");

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

	public List<AccountHasGroup> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountHasGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByModified");
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

        List<AccountHasGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountHasGroup> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByVisible");

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

	public List<AccountHasGroup> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<AccountHasGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findByVisible");
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

        List<AccountHasGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountHasGroup> findAll() {
		return list(namedQuery("AccountHasGroup.findAll").setCacheable(true));
	}

	public List<AccountHasGroup> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findAll");
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

        List<AccountHasGroup> res = list(q);

        return res;
    }

	public List<AccountHasGroup> findAllPaged(Integer page, Integer limit, PagedResults<AccountHasGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountHasGroup.findAll");
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

        List<AccountHasGroup> res = list(q);
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

	public void delete(AccountHasGroup transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountHasGroup transientInstance) {
        //log.debug("editing AccountHasGroup instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountHasGroup transientInstance) {
        //log.debug("creating AccountHasGroup instance");
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
    
    public AccountHasGroup findById( AccountHasGroupId id) {
        //log.debug("getting AccountHasGroup instance with id: " + id);
        try {
            AccountHasGroup instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

