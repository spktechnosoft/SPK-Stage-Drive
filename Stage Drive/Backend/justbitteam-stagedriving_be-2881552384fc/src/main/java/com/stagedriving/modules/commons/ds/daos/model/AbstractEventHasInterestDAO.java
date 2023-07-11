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
 * Home object for domain model class EventHasInterest.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasInterestDAO extends AbstractDAO<EventHasInterest> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasInterestDAO.class);

	public AbstractEventHasInterestDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasInterest> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public EventHasInterest findByUid(String uid) {
		return uniqueResult(namedQuery("EventHasInterest.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<EventHasInterest> findByAccountId(int accountId) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByAccountId");
		return list(q.setParameter("accountId", accountId).setCacheable(true));
	}
	public List<EventHasInterest> findByBookingTicketId(Integer bookingTicketId) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByBookingTicketId");
		return list(q.setParameter("bookingTicketId", bookingTicketId).setCacheable(true));
	}
	public List<EventHasInterest> findByBookingId(Integer bookingId) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByBookingId");
		return list(q.setParameter("bookingId", bookingId).setCacheable(true));
	}
	public List<EventHasInterest> findByActionLikeId(Integer actionLikeId) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionLikeId");
		return list(q.setParameter("actionLikeId", actionLikeId).setCacheable(true));
	}
	public List<EventHasInterest> findByActionCommentId(Integer actionCommentId) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionCommentId");
		return list(q.setParameter("actionCommentId", actionCommentId).setCacheable(true));
	}
	public List<EventHasInterest> findByActionRideId(Integer actionRideId) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionRideId");
		return list(q.setParameter("actionRideId", actionRideId).setCacheable(true));
	}
	public List<EventHasInterest> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventHasInterest> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}


	public List<EventHasInterest> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByEvent");

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

	public List<EventHasInterest> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByEvent");
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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasInterest> findByAccountIdPaged(int accountId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByAccountId");

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

		return list(q.setParameter("accountId", accountId).setCacheable(true));
	}

	public List<EventHasInterest> findByAccountIdPaged(int accountId, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByAccountId");
        q.setParameter("accountId", accountId).setCacheable(true);

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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasInterest> findByBookingTicketIdPaged(Integer bookingTicketId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByBookingTicketId");

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

		return list(q.setParameter("bookingTicketId", bookingTicketId).setCacheable(true));
	}

	public List<EventHasInterest> findByBookingTicketIdPaged(Integer bookingTicketId, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByBookingTicketId");
        q.setParameter("bookingTicketId", bookingTicketId).setCacheable(true);

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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasInterest> findByBookingIdPaged(Integer bookingId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByBookingId");

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

	public List<EventHasInterest> findByBookingIdPaged(Integer bookingId, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByBookingId");
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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasInterest> findByActionLikeIdPaged(Integer actionLikeId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionLikeId");

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

		return list(q.setParameter("actionLikeId", actionLikeId).setCacheable(true));
	}

	public List<EventHasInterest> findByActionLikeIdPaged(Integer actionLikeId, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionLikeId");
        q.setParameter("actionLikeId", actionLikeId).setCacheable(true);

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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasInterest> findByActionCommentIdPaged(Integer actionCommentId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionCommentId");

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

		return list(q.setParameter("actionCommentId", actionCommentId).setCacheable(true));
	}

	public List<EventHasInterest> findByActionCommentIdPaged(Integer actionCommentId, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionCommentId");
        q.setParameter("actionCommentId", actionCommentId).setCacheable(true);

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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasInterest> findByActionRideIdPaged(Integer actionRideId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionRideId");

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

		return list(q.setParameter("actionRideId", actionRideId).setCacheable(true));
	}

	public List<EventHasInterest> findByActionRideIdPaged(Integer actionRideId, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByActionRideId");
        q.setParameter("actionRideId", actionRideId).setCacheable(true);

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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasInterest> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByCreated");

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

	public List<EventHasInterest> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByCreated");
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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasInterest> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasInterest.findByModified");

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

	public List<EventHasInterest> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findByModified");
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

        List<EventHasInterest> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasInterest> findAll() {
		return list(namedQuery("EventHasInterest.findAll").setCacheable(true));
	}

	public List<EventHasInterest> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findAll");
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

        List<EventHasInterest> res = list(q);

        return res;
    }

	public List<EventHasInterest> findAllPaged(Integer page, Integer limit, PagedResults<EventHasInterest> results) {
        org.hibernate.query.Query q = namedQuery("EventHasInterest.findAll");
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

        List<EventHasInterest> res = list(q);
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

	public void delete(EventHasInterest transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasInterest transientInstance) {
        //log.debug("editing EventHasInterest instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasInterest transientInstance) {
        //log.debug("creating EventHasInterest instance");
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
    
    public EventHasInterest findById( Integer id) {
        //log.debug("getting EventHasInterest instance with id: " + id);
        try {
            EventHasInterest instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

