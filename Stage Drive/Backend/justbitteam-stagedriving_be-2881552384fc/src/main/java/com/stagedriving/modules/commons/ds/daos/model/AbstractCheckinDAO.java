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
 * Home object for domain model class Checkin.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractCheckinDAO extends AbstractDAO<Checkin> {

    private final Logger log = new LoggerContext().getLogger(AbstractCheckinDAO.class);

	public AbstractCheckinDAO(SessionFactory session) {
		super(session);
	}

	public Checkin findByUid(String uid) {
		return uniqueResult(namedQuery("Checkin.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Checkin> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Checkin> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Checkin> findByAccountid(int accountid) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public List<Checkin> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Checkin> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}




	public List<Checkin> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Checkin.findByCreated");

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

	public List<Checkin> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Checkin> results) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByCreated");
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

        List<Checkin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Checkin> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Checkin.findByModified");

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

	public List<Checkin> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Checkin> results) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByModified");
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

        List<Checkin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Checkin> findByAccountidPaged(int accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Checkin.findByAccountid");

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

		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}

	public List<Checkin> findByAccountidPaged(int accountid, Integer page, Integer limit, PagedResults<Checkin> results) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByAccountid");
        q.setParameter("accountid", accountid).setCacheable(true);

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

        List<Checkin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Checkin> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Checkin.findByVisible");

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

	public List<Checkin> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Checkin> results) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByVisible");
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

        List<Checkin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Checkin> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Checkin.findByStatus");

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

	public List<Checkin> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Checkin> results) {
        org.hibernate.query.Query q = namedQuery("Checkin.findByStatus");
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

        List<Checkin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Checkin> findAll() {
		return list(namedQuery("Checkin.findAll").setCacheable(true));
	}

	public List<Checkin> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Checkin.findAll");
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

        List<Checkin> res = list(q);

        return res;
    }

	public List<Checkin> findAllPaged(Integer page, Integer limit, PagedResults<Checkin> results) {
        org.hibernate.query.Query q = namedQuery("Checkin.findAll");
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

        List<Checkin> res = list(q);
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

	public void delete(Checkin transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Checkin transientInstance) {
        //log.debug("editing Checkin instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Checkin transientInstance) {
        //log.debug("creating Checkin instance");
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
    
    public Checkin findById( Integer id) {
        //log.debug("getting Checkin instance with id: " + id);
        try {
            Checkin instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

