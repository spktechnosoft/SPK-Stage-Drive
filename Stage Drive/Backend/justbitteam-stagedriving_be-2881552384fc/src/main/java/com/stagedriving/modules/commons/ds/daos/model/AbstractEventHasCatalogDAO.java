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
 * Home object for domain model class EventHasCatalog.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasCatalogDAO extends AbstractDAO<EventHasCatalog> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasCatalogDAO.class);

	public AbstractEventHasCatalogDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasCatalog> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public List<EventHasCatalog> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventHasCatalog> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventHasCatalog> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<EventHasCatalog> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public EventHasCatalog findByUid(String uid) {
		return uniqueResult(namedQuery("EventHasCatalog.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<EventHasCatalog> findByCatalogid(Integer catalogid) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByCatalogid");
		return list(q.setParameter("catalogid", catalogid).setCacheable(true));
	}


	public List<EventHasCatalog> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByEvent");

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

		return list(q.setParameter("event", event).setCacheable(true));
	}

	public List<EventHasCatalog> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventHasCatalog> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByEvent");
        q.setParameter("event", event).setCacheable(true);

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

        List<EventHasCatalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCatalog> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByCreated");

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

	public List<EventHasCatalog> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventHasCatalog> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByCreated");
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

        List<EventHasCatalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCatalog> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByModified");

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

	public List<EventHasCatalog> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventHasCatalog> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByModified");
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

        List<EventHasCatalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCatalog> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByVisible");

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

	public List<EventHasCatalog> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventHasCatalog> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByVisible");
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

        List<EventHasCatalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCatalog> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByStatus");

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

	public List<EventHasCatalog> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<EventHasCatalog> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByStatus");
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

        List<EventHasCatalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasCatalog> findByCatalogidPaged(Integer catalogid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByCatalogid");

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

		return list(q.setParameter("catalogid", catalogid).setCacheable(true));
	}

	public List<EventHasCatalog> findByCatalogidPaged(Integer catalogid, Integer page, Integer limit, PagedResults<EventHasCatalog> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findByCatalogid");
        q.setParameter("catalogid", catalogid).setCacheable(true);

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

        List<EventHasCatalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasCatalog> findAll() {
		return list(namedQuery("EventHasCatalog.findAll").setCacheable(true));
	}

	public List<EventHasCatalog> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findAll");
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

        List<EventHasCatalog> res = list(q);

        return res;
    }

	public List<EventHasCatalog> findAllPaged(Integer page, Integer limit, PagedResults<EventHasCatalog> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCatalog.findAll");
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

        List<EventHasCatalog> res = list(q);
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

	public void delete(EventHasCatalog transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasCatalog transientInstance) {
        //log.debug("editing EventHasCatalog instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasCatalog transientInstance) {
        //log.debug("creating EventHasCatalog instance");
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
    
    public EventHasCatalog findById( Integer id) {
        //log.debug("getting EventHasCatalog instance with id: " + id);
        try {
            EventHasCatalog instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

