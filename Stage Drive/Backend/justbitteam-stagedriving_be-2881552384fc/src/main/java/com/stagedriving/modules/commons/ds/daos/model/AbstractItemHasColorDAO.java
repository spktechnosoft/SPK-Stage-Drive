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
 * Home object for domain model class ItemHasColor.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemHasColorDAO extends AbstractDAO<ItemHasColor> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemHasColorDAO.class);

	public AbstractItemHasColorDAO(SessionFactory session) {
		super(session);
	}

	public List<ItemHasColor> findByItem(Item item) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByItem");
		return list(q.setParameter("item", item).setCacheable(true));
	}
	public List<ItemHasColor> findByColor(Color color) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByColor");
		return list(q.setParameter("color", color).setCacheable(true));
	}
	public List<ItemHasColor> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<ItemHasColor> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<ItemHasColor> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<ItemHasColor> findByItemPaged(Item item, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasColor.findByItem");

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

		return list(q.setParameter("item", item).setCacheable(true));
	}

	public List<ItemHasColor> findByItemPaged(Item item, Integer page, Integer limit, PagedResults<ItemHasColor> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByItem");
        q.setParameter("item", item).setCacheable(true);

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

        List<ItemHasColor> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasColor> findByColorPaged(Color color, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasColor.findByColor");

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

		return list(q.setParameter("color", color).setCacheable(true));
	}

	public List<ItemHasColor> findByColorPaged(Color color, Integer page, Integer limit, PagedResults<ItemHasColor> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByColor");
        q.setParameter("color", color).setCacheable(true);

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

        List<ItemHasColor> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasColor> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasColor.findByCreated");

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

	public List<ItemHasColor> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<ItemHasColor> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByCreated");
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

        List<ItemHasColor> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasColor> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasColor.findByModified");

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

	public List<ItemHasColor> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<ItemHasColor> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByModified");
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

        List<ItemHasColor> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasColor> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasColor.findByVisible");

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

	public List<ItemHasColor> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<ItemHasColor> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findByVisible");
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

        List<ItemHasColor> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemHasColor> findAll() {
		return list(namedQuery("ItemHasColor.findAll").setCacheable(true));
	}

	public List<ItemHasColor> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findAll");
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

        List<ItemHasColor> res = list(q);

        return res;
    }

	public List<ItemHasColor> findAllPaged(Integer page, Integer limit, PagedResults<ItemHasColor> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasColor.findAll");
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

        List<ItemHasColor> res = list(q);
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

	public void delete(ItemHasColor transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(ItemHasColor transientInstance) {
        //log.debug("editing ItemHasColor instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(ItemHasColor transientInstance) {
        //log.debug("creating ItemHasColor instance");
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
    
    public ItemHasColor findById( ItemHasColorId id) {
        //log.debug("getting ItemHasColor instance with id: " + id);
        try {
            ItemHasColor instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

