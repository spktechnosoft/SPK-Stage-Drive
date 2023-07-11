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
 * Home object for domain model class NotificationMeta.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractNotificationMetaDAO extends AbstractDAO<NotificationMeta> {

    private final Logger log = new LoggerContext().getLogger(AbstractNotificationMetaDAO.class);

	public AbstractNotificationMetaDAO(SessionFactory session) {
		super(session);
	}

	public List<NotificationMeta> findByNotification(Notification notification) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByNotification");
		return list(q.setParameter("notification", notification).setCacheable(true));
	}
	public List<NotificationMeta> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<NotificationMeta> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<NotificationMeta> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public NotificationMeta findByUid(String uid) {
		return uniqueResult(namedQuery("NotificationMeta.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<NotificationMeta> findByMetaKey(String metaKey) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByMetaKey");
		return list(q.setParameter("metaKey", metaKey).setCacheable(true));
	}
	public List<NotificationMeta> findByMetaValue(String metaValue) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByMetaValue");
		return list(q.setParameter("metaValue", metaValue).setCacheable(true));
	}


	public List<NotificationMeta> findByNotificationPaged(Notification notification, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationMeta.findByNotification");

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

		return list(q.setParameter("notification", notification).setCacheable(true));
	}

	public List<NotificationMeta> findByNotificationPaged(Notification notification, Integer page, Integer limit, PagedResults<NotificationMeta> results) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByNotification");
        q.setParameter("notification", notification).setCacheable(true);

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

        List<NotificationMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationMeta> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationMeta.findByCreated");

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

	public List<NotificationMeta> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<NotificationMeta> results) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByCreated");
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

        List<NotificationMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationMeta> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationMeta.findByModified");

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

	public List<NotificationMeta> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<NotificationMeta> results) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByModified");
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

        List<NotificationMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationMeta> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationMeta.findByVisible");

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

	public List<NotificationMeta> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<NotificationMeta> results) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByVisible");
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

        List<NotificationMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<NotificationMeta> findByMetaKeyPaged(String metaKey, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationMeta.findByMetaKey");

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

		return list(q.setParameter("metaKey", metaKey).setCacheable(true));
	}

	public List<NotificationMeta> findByMetaKeyPaged(String metaKey, Integer page, Integer limit, PagedResults<NotificationMeta> results) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByMetaKey");
        q.setParameter("metaKey", metaKey).setCacheable(true);

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

        List<NotificationMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationMeta> findByMetaValuePaged(String metaValue, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationMeta.findByMetaValue");

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

		return list(q.setParameter("metaValue", metaValue).setCacheable(true));
	}

	public List<NotificationMeta> findByMetaValuePaged(String metaValue, Integer page, Integer limit, PagedResults<NotificationMeta> results) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findByMetaValue");
        q.setParameter("metaValue", metaValue).setCacheable(true);

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

        List<NotificationMeta> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<NotificationMeta> findAll() {
		return list(namedQuery("NotificationMeta.findAll").setCacheable(true));
	}

	public List<NotificationMeta> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findAll");
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

        List<NotificationMeta> res = list(q);

        return res;
    }

	public List<NotificationMeta> findAllPaged(Integer page, Integer limit, PagedResults<NotificationMeta> results) {
        org.hibernate.query.Query q = namedQuery("NotificationMeta.findAll");
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

        List<NotificationMeta> res = list(q);
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

	public void delete(NotificationMeta transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(NotificationMeta transientInstance) {
        //log.debug("editing NotificationMeta instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(NotificationMeta transientInstance) {
        //log.debug("creating NotificationMeta instance");
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
    
    public NotificationMeta findById( Integer id) {
        //log.debug("getting NotificationMeta instance with id: " + id);
        try {
            NotificationMeta instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

