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
 * Home object for domain model class ItemFamily.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemFamilyDAO extends AbstractDAO<ItemFamily> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemFamilyDAO.class);

	public AbstractItemFamilyDAO(SessionFactory session) {
		super(session);
	}

	public ItemFamily findByUid(String uid) {
		return uniqueResult(namedQuery("ItemFamily.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<ItemFamily> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<ItemFamily> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public ItemFamily findByName(String name) {
		return uniqueResult(namedQuery("ItemFamily.findByName").setParameter("name", name).setCacheable(true));
	}
	public List<ItemFamily> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<ItemFamily> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<ItemFamily> findBySmallUri(String smallUri) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findBySmallUri");
		return list(q.setParameter("smallUri", smallUri).setCacheable(true));
	}
	public List<ItemFamily> findByNormalUri(String normalUri) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByNormalUri");
		return list(q.setParameter("normalUri", normalUri).setCacheable(true));
	}
	public List<ItemFamily> findByLargeUri(String largeUri) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByLargeUri");
		return list(q.setParameter("largeUri", largeUri).setCacheable(true));
	}
	public List<ItemFamily> findByPosition(Integer position) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByPosition");
		return list(q.setParameter("position", position).setCacheable(true));
	}
	public List<ItemFamily> findByTag(String tag) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByTag");
		return list(q.setParameter("tag", tag).setCacheable(true));
	}




	public List<ItemFamily> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByCreated");

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

	public List<ItemFamily> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByCreated");
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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemFamily> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByModified");

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

	public List<ItemFamily> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByModified");
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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemFamily> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByDescription");

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

	public List<ItemFamily> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByDescription");
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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemFamily> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByVisible");

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

	public List<ItemFamily> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByVisible");
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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemFamily> findBySmallUriPaged(String smallUri, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findBySmallUri");

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

		return list(q.setParameter("smallUri", smallUri).setCacheable(true));
	}

	public List<ItemFamily> findBySmallUriPaged(String smallUri, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findBySmallUri");
        q.setParameter("smallUri", smallUri).setCacheable(true);

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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemFamily> findByNormalUriPaged(String normalUri, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByNormalUri");

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

		return list(q.setParameter("normalUri", normalUri).setCacheable(true));
	}

	public List<ItemFamily> findByNormalUriPaged(String normalUri, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByNormalUri");
        q.setParameter("normalUri", normalUri).setCacheable(true);

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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemFamily> findByLargeUriPaged(String largeUri, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByLargeUri");

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

		return list(q.setParameter("largeUri", largeUri).setCacheable(true));
	}

	public List<ItemFamily> findByLargeUriPaged(String largeUri, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByLargeUri");
        q.setParameter("largeUri", largeUri).setCacheable(true);

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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemFamily> findByPositionPaged(Integer position, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByPosition");

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

		return list(q.setParameter("position", position).setCacheable(true));
	}

	public List<ItemFamily> findByPositionPaged(Integer position, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByPosition");
        q.setParameter("position", position).setCacheable(true);

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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemFamily> findByTagPaged(String tag, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemFamily.findByTag");

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

		return list(q.setParameter("tag", tag).setCacheable(true));
	}

	public List<ItemFamily> findByTagPaged(String tag, Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findByTag");
        q.setParameter("tag", tag).setCacheable(true);

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

        List<ItemFamily> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemFamily> findAll() {
		return list(namedQuery("ItemFamily.findAll").setCacheable(true));
	}

	public List<ItemFamily> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findAll");
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

        List<ItemFamily> res = list(q);

        return res;
    }

	public List<ItemFamily> findAllPaged(Integer page, Integer limit, PagedResults<ItemFamily> results) {
        org.hibernate.query.Query q = namedQuery("ItemFamily.findAll");
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

        List<ItemFamily> res = list(q);
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

	public void delete(ItemFamily transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(ItemFamily transientInstance) {
        //log.debug("editing ItemFamily instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(ItemFamily transientInstance) {
        //log.debug("creating ItemFamily instance");
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
    
    public ItemFamily findById( Integer id) {
        //log.debug("getting ItemFamily instance with id: " + id);
        try {
            ItemFamily instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

