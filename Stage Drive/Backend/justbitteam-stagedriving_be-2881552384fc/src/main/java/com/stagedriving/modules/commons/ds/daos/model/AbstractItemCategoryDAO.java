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
 * Home object for domain model class ItemCategory.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemCategoryDAO extends AbstractDAO<ItemCategory> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemCategoryDAO.class);

	public AbstractItemCategoryDAO(SessionFactory session) {
		super(session);
	}

	public List<ItemCategory> findByItemFamily(ItemFamily itemFamily) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByItemFamily");
		return list(q.setParameter("itemFamily", itemFamily).setCacheable(true));
	}
	public ItemCategory findByUid(String uid) {
		return uniqueResult(namedQuery("ItemCategory.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<ItemCategory> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<ItemCategory> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<ItemCategory> findByName(String name) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByName");
		return list(q.setParameter("name", name).setCacheable(true));
	}
	public List<ItemCategory> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<ItemCategory> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<ItemCategory> findByPosition(Integer position) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByPosition");
		return list(q.setParameter("position", position).setCacheable(true));
	}
	public List<ItemCategory> findByTag(String tag) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByTag");
		return list(q.setParameter("tag", tag).setCacheable(true));
	}


	public List<ItemCategory> findByItemFamilyPaged(ItemFamily itemFamily, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByItemFamily");

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

		return list(q.setParameter("itemFamily", itemFamily).setCacheable(true));
	}

	public List<ItemCategory> findByItemFamilyPaged(ItemFamily itemFamily, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByItemFamily");
        q.setParameter("itemFamily", itemFamily).setCacheable(true);

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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemCategory> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByCreated");

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

	public List<ItemCategory> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByCreated");
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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemCategory> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByModified");

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

	public List<ItemCategory> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByModified");
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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemCategory> findByNamePaged(String name, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByName");

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

	public List<ItemCategory> findByNamePaged(String name, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByName");
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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemCategory> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByVisible");

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

	public List<ItemCategory> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByVisible");
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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemCategory> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByDescription");

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

	public List<ItemCategory> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByDescription");
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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemCategory> findByPositionPaged(Integer position, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByPosition");

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

	public List<ItemCategory> findByPositionPaged(Integer position, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByPosition");
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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemCategory> findByTagPaged(String tag, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemCategory.findByTag");

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

	public List<ItemCategory> findByTagPaged(String tag, Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findByTag");
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

        List<ItemCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemCategory> findAll() {
		return list(namedQuery("ItemCategory.findAll").setCacheable(true));
	}

	public List<ItemCategory> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findAll");
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

        List<ItemCategory> res = list(q);

        return res;
    }

	public List<ItemCategory> findAllPaged(Integer page, Integer limit, PagedResults<ItemCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemCategory.findAll");
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

        List<ItemCategory> res = list(q);
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

	public void delete(ItemCategory transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(ItemCategory transientInstance) {
        //log.debug("editing ItemCategory instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(ItemCategory transientInstance) {
        //log.debug("creating ItemCategory instance");
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
    
    public ItemCategory findById( Integer id) {
        //log.debug("getting ItemCategory instance with id: " + id);
        try {
            ItemCategory instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

