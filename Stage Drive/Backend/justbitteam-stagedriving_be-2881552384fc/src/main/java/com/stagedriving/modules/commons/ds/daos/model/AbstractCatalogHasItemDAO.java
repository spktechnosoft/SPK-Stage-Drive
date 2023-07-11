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
 * Home object for domain model class CatalogHasItem.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractCatalogHasItemDAO extends AbstractDAO<CatalogHasItem> {

    private final Logger log = new LoggerContext().getLogger(AbstractCatalogHasItemDAO.class);

	public AbstractCatalogHasItemDAO(SessionFactory session) {
		super(session);
	}

	public List<CatalogHasItem> findByItem(Item item) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByItem");
		return list(q.setParameter("item", item).setCacheable(true));
	}
	public List<CatalogHasItem> findByCatalog(Catalog catalog) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByCatalog");
		return list(q.setParameter("catalog", catalog).setCacheable(true));
	}
	public List<CatalogHasItem> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<CatalogHasItem> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<CatalogHasItem> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<CatalogHasItem> findByItemPaged(Item item, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByItem");

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

	public List<CatalogHasItem> findByItemPaged(Item item, Integer page, Integer limit, PagedResults<CatalogHasItem> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByItem");
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

        List<CatalogHasItem> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasItem> findByCatalogPaged(Catalog catalog, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByCatalog");

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

		return list(q.setParameter("catalog", catalog).setCacheable(true));
	}

	public List<CatalogHasItem> findByCatalogPaged(Catalog catalog, Integer page, Integer limit, PagedResults<CatalogHasItem> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByCatalog");
        q.setParameter("catalog", catalog).setCacheable(true);

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

        List<CatalogHasItem> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasItem> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByCreated");

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

	public List<CatalogHasItem> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<CatalogHasItem> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByCreated");
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

        List<CatalogHasItem> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasItem> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByModified");

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

	public List<CatalogHasItem> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<CatalogHasItem> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByModified");
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

        List<CatalogHasItem> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasItem> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByVisible");

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

	public List<CatalogHasItem> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<CatalogHasItem> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findByVisible");
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

        List<CatalogHasItem> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<CatalogHasItem> findAll() {
		return list(namedQuery("CatalogHasItem.findAll").setCacheable(true));
	}

	public List<CatalogHasItem> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findAll");
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

        List<CatalogHasItem> res = list(q);

        return res;
    }

	public List<CatalogHasItem> findAllPaged(Integer page, Integer limit, PagedResults<CatalogHasItem> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasItem.findAll");
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

        List<CatalogHasItem> res = list(q);
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

	public void delete(CatalogHasItem transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(CatalogHasItem transientInstance) {
        //log.debug("editing CatalogHasItem instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(CatalogHasItem transientInstance) {
        //log.debug("creating CatalogHasItem instance");
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
    
    public CatalogHasItem findById( CatalogHasItemId id) {
        //log.debug("getting CatalogHasItem instance with id: " + id);
        try {
            CatalogHasItem instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

