package com.stagedriving.modules.commons.ds.daos.model;
// Generated 12-giu-2020 12.45.52 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.entities.*;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Home object for domain model class AccountGroup.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountGroupDAO extends AbstractDAO<AccountGroup> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountGroupDAO.class);

	public AbstractAccountGroupDAO(SessionFactory session) {
		super(session);
	}

	public AccountGroup findByUid(String uid) {
		return uniqueResult(namedQuery("AccountGroup.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountGroup> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountGroup> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public AccountGroup findByName(String name) {
		return uniqueResult(namedQuery("AccountGroup.findByName").setParameter("name", name).setCacheable(true));
	}
	public List<AccountGroup> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<AccountGroup> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}




	public List<AccountGroup> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountGroup.findByCreated");

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

	public List<AccountGroup> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByCreated");
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

        List<AccountGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountGroup> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountGroup.findByModified");

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

	public List<AccountGroup> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByModified");
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

        List<AccountGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountGroup> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountGroup.findByDescription");

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

		return list(q.setParameter("description", description).setCacheable(true));
	}

	public List<AccountGroup> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<AccountGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByDescription");
        q.setParameter("description", description).setCacheable(true);

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

        List<AccountGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountGroup> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountGroup.findByStatus");

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

	public List<AccountGroup> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<AccountGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findByStatus");
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

        List<AccountGroup> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountGroup> findAll() {
		return list(namedQuery("AccountGroup.findAll").setCacheable(true));
	}

	public List<AccountGroup> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findAll");
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

        List<AccountGroup> res = list(q);

        return res;
    }

	public List<AccountGroup> findAllPaged(Integer page, Integer limit, PagedResults<AccountGroup> results) {
        org.hibernate.query.Query q = namedQuery("AccountGroup.findAll");
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

        List<AccountGroup> res = list(q);
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

	public void delete(AccountGroup transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountGroup transientInstance) {
        //log.debug("editing AccountGroup instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountGroup transientInstance) {
        //log.debug("creating AccountGroup instance");
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
    
    public AccountGroup findById( Integer id) {
        //log.debug("getting AccountGroup instance with id: " + id);
        try {
            AccountGroup instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

