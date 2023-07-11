package com.stagedriving.modules.commons.ds.daos.model;
// Generated 12-giu-2020 12.45.52 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.entities.*;
import io.dropwizard.hibernate.AbstractDAO;
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
 * Home object for domain model class EventHasBookingId.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasBookingIdDAO extends AbstractDAO<EventHasBookingId> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasBookingIdDAO.class);

	public AbstractEventHasBookingIdDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasBookingId> findByBookingId(int bookingId) {
        org.hibernate.query.Query q = namedQuery("EventHasBookingId.findByBookingId");
		return list(q.setParameter("bookingId", bookingId).setCacheable(true));
	}
	public List<EventHasBookingId> findByEventId(int eventId) {
        org.hibernate.query.Query q = namedQuery("EventHasBookingId.findByEventId");
		return list(q.setParameter("eventId", eventId).setCacheable(true));
	}


	public List<EventHasBookingId> findByBookingIdPaged(int bookingId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBookingId.findByBookingId");

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

		return list(q.setParameter("bookingId", bookingId).setCacheable(true));
	}

	public List<EventHasBookingId> findByBookingIdPaged(int bookingId, Integer page, Integer limit, PagedResults<EventHasBookingId> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBookingId.findByBookingId");
        q.setParameter("bookingId", bookingId).setCacheable(true);

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

        List<EventHasBookingId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBookingId> findByEventIdPaged(int eventId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBookingId.findByEventId");

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

		return list(q.setParameter("eventId", eventId).setCacheable(true));
	}

	public List<EventHasBookingId> findByEventIdPaged(int eventId, Integer page, Integer limit, PagedResults<EventHasBookingId> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBookingId.findByEventId");
        q.setParameter("eventId", eventId).setCacheable(true);

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

        List<EventHasBookingId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasBookingId> findAll() {
		return list(namedQuery("EventHasBookingId.findAll").setCacheable(true));
	}

	public List<EventHasBookingId> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasBookingId.findAll");
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

        List<EventHasBookingId> res = list(q);

        return res;
    }

	public List<EventHasBookingId> findAllPaged(Integer page, Integer limit, PagedResults<EventHasBookingId> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBookingId.findAll");
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

        List<EventHasBookingId> res = list(q);
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

	public void delete(EventHasBookingId transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasBookingId transientInstance) {
        //log.debug("editing EventHasBookingId instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasBookingId transientInstance) {
        //log.debug("creating EventHasBookingId instance");
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
    
}

