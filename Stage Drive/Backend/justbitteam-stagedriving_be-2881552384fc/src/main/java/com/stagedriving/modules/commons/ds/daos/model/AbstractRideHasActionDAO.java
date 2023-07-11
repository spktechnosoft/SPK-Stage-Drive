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
 * Home object for domain model class RideHasAction.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractRideHasActionDAO extends AbstractDAO<RideHasAction> {

    private final Logger log = new LoggerContext().getLogger(AbstractRideHasActionDAO.class);

	public AbstractRideHasActionDAO(SessionFactory session) {
		super(session);
	}

	public List<RideHasAction> findByRide(Ride ride) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByRide");
		return list(q.setParameter("ride", ride).setCacheable(true));
	}
	public List<RideHasAction> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<RideHasAction> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<RideHasAction> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<RideHasAction> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<RideHasAction> findByAccountid(int accountid) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public RideHasAction findByUid(String uid) {
		return uniqueResult(namedQuery("RideHasAction.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<RideHasAction> findByTaxonomy(String taxonomy) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByTaxonomy");
		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}
	public List<RideHasAction> findByContent(String content) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByContent");
		return list(q.setParameter("content", content).setCacheable(true));
	}


	public List<RideHasAction> findByRidePaged(Ride ride, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByRide");

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

		return list(q.setParameter("ride", ride).setCacheable(true));
	}

	public List<RideHasAction> findByRidePaged(Ride ride, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByRide");
        q.setParameter("ride", ride).setCacheable(true);

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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RideHasAction> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByCreated");

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

	public List<RideHasAction> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByCreated");
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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RideHasAction> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByModified");

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

	public List<RideHasAction> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByModified");
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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RideHasAction> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByVisible");

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

	public List<RideHasAction> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByVisible");
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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RideHasAction> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByStatus");

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

	public List<RideHasAction> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByStatus");
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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RideHasAction> findByAccountidPaged(int accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByAccountid");

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

	public List<RideHasAction> findByAccountidPaged(int accountid, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByAccountid");
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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<RideHasAction> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByTaxonomy");

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

	public List<RideHasAction> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByTaxonomy");
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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RideHasAction> findByContentPaged(String content, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasAction.findByContent");

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

	public List<RideHasAction> findByContentPaged(String content, Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findByContent");
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

        List<RideHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<RideHasAction> findAll() {
		return list(namedQuery("RideHasAction.findAll").setCacheable(true));
	}

	public List<RideHasAction> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findAll");
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

        List<RideHasAction> res = list(q);

        return res;
    }

	public List<RideHasAction> findAllPaged(Integer page, Integer limit, PagedResults<RideHasAction> results) {
        org.hibernate.query.Query q = namedQuery("RideHasAction.findAll");
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

        List<RideHasAction> res = list(q);
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

	public void delete(RideHasAction transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(RideHasAction transientInstance) {
        //log.debug("editing RideHasAction instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(RideHasAction transientInstance) {
        //log.debug("creating RideHasAction instance");
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
    
    public RideHasAction findById( RideHasActionId id) {
        //log.debug("getting RideHasAction instance with id: " + id);
        try {
            RideHasAction instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

