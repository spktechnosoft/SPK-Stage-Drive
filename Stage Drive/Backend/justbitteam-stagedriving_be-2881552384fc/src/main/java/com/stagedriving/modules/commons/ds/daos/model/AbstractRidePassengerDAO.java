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
 * Home object for domain model class RidePassenger.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractRidePassengerDAO extends AbstractDAO<RidePassenger> {

    private final Logger log = new LoggerContext().getLogger(AbstractRidePassengerDAO.class);

	public AbstractRidePassengerDAO(SessionFactory session) {
		super(session);
	}

	public List<RidePassenger> findByRide(Ride ride) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByRide");
		return list(q.setParameter("ride", ride).setCacheable(true));
	}
	public RidePassenger findByUid(String uid) {
		return uniqueResult(namedQuery("RidePassenger.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<RidePassenger> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<RidePassenger> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<RidePassenger> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<RidePassenger> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<RidePassenger> findByAccountId(Integer accountId) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByAccountId");
		return list(q.setParameter("accountId", accountId).setCacheable(true));
	}
	public List<RidePassenger> findByTransactionId(String transactionId) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByTransactionId");
		return list(q.setParameter("transactionId", transactionId).setCacheable(true));
	}
	public List<RidePassenger> findBySeats(Integer seats) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findBySeats");
		return list(q.setParameter("seats", seats).setCacheable(true));
	}


	public List<RidePassenger> findByRidePaged(Ride ride, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findByRide");

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

	public List<RidePassenger> findByRidePaged(Ride ride, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByRide");
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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<RidePassenger> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findByCreated");

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

	public List<RidePassenger> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByCreated");
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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RidePassenger> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findByModified");

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

	public List<RidePassenger> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByModified");
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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RidePassenger> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findByStatus");

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

	public List<RidePassenger> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByStatus");
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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RidePassenger> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findByVisible");

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

	public List<RidePassenger> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByVisible");
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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RidePassenger> findByAccountIdPaged(Integer accountId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findByAccountId");

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

	public List<RidePassenger> findByAccountIdPaged(Integer accountId, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByAccountId");
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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RidePassenger> findByTransactionIdPaged(String transactionId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findByTransactionId");

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

		return list(q.setParameter("transactionId", transactionId).setCacheable(true));
	}

	public List<RidePassenger> findByTransactionIdPaged(String transactionId, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findByTransactionId");
        q.setParameter("transactionId", transactionId).setCacheable(true);

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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<RidePassenger> findBySeatsPaged(Integer seats, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("RidePassenger.findBySeats");

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

		return list(q.setParameter("seats", seats).setCacheable(true));
	}

	public List<RidePassenger> findBySeatsPaged(Integer seats, Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findBySeats");
        q.setParameter("seats", seats).setCacheable(true);

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

        List<RidePassenger> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<RidePassenger> findAll() {
		return list(namedQuery("RidePassenger.findAll").setCacheable(true));
	}

	public List<RidePassenger> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findAll");
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

        List<RidePassenger> res = list(q);

        return res;
    }

	public List<RidePassenger> findAllPaged(Integer page, Integer limit, PagedResults<RidePassenger> results) {
        org.hibernate.query.Query q = namedQuery("RidePassenger.findAll");
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

        List<RidePassenger> res = list(q);
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

	public void delete(RidePassenger transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(RidePassenger transientInstance) {
        //log.debug("editing RidePassenger instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(RidePassenger transientInstance) {
        //log.debug("creating RidePassenger instance");
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
    
    public RidePassenger findById( RidePassengerId id) {
        //log.debug("getting RidePassenger instance with id: " + id);
        try {
            RidePassenger instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

