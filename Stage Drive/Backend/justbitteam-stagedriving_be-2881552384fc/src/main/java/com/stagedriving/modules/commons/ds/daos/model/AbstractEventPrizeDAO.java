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
 * Home object for domain model class EventPrize.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventPrizeDAO extends AbstractDAO<EventPrize> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventPrizeDAO.class);

	public AbstractEventPrizeDAO(SessionFactory session) {
		super(session);
	}

	public List<EventPrize> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public EventPrize findByUid(String uid) {
		return uniqueResult(namedQuery("EventPrize.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<EventPrize> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventPrize> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventPrize> findByCategory(String category) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByCategory");
		return list(q.setParameter("category", category).setCacheable(true));
	}
	public List<EventPrize> findByAccountid(Integer accountid) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public List<EventPrize> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<EventPrize> findByConsumed(Boolean consumed) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByConsumed");
		return list(q.setParameter("consumed", consumed).setCacheable(true));
	}


	public List<EventPrize> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventPrize.findByEvent");

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

	public List<EventPrize> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByEvent");
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

        List<EventPrize> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventPrize> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventPrize.findByCreated");

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

	public List<EventPrize> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByCreated");
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

        List<EventPrize> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventPrize> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventPrize.findByModified");

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

	public List<EventPrize> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByModified");
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

        List<EventPrize> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventPrize> findByCategoryPaged(String category, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventPrize.findByCategory");

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

		return list(q.setParameter("category", category).setCacheable(true));
	}

	public List<EventPrize> findByCategoryPaged(String category, Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByCategory");
        q.setParameter("category", category).setCacheable(true);

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

        List<EventPrize> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventPrize> findByAccountidPaged(Integer accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventPrize.findByAccountid");

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

	public List<EventPrize> findByAccountidPaged(Integer accountid, Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByAccountid");
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

        List<EventPrize> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventPrize> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventPrize.findByVisible");

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

	public List<EventPrize> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByVisible");
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

        List<EventPrize> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventPrize> findByConsumedPaged(Boolean consumed, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventPrize.findByConsumed");

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

		return list(q.setParameter("consumed", consumed).setCacheable(true));
	}

	public List<EventPrize> findByConsumedPaged(Boolean consumed, Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findByConsumed");
        q.setParameter("consumed", consumed).setCacheable(true);

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

        List<EventPrize> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventPrize> findAll() {
		return list(namedQuery("EventPrize.findAll").setCacheable(true));
	}

	public List<EventPrize> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findAll");
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

        List<EventPrize> res = list(q);

        return res;
    }

	public List<EventPrize> findAllPaged(Integer page, Integer limit, PagedResults<EventPrize> results) {
        org.hibernate.query.Query q = namedQuery("EventPrize.findAll");
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

        List<EventPrize> res = list(q);
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

	public void delete(EventPrize transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventPrize transientInstance) {
        //log.debug("editing EventPrize instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventPrize transientInstance) {
        //log.debug("creating EventPrize instance");
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
    
    public EventPrize findById( EventPrizeId id) {
        //log.debug("getting EventPrize instance with id: " + id);
        try {
            EventPrize instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

