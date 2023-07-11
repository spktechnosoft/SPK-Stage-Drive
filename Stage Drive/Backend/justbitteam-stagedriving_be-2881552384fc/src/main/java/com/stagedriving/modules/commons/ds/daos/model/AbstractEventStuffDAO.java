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
 * Home object for domain model class EventStuff.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventStuffDAO extends AbstractDAO<EventStuff> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventStuffDAO.class);

	public AbstractEventStuffDAO(SessionFactory session) {
		super(session);
	}

	public List<EventStuff> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public EventStuff findByUid(String uid) {
		return uniqueResult(namedQuery("EventStuff.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<EventStuff> findByTaxonomy(String taxonomy) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByTaxonomy");
		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}
	public List<EventStuff> findByContent(String content) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByContent");
		return list(q.setParameter("content", content).setCacheable(true));
	}
	public List<EventStuff> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventStuff> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventStuff> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<EventStuff> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventStuff.findByEvent");

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

	public List<EventStuff> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventStuff> results) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByEvent");
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

        List<EventStuff> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventStuff> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventStuff.findByTaxonomy");

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

		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}

	public List<EventStuff> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit, PagedResults<EventStuff> results) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByTaxonomy");
        q.setParameter("taxonomy", taxonomy).setCacheable(true);

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

        List<EventStuff> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventStuff> findByContentPaged(String content, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventStuff.findByContent");

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

		return list(q.setParameter("content", content).setCacheable(true));
	}

	public List<EventStuff> findByContentPaged(String content, Integer page, Integer limit, PagedResults<EventStuff> results) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByContent");
        q.setParameter("content", content).setCacheable(true);

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

        List<EventStuff> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventStuff> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventStuff.findByCreated");

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

	public List<EventStuff> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventStuff> results) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByCreated");
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

        List<EventStuff> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventStuff> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventStuff.findByModified");

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

	public List<EventStuff> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventStuff> results) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByModified");
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

        List<EventStuff> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventStuff> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventStuff.findByVisible");

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

	public List<EventStuff> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventStuff> results) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findByVisible");
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

        List<EventStuff> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventStuff> findAll() {
		return list(namedQuery("EventStuff.findAll").setCacheable(true));
	}

	public List<EventStuff> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findAll");
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

        List<EventStuff> res = list(q);

        return res;
    }

	public List<EventStuff> findAllPaged(Integer page, Integer limit, PagedResults<EventStuff> results) {
        org.hibernate.query.Query q = namedQuery("EventStuff.findAll");
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

        List<EventStuff> res = list(q);
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

	public void delete(EventStuff transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventStuff transientInstance) {
        //log.debug("editing EventStuff instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventStuff transientInstance) {
        //log.debug("creating EventStuff instance");
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
    
    public EventStuff findById( EventStuffId id) {
        //log.debug("getting EventStuff instance with id: " + id);
        try {
            EventStuff instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

