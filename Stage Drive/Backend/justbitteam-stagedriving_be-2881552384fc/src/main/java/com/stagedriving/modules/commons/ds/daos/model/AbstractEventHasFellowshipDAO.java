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
 * Home object for domain model class EventHasFellowship.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasFellowshipDAO extends AbstractDAO<EventHasFellowship> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasFellowshipDAO.class);

	public AbstractEventHasFellowshipDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasFellowship> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public List<EventHasFellowship> findByFellowship(Fellowship fellowship) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByFellowship");
		return list(q.setParameter("fellowship", fellowship).setCacheable(true));
	}
	public List<EventHasFellowship> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventHasFellowship> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventHasFellowship> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<EventHasFellowship> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByEvent");

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

	public List<EventHasFellowship> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventHasFellowship> results) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByEvent");
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

        List<EventHasFellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasFellowship> findByFellowshipPaged(Fellowship fellowship, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByFellowship");

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

		return list(q.setParameter("fellowship", fellowship).setCacheable(true));
	}

	public List<EventHasFellowship> findByFellowshipPaged(Fellowship fellowship, Integer page, Integer limit, PagedResults<EventHasFellowship> results) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByFellowship");
        q.setParameter("fellowship", fellowship).setCacheable(true);

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

        List<EventHasFellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasFellowship> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByCreated");

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

	public List<EventHasFellowship> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventHasFellowship> results) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByCreated");
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

        List<EventHasFellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasFellowship> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByModified");

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

	public List<EventHasFellowship> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventHasFellowship> results) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByModified");
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

        List<EventHasFellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasFellowship> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByVisible");

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

	public List<EventHasFellowship> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventHasFellowship> results) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findByVisible");
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

        List<EventHasFellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasFellowship> findAll() {
		return list(namedQuery("EventHasFellowship.findAll").setCacheable(true));
	}

	public List<EventHasFellowship> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findAll");
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

        List<EventHasFellowship> res = list(q);

        return res;
    }

	public List<EventHasFellowship> findAllPaged(Integer page, Integer limit, PagedResults<EventHasFellowship> results) {
        org.hibernate.query.Query q = namedQuery("EventHasFellowship.findAll");
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

        List<EventHasFellowship> res = list(q);
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

	public void delete(EventHasFellowship transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasFellowship transientInstance) {
        //log.debug("editing EventHasFellowship instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasFellowship transientInstance) {
        //log.debug("creating EventHasFellowship instance");
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
    
    public EventHasFellowship findById( EventHasFellowshipId id) {
        //log.debug("getting EventHasFellowship instance with id: " + id);
        try {
            EventHasFellowship instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

