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
 * Home object for domain model class RideHasActionId.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractRideHasActionIdDAO extends AbstractDAO<RideHasActionId> {

    private final Logger log = new LoggerContext().getLogger(AbstractRideHasActionIdDAO.class);

	public AbstractRideHasActionIdDAO(SessionFactory session) {
		super(session);
	}

	public List<RideHasActionId> findByRideId(int rideId) {
        org.hibernate.query.Query q = namedQuery("RideHasActionId.findByRideId");
		return list(q.setParameter("rideId", rideId).setCacheable(true));
	}


	public List<RideHasActionId> findByRideIdPaged(int rideId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RideHasActionId.findByRideId");

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

		return list(q.setParameter("rideId", rideId).setCacheable(true));
	}

	public List<RideHasActionId> findByRideIdPaged(int rideId, Integer page, Integer limit, PagedResults<RideHasActionId> results) {
        org.hibernate.query.Query q = namedQuery("RideHasActionId.findByRideId");
        q.setParameter("rideId", rideId).setCacheable(true);

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

        List<RideHasActionId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<RideHasActionId> findAll() {
		return list(namedQuery("RideHasActionId.findAll").setCacheable(true));
	}

	public List<RideHasActionId> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("RideHasActionId.findAll");
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

        List<RideHasActionId> res = list(q);

        return res;
    }

	public List<RideHasActionId> findAllPaged(Integer page, Integer limit, PagedResults<RideHasActionId> results) {
        org.hibernate.query.Query q = namedQuery("RideHasActionId.findAll");
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

        List<RideHasActionId> res = list(q);
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

	public void delete(RideHasActionId transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(RideHasActionId transientInstance) {
        //log.debug("editing RideHasActionId instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(RideHasActionId transientInstance) {
        //log.debug("creating RideHasActionId instance");
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

