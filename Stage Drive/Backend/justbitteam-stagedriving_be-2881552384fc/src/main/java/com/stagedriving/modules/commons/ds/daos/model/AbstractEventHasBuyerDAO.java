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
 * Home object for domain model class EventHasBuyer.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasBuyerDAO extends AbstractDAO<EventHasBuyer> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasBuyerDAO.class);

	public AbstractEventHasBuyerDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasBuyer> findByEventTicket(EventTicket eventTicket) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByEventTicket");
		return list(q.setParameter("eventTicket", eventTicket).setCacheable(true));
	}
	public List<EventHasBuyer> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public List<EventHasBuyer> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventHasBuyer> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventHasBuyer> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<EventHasBuyer> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<EventHasBuyer> findByAccountid(int accountid) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public EventHasBuyer findByUid(String uid) {
		return uniqueResult(namedQuery("EventHasBuyer.findByUid").setParameter("uid", uid).setCacheable(true));
	}


	public List<EventHasBuyer> findByEventTicketPaged(EventTicket eventTicket, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByEventTicket");

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

		return list(q.setParameter("eventTicket", eventTicket).setCacheable(true));
	}

	public List<EventHasBuyer> findByEventTicketPaged(EventTicket eventTicket, Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByEventTicket");
        q.setParameter("eventTicket", eventTicket).setCacheable(true);

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

        List<EventHasBuyer> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBuyer> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByEvent");

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

	public List<EventHasBuyer> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByEvent");
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

        List<EventHasBuyer> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBuyer> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByCreated");

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

	public List<EventHasBuyer> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByCreated");
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

        List<EventHasBuyer> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBuyer> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByModified");

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

	public List<EventHasBuyer> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByModified");
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

        List<EventHasBuyer> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBuyer> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByVisible");

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

	public List<EventHasBuyer> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByVisible");
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

        List<EventHasBuyer> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBuyer> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByStatus");

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

	public List<EventHasBuyer> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByStatus");
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

        List<EventHasBuyer> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasBuyer> findByAccountidPaged(int accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByAccountid");

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

	public List<EventHasBuyer> findByAccountidPaged(int accountid, Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findByAccountid");
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

        List<EventHasBuyer> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }





	public List<EventHasBuyer> findAll() {
		return list(namedQuery("EventHasBuyer.findAll").setCacheable(true));
	}

	public List<EventHasBuyer> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findAll");
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

        List<EventHasBuyer> res = list(q);

        return res;
    }

	public List<EventHasBuyer> findAllPaged(Integer page, Integer limit, PagedResults<EventHasBuyer> results) {
        org.hibernate.query.Query q = namedQuery("EventHasBuyer.findAll");
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

        List<EventHasBuyer> res = list(q);
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

	public void delete(EventHasBuyer transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasBuyer transientInstance) {
        //log.debug("editing EventHasBuyer instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasBuyer transientInstance) {
        //log.debug("creating EventHasBuyer instance");
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
    
    public EventHasBuyer findById( EventHasBuyerId id) {
        //log.debug("getting EventHasBuyer instance with id: " + id);
        try {
            EventHasBuyer instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

