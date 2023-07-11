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
 * Home object for domain model class EventHasAction.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventHasActionDAO extends AbstractDAO<EventHasAction> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventHasActionDAO.class);

	public AbstractEventHasActionDAO(SessionFactory session) {
		super(session);
	}

	public List<EventHasAction> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public List<EventHasAction> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventHasAction> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventHasAction> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<EventHasAction> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<EventHasAction> findByAccountid(int accountid) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public EventHasAction findByUid(String uid) {
		return uniqueResult(namedQuery("EventHasAction.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<EventHasAction> findByImage(String image) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByImage");
		return list(q.setParameter("image", image).setCacheable(true));
	}
	public List<EventHasAction> findByTaxonomy(String taxonomy) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByTaxonomy");
		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}
	public List<EventHasAction> findByContent(String content) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByContent");
		return list(q.setParameter("content", content).setCacheable(true));
	}


	public List<EventHasAction> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByEvent");

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

	public List<EventHasAction> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByEvent");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasAction> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByCreated");

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

	public List<EventHasAction> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByCreated");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasAction> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByModified");

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

	public List<EventHasAction> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByModified");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasAction> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByVisible");

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

	public List<EventHasAction> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByVisible");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasAction> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByStatus");

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

	public List<EventHasAction> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByStatus");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasAction> findByAccountidPaged(int accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByAccountid");

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

	public List<EventHasAction> findByAccountidPaged(int accountid, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByAccountid");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasAction> findByImagePaged(String image, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByImage");

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

		return list(q.setParameter("image", image).setCacheable(true));
	}

	public List<EventHasAction> findByImagePaged(String image, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByImage");
        q.setParameter("image", image).setCacheable(true);

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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasAction> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByTaxonomy");

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

	public List<EventHasAction> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByTaxonomy");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventHasAction> findByContentPaged(String content, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventHasAction.findByContent");

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

	public List<EventHasAction> findByContentPaged(String content, Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findByContent");
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

        List<EventHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventHasAction> findAll() {
		return list(namedQuery("EventHasAction.findAll").setCacheable(true));
	}

	public List<EventHasAction> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findAll");
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

        List<EventHasAction> res = list(q);

        return res;
    }

	public List<EventHasAction> findAllPaged(Integer page, Integer limit, PagedResults<EventHasAction> results) {
        org.hibernate.query.Query q = namedQuery("EventHasAction.findAll");
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

        List<EventHasAction> res = list(q);
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

	public void delete(EventHasAction transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventHasAction transientInstance) {
        //log.debug("editing EventHasAction instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventHasAction transientInstance) {
        //log.debug("creating EventHasAction instance");
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
    
    public EventHasAction findById( Integer id) {
        //log.debug("getting EventHasAction instance with id: " + id);
        try {
            EventHasAction instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

