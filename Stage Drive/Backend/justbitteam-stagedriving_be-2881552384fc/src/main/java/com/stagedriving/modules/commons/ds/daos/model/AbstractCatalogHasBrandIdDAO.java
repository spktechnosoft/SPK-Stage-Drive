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
 * Home object for domain model class CatalogHasBrandId.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractCatalogHasBrandIdDAO extends AbstractDAO<CatalogHasBrandId> {

    private final Logger log = new LoggerContext().getLogger(AbstractCatalogHasBrandIdDAO.class);

	public AbstractCatalogHasBrandIdDAO(SessionFactory session) {
		super(session);
	}

	public List<CatalogHasBrandId> findByCatalogId(int catalogId) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findByCatalogId");
		return list(q.setParameter("catalogId", catalogId).setCacheable(true));
	}
	public List<CatalogHasBrandId> findByBrandId(int brandId) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findByBrandId");
		return list(q.setParameter("brandId", brandId).setCacheable(true));
	}


	public List<CatalogHasBrandId> findByCatalogIdPaged(int catalogId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findByCatalogId");

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

		return list(q.setParameter("catalogId", catalogId).setCacheable(true));
	}

	public List<CatalogHasBrandId> findByCatalogIdPaged(int catalogId, Integer page, Integer limit, PagedResults<CatalogHasBrandId> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findByCatalogId");
        q.setParameter("catalogId", catalogId).setCacheable(true);

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

        List<CatalogHasBrandId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasBrandId> findByBrandIdPaged(int brandId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findByBrandId");

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

		return list(q.setParameter("brandId", brandId).setCacheable(true));
	}

	public List<CatalogHasBrandId> findByBrandIdPaged(int brandId, Integer page, Integer limit, PagedResults<CatalogHasBrandId> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findByBrandId");
        q.setParameter("brandId", brandId).setCacheable(true);

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

        List<CatalogHasBrandId> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<CatalogHasBrandId> findAll() {
		return list(namedQuery("CatalogHasBrandId.findAll").setCacheable(true));
	}

	public List<CatalogHasBrandId> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findAll");
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

        List<CatalogHasBrandId> res = list(q);

        return res;
    }

	public List<CatalogHasBrandId> findAllPaged(Integer page, Integer limit, PagedResults<CatalogHasBrandId> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrandId.findAll");
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

        List<CatalogHasBrandId> res = list(q);
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

	public void delete(CatalogHasBrandId transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(CatalogHasBrandId transientInstance) {
        //log.debug("editing CatalogHasBrandId instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(CatalogHasBrandId transientInstance) {
        //log.debug("creating CatalogHasBrandId instance");
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

