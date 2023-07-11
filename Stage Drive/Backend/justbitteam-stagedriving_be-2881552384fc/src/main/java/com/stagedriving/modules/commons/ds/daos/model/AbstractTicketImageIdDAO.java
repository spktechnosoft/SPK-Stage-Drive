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
 * Home object for domain model class TicketImageId.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractTicketImageIdDAO extends AbstractDAO<TicketImageId> {

    private final Logger log = new LoggerContext().getLogger(AbstractTicketImageIdDAO.class);

	public AbstractTicketImageIdDAO(SessionFactory session) {
		super(session);
	}

	public List<TicketImageId> findByEventTicketId(int eventTicketId) {
        org.hibernate.query.Query q = namedQuery("TicketImageId.findByEventTicketId");
		return list(q.setParameter("eventTicketId", eventTicketId).setCacheable(true));
	}


	public List<TicketImageId> findByEventTicketIdPaged(int eventTicketId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("TicketImageId.findByEventTicketId");

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

		return list(q.setParameter("eventTicketId", eventTicketId).setCacheable(true));
	}

	public List<TicketImageId> findByEventTicketIdPaged(int eventTicketId, Integer page, Integer limit, PagedResults<TicketImageId> results) {
        org.hibernate.query.Query q = namedQuery("TicketImageId.findByEventTicketId");
        q.setParameter("eventTicketId", eventTicketId).setCacheable(true);

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

        List<TicketImageId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<TicketImageId> findAll() {
		return list(namedQuery("TicketImageId.findAll").setCacheable(true));
	}

	public List<TicketImageId> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("TicketImageId.findAll");
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

        List<TicketImageId> res = list(q);

        return res;
    }

	public List<TicketImageId> findAllPaged(Integer page, Integer limit, PagedResults<TicketImageId> results) {
        org.hibernate.query.Query q = namedQuery("TicketImageId.findAll");
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

        List<TicketImageId> res = list(q);
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

	public void delete(TicketImageId transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(TicketImageId transientInstance) {
        //log.debug("editing TicketImageId instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(TicketImageId transientInstance) {
        //log.debug("creating TicketImageId instance");
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

