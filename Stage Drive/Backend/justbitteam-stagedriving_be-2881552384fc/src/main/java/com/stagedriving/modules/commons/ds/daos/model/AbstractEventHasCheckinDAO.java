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
 * Home object for domain model class EventHasCheckin.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasCheckinDAO extends AbstractDAO<EventHasCheckin> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasCheckinDAO.class);

	public AbstractEventHasCheckinDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasCheckin> findByCheckin(Checkin checkin) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByCheckin");
		return list(q.setParameter("checkin", checkin).setCacheable(true));
	}
	public List<EventHasCheckin> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public List<EventHasCheckin> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventHasCheckin> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventHasCheckin> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<EventHasCheckin> findByCheckinPaged(Checkin checkin, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByCheckin");

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

		return list(q.setParameter("checkin", checkin).setCacheable(true));
	}

	public List<EventHasCheckin> findByCheckinPaged(Checkin checkin, Integer page, Integer limit, PagedResults<EventHasCheckin> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByCheckin");
        q.setParameter("checkin", checkin).setCacheable(true);

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

        List<EventHasCheckin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCheckin> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByEvent");

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

	public List<EventHasCheckin> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventHasCheckin> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByEvent");
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

        List<EventHasCheckin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCheckin> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByCreated");

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

	public List<EventHasCheckin> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventHasCheckin> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByCreated");
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

        List<EventHasCheckin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCheckin> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByModified");

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

	public List<EventHasCheckin> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventHasCheckin> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByModified");
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

        List<EventHasCheckin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasCheckin> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByVisible");

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

	public List<EventHasCheckin> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventHasCheckin> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findByVisible");
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

        List<EventHasCheckin> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasCheckin> findAll() {
		return list(namedQuery("EventHasCheckin.findAll").setCacheable(true));
	}

	public List<EventHasCheckin> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findAll");
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

        List<EventHasCheckin> res = list(q);

        return res;
    }

	public List<EventHasCheckin> findAllPaged(Integer page, Integer limit, PagedResults<EventHasCheckin> results) {
        org.hibernate.query.Query q = namedQuery("EventHasCheckin.findAll");
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

        List<EventHasCheckin> res = list(q);
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

	public void delete(EventHasCheckin transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasCheckin transientInstance) {
        //log.debug("editing EventHasCheckin instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasCheckin transientInstance) {
        //log.debug("creating EventHasCheckin instance");
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
    
    public EventHasCheckin findById( EventHasCheckinId id) {
        //log.debug("getting EventHasCheckin instance with id: " + id);
        try {
            EventHasCheckin instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

