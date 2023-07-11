package com.stagedriving.modules.commons.ds.daos.model;
// Generated 12-giu-2020 12.45.52 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.entities.*;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Home object for domain model class Booking.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractBookingDAO extends AbstractDAO<Booking> {

    private final Logger log = new LoggerContext().getLogger(AbstractBookingDAO.class);

	public AbstractBookingDAO(SessionFactory session) {
		super(session);
	}

	public Booking findByUid(String uid) {
		return uniqueResult(namedQuery("Booking.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Booking> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Booking.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Booking> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Booking.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Booking> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Booking.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Booking> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Booking.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Booking> findByWithTicket(Boolean withTicket) {
        org.hibernate.query.Query q = namedQuery("Booking.findByWithTicket");
		return list(q.setParameter("withTicket", withTicket).setCacheable(true));
	}




	public List<Booking> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Booking.findByCreated");

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

	public List<Booking> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Booking> results) {
        org.hibernate.query.Query q = namedQuery("Booking.findByCreated");
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

        List<Booking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Booking> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Booking.findByModified");

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

	public List<Booking> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Booking> results) {
        org.hibernate.query.Query q = namedQuery("Booking.findByModified");
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

        List<Booking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Booking> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Booking.findByVisible");

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

	public List<Booking> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Booking> results) {
        org.hibernate.query.Query q = namedQuery("Booking.findByVisible");
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

        List<Booking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Booking> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Booking.findByStatus");

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

	public List<Booking> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Booking> results) {
        org.hibernate.query.Query q = namedQuery("Booking.findByStatus");
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

        List<Booking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Booking> findByWithTicketPaged(Boolean withTicket, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Booking.findByWithTicket");

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

		return list(q.setParameter("withTicket", withTicket).setCacheable(true));
	}

	public List<Booking> findByWithTicketPaged(Boolean withTicket, Integer page, Integer limit, PagedResults<Booking> results) {
        org.hibernate.query.Query q = namedQuery("Booking.findByWithTicket");
        q.setParameter("withTicket", withTicket).setCacheable(true);

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

        List<Booking> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Booking> findAll() {
		return list(namedQuery("Booking.findAll").setCacheable(true));
	}

	public List<Booking> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Booking.findAll");
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

        List<Booking> res = list(q);

        return res;
    }

	public List<Booking> findAllPaged(Integer page, Integer limit, PagedResults<Booking> results) {
        org.hibernate.query.Query q = namedQuery("Booking.findAll");
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

        List<Booking> res = list(q);
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

	public void delete(Booking transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Booking transientInstance) {
        //log.debug("editing Booking instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Booking transientInstance) {
        //log.debug("creating Booking instance");
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
    
    public Booking findById( Integer id) {
        //log.debug("getting Booking instance with id: " + id);
        try {
            Booking instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

