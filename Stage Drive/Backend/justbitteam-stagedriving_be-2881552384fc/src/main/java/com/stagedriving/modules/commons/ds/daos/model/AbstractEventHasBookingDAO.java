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
 * Home object for domain model class EventHasBooking.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasBookingDAO extends AbstractDAO<EventHasBooking> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasBookingDAO.class);

	public AbstractEventHasBookingDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasBooking> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public List<EventHasBooking> findByBooking(Booking booking) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByBooking");
		return list(q.setParameter("booking", booking).setCacheable(true));
	}
	public List<EventHasBooking> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventHasBooking> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventHasBooking> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<EventHasBooking> findByAccountid(Integer accountid) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}


	public List<EventHasBooking> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBooking.findByEvent");

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

	public List<EventHasBooking> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventHasBooking> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByEvent");
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

        List<EventHasBooking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBooking> findByBookingPaged(Booking booking, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBooking.findByBooking");

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

		return list(q.setParameter("booking", booking).setCacheable(true));
	}

	public List<EventHasBooking> findByBookingPaged(Booking booking, Integer page, Integer limit, PagedResults<EventHasBooking> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByBooking");
        q.setParameter("booking", booking).setCacheable(true);

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

        List<EventHasBooking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBooking> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBooking.findByCreated");

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

	public List<EventHasBooking> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventHasBooking> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByCreated");
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

        List<EventHasBooking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBooking> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBooking.findByModified");

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

	public List<EventHasBooking> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventHasBooking> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByModified");
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

        List<EventHasBooking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBooking> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBooking.findByVisible");

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

	public List<EventHasBooking> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventHasBooking> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByVisible");
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

        List<EventHasBooking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBooking> findByAccountidPaged(Integer accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBooking.findByAccountid");

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

	public List<EventHasBooking> findByAccountidPaged(Integer accountid, Integer page, Integer limit, PagedResults<EventHasBooking> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findByAccountid");
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

        List<EventHasBooking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasBooking> findAll() {
		return list(namedQuery("EventHasBooking.findAll").setCacheable(true));
	}

	public List<EventHasBooking> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findAll");
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

        List<EventHasBooking> res = list(q);

        return res;
    }

	public List<EventHasBooking> findAllPaged(Integer page, Integer limit, PagedResults<EventHasBooking> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBooking.findAll");
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

        List<EventHasBooking> res = list(q);
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

	public void delete(EventHasBooking transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasBooking transientInstance) {
        //log.debug("editing EventHasBooking instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasBooking transientInstance) {
        //log.debug("creating EventHasBooking instance");
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
    
    public EventHasBooking findById( EventHasBookingId id) {
        //log.debug("getting EventHasBooking instance with id: " + id);
        try {
            EventHasBooking instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

