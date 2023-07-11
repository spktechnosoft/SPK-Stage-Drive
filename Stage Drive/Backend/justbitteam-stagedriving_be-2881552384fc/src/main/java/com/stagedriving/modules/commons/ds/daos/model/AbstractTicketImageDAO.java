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
 * Home object for domain model class TicketImage.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractTicketImageDAO extends AbstractDAO<TicketImage> {

    private final Logger log = new LoggerContext().getLogger(AbstractTicketImageDAO.class);

	public AbstractTicketImageDAO(SessionFactory session) {
		super(session);
	}

	public List<TicketImage> findByEventTicket(EventTicket eventTicket) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByEventTicket");
		return list(q.setParameter("eventTicket", eventTicket).setCacheable(true));
	}
	public TicketImage findByUid(String uid) {
		return uniqueResult(namedQuery("TicketImage.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<TicketImage> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<TicketImage> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<TicketImage> findBySmallUri(String smallUri) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findBySmallUri");
		return list(q.setParameter("smallUri", smallUri).setCacheable(true));
	}
	public List<TicketImage> findByNormalUri(String normalUri) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByNormalUri");
		return list(q.setParameter("normalUri", normalUri).setCacheable(true));
	}
	public List<TicketImage> findByLargeUri(String largeUri) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByLargeUri");
		return list(q.setParameter("largeUri", largeUri).setCacheable(true));
	}
	public List<TicketImage> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<TicketImage> findByCategory(String category) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByCategory");
		return list(q.setParameter("category", category).setCacheable(true));
	}


	public List<TicketImage> findByEventTicketPaged(EventTicket eventTicket, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findByEventTicket");

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

	public List<TicketImage> findByEventTicketPaged(EventTicket eventTicket, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByEventTicket");
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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<TicketImage> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findByCreated");

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

	public List<TicketImage> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByCreated");
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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<TicketImage> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findByModified");

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

	public List<TicketImage> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByModified");
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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<TicketImage> findBySmallUriPaged(String smallUri, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findBySmallUri");

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

		return list(q.setParameter("smallUri", smallUri).setCacheable(true));
	}

	public List<TicketImage> findBySmallUriPaged(String smallUri, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findBySmallUri");
        q.setParameter("smallUri", smallUri).setCacheable(true);

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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<TicketImage> findByNormalUriPaged(String normalUri, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findByNormalUri");

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

		return list(q.setParameter("normalUri", normalUri).setCacheable(true));
	}

	public List<TicketImage> findByNormalUriPaged(String normalUri, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByNormalUri");
        q.setParameter("normalUri", normalUri).setCacheable(true);

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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<TicketImage> findByLargeUriPaged(String largeUri, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findByLargeUri");

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

		return list(q.setParameter("largeUri", largeUri).setCacheable(true));
	}

	public List<TicketImage> findByLargeUriPaged(String largeUri, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByLargeUri");
        q.setParameter("largeUri", largeUri).setCacheable(true);

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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<TicketImage> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findByVisible");

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

	public List<TicketImage> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByVisible");
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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<TicketImage> findByCategoryPaged(String category, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImage.findByCategory");

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

	public List<TicketImage> findByCategoryPaged(String category, Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findByCategory");
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

        List<TicketImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<TicketImage> findAll() {
		return list(namedQuery("TicketImage.findAll").setCacheable(true));
	}

	public List<TicketImage> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findAll");
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

        List<TicketImage> res = list(q);

        return res;
    }

	public List<TicketImage> findAllPaged(Integer page, Integer limit, PagedResults<TicketImage> results) {
        org.hibernate.query.Query q = namedQuery("TicketImage.findAll");
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

        List<TicketImage> res = list(q);
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

	public void delete(TicketImage transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(TicketImage transientInstance) {
        //log.debug("editing TicketImage instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(TicketImage transientInstance) {
        //log.debug("creating TicketImage instance");
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
    
    public TicketImage findById( TicketImageId id) {
        //log.debug("getting TicketImage instance with id: " + id);
        try {
            TicketImage instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

