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
 * Home object for domain model class ItemHasCategory.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemHasCategoryDAO extends AbstractDAO<ItemHasCategory> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemHasCategoryDAO.class);

	public AbstractItemHasCategoryDAO(SessionFactory session) {
		super(session);
	}

	public List<ItemHasCategory> findByItem(Item item) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByItem");
		return list(q.setParameter("item", item).setCacheable(true));
	}
	public List<ItemHasCategory> findByItemCategory(ItemCategory itemCategory) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByItemCategory");
		return list(q.setParameter("itemCategory", itemCategory).setCacheable(true));
	}
	public List<ItemHasCategory> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<ItemHasCategory> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<ItemHasCategory> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public ItemHasCategory findByUid(String uid) {
		return uniqueResult(namedQuery("ItemHasCategory.findByUid").setParameter("uid", uid).setCacheable(true));
	}


	public List<ItemHasCategory> findByItemPaged(Item item, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByItem");

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

	public List<ItemHasCategory> findByItemPaged(Item item, Integer page, Integer limit, PagedResults<ItemHasCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByItem");
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

        List<ItemHasCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasCategory> findByItemCategoryPaged(ItemCategory itemCategory, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByItemCategory");

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

		return list(q.setParameter("itemCategory", itemCategory).setCacheable(true));
	}

	public List<ItemHasCategory> findByItemCategoryPaged(ItemCategory itemCategory, Integer page, Integer limit, PagedResults<ItemHasCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByItemCategory");
        q.setParameter("itemCategory", itemCategory).setCacheable(true);

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

        List<ItemHasCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasCategory> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByCreated");

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

	public List<ItemHasCategory> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<ItemHasCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByCreated");
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

        List<ItemHasCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasCategory> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByModified");

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

	public List<ItemHasCategory> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<ItemHasCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByModified");
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

        List<ItemHasCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasCategory> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByVisible");

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

	public List<ItemHasCategory> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<ItemHasCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findByVisible");
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

        List<ItemHasCategory> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }





	public List<ItemHasCategory> findAll() {
		return list(namedQuery("ItemHasCategory.findAll").setCacheable(true));
	}

	public List<ItemHasCategory> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findAll");
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

        List<ItemHasCategory> res = list(q);

        return res;
    }

	public List<ItemHasCategory> findAllPaged(Integer page, Integer limit, PagedResults<ItemHasCategory> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasCategory.findAll");
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

        List<ItemHasCategory> res = list(q);
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

	public void delete(ItemHasCategory transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(ItemHasCategory transientInstance) {
        //log.debug("editing ItemHasCategory instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(ItemHasCategory transientInstance) {
        //log.debug("creating ItemHasCategory instance");
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
    
    public ItemHasCategory findById( Integer id) {
        //log.debug("getting ItemHasCategory instance with id: " + id);
        try {
            ItemHasCategory instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

