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
 * Home object for domain model class NotificationRecipient.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractNotificationRecipientDAO extends AbstractDAO<NotificationRecipient> {

    private final Logger log = new LoggerContext().getLogger(AbstractNotificationRecipientDAO.class);

	public AbstractNotificationRecipientDAO(SessionFactory session) {
		super(session);
	}

	public List<NotificationRecipient> findByNotification(Notification notification) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByNotification");
		return list(q.setParameter("notification", notification).setCacheable(true));
	}
	public NotificationRecipient findByUid(String uid) {
		return uniqueResult(namedQuery("NotificationRecipient.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<NotificationRecipient> findByDest(String dest) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByDest");
		return list(q.setParameter("dest", dest).setCacheable(true));
	}
	public List<NotificationRecipient> findByClickedAt(Date clickedAt) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByClickedAt");
		return list(q.setParameter("clickedAt", clickedAt).setCacheable(true));
	}
	public List<NotificationRecipient> findByReadAt(Date readAt) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByReadAt");
		return list(q.setParameter("readAt", readAt).setCacheable(true));
	}
	public List<NotificationRecipient> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<NotificationRecipient> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<NotificationRecipient> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<NotificationRecipient> findByStatusDescription(String statusDescription) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByStatusDescription");
		return list(q.setParameter("statusDescription", statusDescription).setCacheable(true));
	}
	public List<NotificationRecipient> findByType(String type) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByType");
		return list(q.setParameter("type", type).setCacheable(true));
	}
	public List<NotificationRecipient> findByAccountId(Integer accountId) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByAccountId");
		return list(q.setParameter("accountId", accountId).setCacheable(true));
	}
	public NotificationRecipient findByExtId(String extId) {
		return uniqueResult(namedQuery("NotificationRecipient.findByExtId").setParameter("extId", extId).setCacheable(true));
	}
	public List<NotificationRecipient> findByOs(String os) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByOs");
		return list(q.setParameter("os", os).setCacheable(true));
	}


	public List<NotificationRecipient> findByNotificationPaged(Notification notification, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByNotification");

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

	public List<NotificationRecipient> findByNotificationPaged(Notification notification, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByNotification");
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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<NotificationRecipient> findByDestPaged(String dest, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByDest");

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

		return list(q.setParameter("dest", dest).setCacheable(true));
	}

	public List<NotificationRecipient> findByDestPaged(String dest, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByDest");
        q.setParameter("dest", dest).setCacheable(true);

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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByClickedAtPaged(Date clickedAt, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByClickedAt");

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

		return list(q.setParameter("clickedAt", clickedAt).setCacheable(true));
	}

	public List<NotificationRecipient> findByClickedAtPaged(Date clickedAt, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByClickedAt");
        q.setParameter("clickedAt", clickedAt).setCacheable(true);

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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByReadAtPaged(Date readAt, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByReadAt");

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

		return list(q.setParameter("readAt", readAt).setCacheable(true));
	}

	public List<NotificationRecipient> findByReadAtPaged(Date readAt, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByReadAt");
        q.setParameter("readAt", readAt).setCacheable(true);

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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByCreated");

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

	public List<NotificationRecipient> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByCreated");
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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByModified");

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

	public List<NotificationRecipient> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByModified");
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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByStatus");

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

	public List<NotificationRecipient> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByStatus");
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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByStatusDescriptionPaged(String statusDescription, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByStatusDescription");

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

		return list(q.setParameter("statusDescription", statusDescription).setCacheable(true));
	}

	public List<NotificationRecipient> findByStatusDescriptionPaged(String statusDescription, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByStatusDescription");
        q.setParameter("statusDescription", statusDescription).setCacheable(true);

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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByTypePaged(String type, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByType");

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

		return list(q.setParameter("type", type).setCacheable(true));
	}

	public List<NotificationRecipient> findByTypePaged(String type, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByType");
        q.setParameter("type", type).setCacheable(true);

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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<NotificationRecipient> findByAccountIdPaged(Integer accountId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByAccountId");

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

		return list(q.setParameter("accountId", accountId).setCacheable(true));
	}

	public List<NotificationRecipient> findByAccountIdPaged(Integer accountId, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByAccountId");
        q.setParameter("accountId", accountId).setCacheable(true);

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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<NotificationRecipient> findByOsPaged(String os, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByOs");

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

		return list(q.setParameter("os", os).setCacheable(true));
	}

	public List<NotificationRecipient> findByOsPaged(String os, Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findByOs");
        q.setParameter("os", os).setCacheable(true);

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

        List<NotificationRecipient> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<NotificationRecipient> findAll() {
		return list(namedQuery("NotificationRecipient.findAll").setCacheable(true));
	}

	public List<NotificationRecipient> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findAll");
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

        List<NotificationRecipient> res = list(q);

        return res;
    }

	public List<NotificationRecipient> findAllPaged(Integer page, Integer limit, PagedResults<NotificationRecipient> results) {
        org.hibernate.query.Query q = namedQuery("NotificationRecipient.findAll");
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

        List<NotificationRecipient> res = list(q);
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

	public void delete(NotificationRecipient transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(NotificationRecipient transientInstance) {
        //log.debug("editing NotificationRecipient instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(NotificationRecipient transientInstance) {
        //log.debug("creating NotificationRecipient instance");
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
    
    public NotificationRecipient findById( Integer id) {
        //log.debug("getting NotificationRecipient instance with id: " + id);
        try {
            NotificationRecipient instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

