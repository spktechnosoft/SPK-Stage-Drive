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
 * Home object for domain model class ItemHasAction.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemHasActionDAO extends AbstractDAO<ItemHasAction> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemHasActionDAO.class);

	public AbstractItemHasActionDAO(SessionFactory session) {
		super(session);
	}

	public List<ItemHasAction> findByItem(Item item) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByItem");
		return list(q.setParameter("item", item).setCacheable(true));
	}
	public List<ItemHasAction> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<ItemHasAction> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<ItemHasAction> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<ItemHasAction> findByAccountid(Integer accountid) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public List<ItemHasAction> findByTaxonomy(String taxonomy) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByTaxonomy");
		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}
	public ItemHasAction findByUid(String uid) {
		return uniqueResult(namedQuery("ItemHasAction.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<ItemHasAction> findByContent(String content) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByContent");
		return list(q.setParameter("content", content).setCacheable(true));
	}


	public List<ItemHasAction> findByItemPaged(Item item, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasAction.findByItem");

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

	public List<ItemHasAction> findByItemPaged(Item item, Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByItem");
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

        List<ItemHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasAction> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasAction.findByCreated");

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

	public List<ItemHasAction> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByCreated");
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

        List<ItemHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasAction> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasAction.findByModified");

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

	public List<ItemHasAction> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByModified");
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

        List<ItemHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasAction> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasAction.findByVisible");

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

	public List<ItemHasAction> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByVisible");
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

        List<ItemHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasAction> findByAccountidPaged(Integer accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasAction.findByAccountid");

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

		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}

	public List<ItemHasAction> findByAccountidPaged(Integer accountid, Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByAccountid");
        q.setParameter("accountid", accountid).setCacheable(true);

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

        List<ItemHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<ItemHasAction> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasAction.findByTaxonomy");

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

		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}

	public List<ItemHasAction> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByTaxonomy");
        q.setParameter("taxonomy", taxonomy).setCacheable(true);

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

        List<ItemHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemHasAction> findByContentPaged(String content, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("ItemHasAction.findByContent");

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

		return list(q.setParameter("content", content).setCacheable(true));
	}

	public List<ItemHasAction> findByContentPaged(String content, Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findByContent");
        q.setParameter("content", content).setCacheable(true);

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

        List<ItemHasAction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<ItemHasAction> findAll() {
		return list(namedQuery("ItemHasAction.findAll").setCacheable(true));
	}

	public List<ItemHasAction> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findAll");
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

        List<ItemHasAction> res = list(q);

        return res;
    }

	public List<ItemHasAction> findAllPaged(Integer page, Integer limit, PagedResults<ItemHasAction> results) {
        org.hibernate.query.Query q = namedQuery("ItemHasAction.findAll");
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

        List<ItemHasAction> res = list(q);
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

	public void delete(ItemHasAction transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(ItemHasAction transientInstance) {
        //log.debug("editing ItemHasAction instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(ItemHasAction transientInstance) {
        //log.debug("creating ItemHasAction instance");
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
    
    public ItemHasAction findById( ItemHasActionId id) {
        //log.debug("getting ItemHasAction instance with id: " + id);
        try {
            ItemHasAction instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

