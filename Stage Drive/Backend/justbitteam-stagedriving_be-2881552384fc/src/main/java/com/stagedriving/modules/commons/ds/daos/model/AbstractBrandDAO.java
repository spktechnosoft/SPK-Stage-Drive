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
 * Home object for domain model class Brand.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractBrandDAO extends AbstractDAO<Brand> {

    private final Logger log = new LoggerContext().getLogger(AbstractBrandDAO.class);

	public AbstractBrandDAO(SessionFactory session) {
		super(session);
	}

	public Brand findByUid(String uid) {
		return uniqueResult(namedQuery("Brand.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Brand> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Brand.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Brand> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Brand.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Brand> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Brand.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Brand> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Brand.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public Brand findByName(String name) {
		return uniqueResult(namedQuery("Brand.findByName").setParameter("name", name).setCacheable(true));
	}
	public List<Brand> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("Brand.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<Brand> findByBase(Boolean base) {
        org.hibernate.query.Query q = namedQuery("Brand.findByBase");
		return list(q.setParameter("base", base).setCacheable(true));
	}
	public List<Brand> findByUri(String uri) {
        org.hibernate.query.Query q = namedQuery("Brand.findByUri");
		return list(q.setParameter("uri", uri).setCacheable(true));
	}




	public List<Brand> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Brand.findByStatus");

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

	public List<Brand> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findByStatus");
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

        List<Brand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Brand> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Brand.findByVisible");

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

	public List<Brand> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findByVisible");
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

        List<Brand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Brand> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Brand.findByCreated");

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

	public List<Brand> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findByCreated");
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

        List<Brand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Brand> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Brand.findByModified");

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

	public List<Brand> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findByModified");
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

        List<Brand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Brand> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Brand.findByDescription");

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

	public List<Brand> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findByDescription");
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

        List<Brand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Brand> findByBasePaged(Boolean base, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Brand.findByBase");

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

		return list(q.setParameter("base", base).setCacheable(true));
	}

	public List<Brand> findByBasePaged(Boolean base, Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findByBase");
        q.setParameter("base", base).setCacheable(true);

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

        List<Brand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Brand> findByUriPaged(String uri, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Brand.findByUri");

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

		return list(q.setParameter("uri", uri).setCacheable(true));
	}

	public List<Brand> findByUriPaged(String uri, Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findByUri");
        q.setParameter("uri", uri).setCacheable(true);

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

        List<Brand> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Brand> findAll() {
		return list(namedQuery("Brand.findAll").setCacheable(true));
	}

	public List<Brand> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Brand.findAll");
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

        List<Brand> res = list(q);

        return res;
    }

	public List<Brand> findAllPaged(Integer page, Integer limit, PagedResults<Brand> results) {
        org.hibernate.query.Query q = namedQuery("Brand.findAll");
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

        List<Brand> res = list(q);
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

	public void delete(Brand transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Brand transientInstance) {
        //log.debug("editing Brand instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Brand transientInstance) {
        //log.debug("creating Brand instance");
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
    
    public Brand findById( Integer id) {
        //log.debug("getting Brand instance with id: " + id);
        try {
            Brand instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

