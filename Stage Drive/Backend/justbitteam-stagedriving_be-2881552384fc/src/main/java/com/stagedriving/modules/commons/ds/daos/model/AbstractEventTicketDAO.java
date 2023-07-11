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
 * Home object for domain model class EventTicket.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventTicketDAO extends AbstractDAO<EventTicket> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventTicketDAO.class);

	public AbstractEventTicketDAO(SessionFactory session) {
		super(session);
	}

	public EventTicket findByUid(String uid) {
		return uniqueResult(namedQuery("EventTicket.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<EventTicket> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<EventTicket> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<EventTicket> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<EventTicket> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<EventTicket> findByName(String name) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByName");
		return list(q.setParameter("name", name).setCacheable(true));
	}
	public List<EventTicket> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}




	public List<EventTicket> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventTicket.findByCreated");

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

	public List<EventTicket> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<EventTicket> results) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByCreated");
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

        List<EventTicket> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventTicket> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventTicket.findByModified");

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

	public List<EventTicket> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<EventTicket> results) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByModified");
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

        List<EventTicket> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventTicket> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventTicket.findByVisible");

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

	public List<EventTicket> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<EventTicket> results) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByVisible");
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

        List<EventTicket> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventTicket> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventTicket.findByStatus");

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

	public List<EventTicket> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<EventTicket> results) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByStatus");
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

        List<EventTicket> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventTicket> findByNamePaged(String name, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventTicket.findByName");

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

		return list(q.setParameter("name", name).setCacheable(true));
	}

	public List<EventTicket> findByNamePaged(String name, Integer page, Integer limit, PagedResults<EventTicket> results) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByName");
        q.setParameter("name", name).setCacheable(true);

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

        List<EventTicket> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<EventTicket> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("EventTicket.findByDescription");

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

		return list(q.setParameter("description", description).setCacheable(true));
	}

	public List<EventTicket> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<EventTicket> results) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findByDescription");
        q.setParameter("description", description).setCacheable(true);

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

        List<EventTicket> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<EventTicket> findAll() {
		return list(namedQuery("EventTicket.findAll").setCacheable(true));
	}

	public List<EventTicket> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findAll");
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

        List<EventTicket> res = list(q);

        return res;
    }

	public List<EventTicket> findAllPaged(Integer page, Integer limit, PagedResults<EventTicket> results) {
        org.hibernate.query.Query q = namedQuery("EventTicket.findAll");
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

        List<EventTicket> res = list(q);
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

	public void delete(EventTicket transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(EventTicket transientInstance) {
        //log.debug("editing EventTicket instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(EventTicket transientInstance) {
        //log.debug("creating EventTicket instance");
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
    
    public EventTicket findById( Integer id) {
        //log.debug("getting EventTicket instance with id: " + id);
        try {
            EventTicket instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

