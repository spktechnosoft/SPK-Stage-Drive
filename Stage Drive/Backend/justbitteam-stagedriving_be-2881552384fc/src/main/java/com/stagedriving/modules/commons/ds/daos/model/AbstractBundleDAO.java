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
 * Home object for domain model class Bundle.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractBundleDAO extends AbstractDAO<Bundle> {

    private final Logger log = new LoggerContext().getLogger(AbstractBundleDAO.class);

	public AbstractBundleDAO(SessionFactory session) {
		super(session);
	}

	public Bundle findByUid(String uid) {
		return uniqueResult(namedQuery("Bundle.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Bundle> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Bundle> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Bundle> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Bundle> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}




	public List<Bundle> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Bundle.findByStatus");

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

	public List<Bundle> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Bundle> results) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByStatus");
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

        List<Bundle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Bundle> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Bundle.findByVisible");

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

	public List<Bundle> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Bundle> results) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByVisible");
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

        List<Bundle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Bundle> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Bundle.findByCreated");

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

	public List<Bundle> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Bundle> results) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByCreated");
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

        List<Bundle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Bundle> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Bundle.findByModified");

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

	public List<Bundle> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Bundle> results) {
        org.hibernate.query.Query q = namedQuery("Bundle.findByModified");
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

        List<Bundle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Bundle> findAll() {
		return list(namedQuery("Bundle.findAll").setCacheable(true));
	}

	public List<Bundle> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Bundle.findAll");
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

        List<Bundle> res = list(q);

        return res;
    }

	public List<Bundle> findAllPaged(Integer page, Integer limit, PagedResults<Bundle> results) {
        org.hibernate.query.Query q = namedQuery("Bundle.findAll");
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

        List<Bundle> res = list(q);
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

	public void delete(Bundle transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Bundle transientInstance) {
        //log.debug("editing Bundle instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Bundle transientInstance) {
        //log.debug("creating Bundle instance");
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
    
    public Bundle findById( Integer id) {
        //log.debug("getting Bundle instance with id: " + id);
        try {
            Bundle instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

