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
 * Home object for domain model class CatalogHasBrand.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractCatalogHasBrandDAO extends AbstractDAO<CatalogHasBrand> {

    private final Logger log = new LoggerContext().getLogger(AbstractCatalogHasBrandDAO.class);

	public AbstractCatalogHasBrandDAO(SessionFactory session) {
		super(session);
	}

	public List<CatalogHasBrand> findByBrand(Brand brand) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByBrand");
		return list(q.setParameter("brand", brand).setCacheable(true));
	}
	public List<CatalogHasBrand> findByCatalog(Catalog catalog) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByCatalog");
		return list(q.setParameter("catalog", catalog).setCacheable(true));
	}
	public List<CatalogHasBrand> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<CatalogHasBrand> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<CatalogHasBrand> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}


	public List<CatalogHasBrand> findByBrandPaged(Brand brand, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByBrand");

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

	public List<CatalogHasBrand> findByBrandPaged(Brand brand, Integer page, Integer limit, PagedResults<CatalogHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByBrand");
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

        List<CatalogHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasBrand> findByCatalogPaged(Catalog catalog, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByCatalog");

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

	public List<CatalogHasBrand> findByCatalogPaged(Catalog catalog, Integer page, Integer limit, PagedResults<CatalogHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByCatalog");
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

        List<CatalogHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasBrand> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByCreated");

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

	public List<CatalogHasBrand> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<CatalogHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByCreated");
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

        List<CatalogHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasBrand> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByModified");

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

	public List<CatalogHasBrand> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<CatalogHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByModified");
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

        List<CatalogHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<CatalogHasBrand> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByVisible");

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

	public List<CatalogHasBrand> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<CatalogHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findByVisible");
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

        List<CatalogHasBrand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<CatalogHasBrand> findAll() {
		return list(namedQuery("CatalogHasBrand.findAll").setCacheable(true));
	}

	public List<CatalogHasBrand> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findAll");
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

        List<CatalogHasBrand> res = list(q);

        return res;
    }

	public List<CatalogHasBrand> findAllPaged(Integer page, Integer limit, PagedResults<CatalogHasBrand> results) {
        org.hibernate.query.Query q = namedQuery("CatalogHasBrand.findAll");
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

        List<CatalogHasBrand> res = list(q);
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

	public void delete(CatalogHasBrand transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(CatalogHasBrand transientInstance) {
        //log.debug("editing CatalogHasBrand instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(CatalogHasBrand transientInstance) {
        //log.debug("creating CatalogHasBrand instance");
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
    
    public CatalogHasBrand findById( CatalogHasBrandId id) {
        //log.debug("getting CatalogHasBrand instance with id: " + id);
        try {
            CatalogHasBrand instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

