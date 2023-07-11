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
 * Home object for domain model class Catalog.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractCatalogDAO extends AbstractDAO<Catalog> {

    private final Logger log = new LoggerContext().getLogger(AbstractCatalogDAO.class);

	public AbstractCatalogDAO(SessionFactory session) {
		super(session);
	}

	public Catalog findByUid(String uid) {
		return uniqueResult(namedQuery("Catalog.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Catalog> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Catalog> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Catalog> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Catalog> findByCardinality(Integer cardinality) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByCardinality");
		return list(q.setParameter("cardinality", cardinality).setCacheable(true));
	}
	public List<Catalog> findByCategory(String category) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByCategory");
		return list(q.setParameter("category", category).setCacheable(true));
	}
	public Catalog findByName(String name) {
		return uniqueResult(namedQuery("Catalog.findByName").setParameter("name", name).setCacheable(true));
	}
	public List<Catalog> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<Catalog> findByEventid(String eventid) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByEventid");
		return list(q.setParameter("eventid", eventid).setCacheable(true));
	}




	public List<Catalog> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Catalog.findByCreated");

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

	public List<Catalog> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByCreated");
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

        List<Catalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Catalog> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Catalog.findByModified");

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

	public List<Catalog> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByModified");
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

        List<Catalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Catalog> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Catalog.findByVisible");

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

	public List<Catalog> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByVisible");
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

        List<Catalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Catalog> findByCardinalityPaged(Integer cardinality, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Catalog.findByCardinality");

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

		return list(q.setParameter("cardinality", cardinality).setCacheable(true));
	}

	public List<Catalog> findByCardinalityPaged(Integer cardinality, Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByCardinality");
        q.setParameter("cardinality", cardinality).setCacheable(true);

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

        List<Catalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Catalog> findByCategoryPaged(String category, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Catalog.findByCategory");

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

		return list(q.setParameter("category", category).setCacheable(true));
	}

	public List<Catalog> findByCategoryPaged(String category, Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByCategory");
        q.setParameter("category", category).setCacheable(true);

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

        List<Catalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Catalog> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Catalog.findByDescription");

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

	public List<Catalog> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByDescription");
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

        List<Catalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Catalog> findByEventidPaged(String eventid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Catalog.findByEventid");

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

		return list(q.setParameter("eventid", eventid).setCacheable(true));
	}

	public List<Catalog> findByEventidPaged(String eventid, Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findByEventid");
        q.setParameter("eventid", eventid).setCacheable(true);

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

        List<Catalog> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Catalog> findAll() {
		return list(namedQuery("Catalog.findAll").setCacheable(true));
	}

	public List<Catalog> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Catalog.findAll");
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

        List<Catalog> res = list(q);

        return res;
    }

	public List<Catalog> findAllPaged(Integer page, Integer limit, PagedResults<Catalog> results) {
        org.hibernate.query.Query q = namedQuery("Catalog.findAll");
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

        List<Catalog> res = list(q);
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

	public void delete(Catalog transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Catalog transientInstance) {
        //log.debug("editing Catalog instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Catalog transientInstance) {
        //log.debug("creating Catalog instance");
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
    
    public Catalog findById( Integer id) {
        //log.debug("getting Catalog instance with id: " + id);
        try {
            Catalog instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

