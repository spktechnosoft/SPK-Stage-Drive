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
 * Home object for domain model class ItemHasBrand.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemHasBrandDAO extends AbstractDAO<ItemHasBrand> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemHasBrandDAO.class);

	public AbstractItemHasBrandDAO(SessionFactory session) {
		super(session);
	}

	public List<ItemHasBrand> findByBrand(Brand brand) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByBrand");
		return list(q.setParameter("brand", brand).setCacheable(true));
	}
	public List<ItemHasBrand> findByItem(Item item) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByItem");
		return list(q.setParameter("item", item).setCacheable(true));
	}
	public List<ItemHasBrand> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<ItemHasBrand> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<ItemHasBrand> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<ItemHasBrand> findByBrandPaged(Brand brand, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByBrand");

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

		return list(q.setParameter("brand", brand).setCacheable(true));
	}

	public List<ItemHasBrand> findByBrandPaged(Brand brand, Integer page, Integer limit, PagedResults<ItemHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByBrand");
        q.setParameter("brand", brand).setCacheable(true);

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

        List<ItemHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasBrand> findByItemPaged(Item item, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByItem");

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

	public List<ItemHasBrand> findByItemPaged(Item item, Integer page, Integer limit, PagedResults<ItemHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByItem");
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

        List<ItemHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasBrand> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByCreated");

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

	public List<ItemHasBrand> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<ItemHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByCreated");
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

        List<ItemHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasBrand> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByModified");

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

	public List<ItemHasBrand> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<ItemHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByModified");
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

        List<ItemHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasBrand> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByVisible");

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

	public List<ItemHasBrand> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<ItemHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findByVisible");
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

        List<ItemHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemHasBrand> findAll() {
		return list(namedQuery("ItemHasBrand.findAll").setCacheable(true));
	}

	public List<ItemHasBrand> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findAll");
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

        List<ItemHasBrand> res = list(q);

        return res;
    }

	public List<ItemHasBrand> findAllPaged(Integer page, Integer limit, PagedResults<ItemHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasBrand.findAll");
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

        List<ItemHasBrand> res = list(q);
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

	public void delete(ItemHasBrand transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(ItemHasBrand transientInstance) {
        //log.debug("editing ItemHasBrand instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(ItemHasBrand transientInstance) {
        //log.debug("creating ItemHasBrand instance");
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
    
    public ItemHasBrand findById( ItemHasBrandId id) {
        //log.debug("getting ItemHasBrand instance with id: " + id);
        try {
            ItemHasBrand instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

