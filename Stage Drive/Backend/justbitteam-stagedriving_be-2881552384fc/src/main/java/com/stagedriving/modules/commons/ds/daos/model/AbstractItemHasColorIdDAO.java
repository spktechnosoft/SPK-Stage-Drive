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
 * Home object for domain model class ItemHasColorId.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemHasColorIdDAO extends AbstractDAO<ItemHasColorId> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemHasColorIdDAO.class);

	public AbstractItemHasColorIdDAO(SessionFactory session) {
		super(session);
	}

	public List<ItemHasColorId> findByItemId(int itemId) {
        org.hibernate.query.Query q = namedQuery("ItemHasColorId.findByItemId");
		return list(q.setParameter("itemId", itemId).setCacheable(true));
	}
	public List<ItemHasColorId> findByColorId(int colorId) {
        org.hibernate.query.Query q = namedQuery("ItemHasColorId.findByColorId");
		return list(q.setParameter("colorId", colorId).setCacheable(true));
	}


	public List<ItemHasColorId> findByItemIdPaged(int itemId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasColorId.findByItemId");

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

		return list(q.setParameter("itemId", itemId).setCacheable(true));
	}

	public List<ItemHasColorId> findByItemIdPaged(int itemId, Integer page, Integer limit, PagedResults<ItemHasColorId> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColorId.findByItemId");
        q.setParameter("itemId", itemId).setCacheable(true);

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

        List<ItemHasColorId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasColorId> findByColorIdPaged(int colorId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasColorId.findByColorId");

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

		return list(q.setParameter("colorId", colorId).setCacheable(true));
	}

	public List<ItemHasColorId> findByColorIdPaged(int colorId, Integer page, Integer limit, PagedResults<ItemHasColorId> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColorId.findByColorId");
        q.setParameter("colorId", colorId).setCacheable(true);

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

        List<ItemHasColorId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemHasColorId> findAll() {
		return list(namedQuery("ItemHasColorId.findAll").setCacheable(true));
	}

	public List<ItemHasColorId> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("ItemHasColorId.findAll");
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

        List<ItemHasColorId> res = list(q);

        return res;
    }

	public List<ItemHasColorId> findAllPaged(Integer page, Integer limit, PagedResults<ItemHasColorId> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColorId.findAll");
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

        List<ItemHasColorId> res = list(q);
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

	public void delete(ItemHasColorId transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(ItemHasColorId transientInstance) {
        //log.debug("editing ItemHasColorId instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(ItemHasColorId transientInstance) {
        //log.debug("creating ItemHasColorId instance");
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

