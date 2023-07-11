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
 * Home object for domain model class EventImage.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventImageDAO extends AbstractDAO<EventImage> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventImageDAO.class);

	public AbstractEventImageDAO(SessionFactory session) {
		super(session);
	}

	public List<EventImage> findByEvent(Event event) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByEvent");
		return list(q.setParameter("event", event).setCacheable(true));
	}
	public EventImage findByUid(String uid) {
		return uniqueResult(namedQuery("EventImage.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<EventImage> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventImage> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventImage> findByImage(String image) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByImage");
		return list(q.setParameter("image", image).setCacheable(true));
	}
	public List<EventImage> findByTaxonomy(String taxonomy) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByTaxonomy");
		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}


	public List<EventImage> findByEventPaged(Event event, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventImage.findByEvent");

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

	public List<EventImage> findByEventPaged(Event event, Integer page, Integer limit, PagedResults<EventImage> results) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByEvent");
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

        List<EventImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventImage> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventImage.findByCreated");

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

	public List<EventImage> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventImage> results) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByCreated");
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

        List<EventImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventImage> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventImage.findByModified");

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

	public List<EventImage> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventImage> results) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByModified");
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

        List<EventImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventImage> findByImagePaged(String image, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventImage.findByImage");

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

	public List<EventImage> findByImagePaged(String image, Integer page, Integer limit, PagedResults<EventImage> results) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByImage");
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

        List<EventImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventImage> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventImage.findByTaxonomy");

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

	public List<EventImage> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit, PagedResults<EventImage> results) {
        org.hibernate.query.Query q = namedQuery("EventImage.findByTaxonomy");
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

        List<EventImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventImage> findAll() {
		return list(namedQuery("EventImage.findAll").setCacheable(true));
	}

	public List<EventImage> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventImage.findAll");
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

        List<EventImage> res = list(q);

        return res;
    }

	public List<EventImage> findAllPaged(Integer page, Integer limit, PagedResults<EventImage> results) {
        org.hibernate.query.Query q = namedQuery("EventImage.findAll");
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

        List<EventImage> res = list(q);
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

	public void delete(EventImage transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventImage transientInstance) {
        //log.debug("editing EventImage instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventImage transientInstance) {
        //log.debug("creating EventImage instance");
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
    
    public EventImage findById( Integer id) {
        //log.debug("getting EventImage instance with id: " + id);
        try {
            EventImage instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

