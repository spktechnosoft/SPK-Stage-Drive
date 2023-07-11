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
 * Home object for domain model class Notification.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractNotificationDAO extends AbstractDAO<Notification> {

    private final Logger log = new LoggerContext().getLogger(AbstractNotificationDAO.class);

	public AbstractNotificationDAO(SessionFactory session) {
		super(session);
	}

	public Notification findByUid(String uid) {
		return uniqueResult(namedQuery("Notification.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Notification> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Notification.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Notification> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Notification.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Notification> findByName(String name) {
        org.hibernate.query.Query q = namedQuery("Notification.findByName");
		return list(q.setParameter("name", name).setCacheable(true));
	}
	public List<Notification> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("Notification.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<Notification> findByContent(String content) {
        org.hibernate.query.Query q = namedQuery("Notification.findByContent");
		return list(q.setParameter("content", content).setCacheable(true));
	}
	public List<Notification> findByExpires(Date expires) {
        org.hibernate.query.Query q = namedQuery("Notification.findByExpires");
		return list(q.setParameter("expires", expires).setCacheable(true));
	}
	public List<Notification> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Notification.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Notification> findByType(String type) {
        org.hibernate.query.Query q = namedQuery("Notification.findByType");
		return list(q.setParameter("type", type).setCacheable(true));
	}
	public List<Notification> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Notification.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Notification> findBySync(Boolean sync) {
        org.hibernate.query.Query q = namedQuery("Notification.findBySync");
		return list(q.setParameter("sync", sync).setCacheable(true));
	}
	public List<Notification> findByStatusDescription(String statusDescription) {
        org.hibernate.query.Query q = namedQuery("Notification.findByStatusDescription");
		return list(q.setParameter("statusDescription", statusDescription).setCacheable(true));
	}
	public List<Notification> findBySender(String sender) {
        org.hibernate.query.Query q = namedQuery("Notification.findBySender");
		return list(q.setParameter("sender", sender).setCacheable(true));
	}
	public List<Notification> findByScope(String scope) {
        org.hibernate.query.Query q = namedQuery("Notification.findByScope");
		return list(q.setParameter("scope", scope).setCacheable(true));
	}
	public List<Notification> findByReplyAddress(String replyAddress) {
        org.hibernate.query.Query q = namedQuery("Notification.findByReplyAddress");
		return list(q.setParameter("replyAddress", replyAddress).setCacheable(true));
	}
	public Notification findByExtId(String extId) {
		return uniqueResult(namedQuery("Notification.findByExtId").setParameter("extId", extId).setCacheable(true));
	}
	public List<Notification> findByJobId(String jobId) {
        org.hibernate.query.Query q = namedQuery("Notification.findByJobId");
		return list(q.setParameter("jobId", jobId).setCacheable(true));
	}
	public List<Notification> findByWhenSend(Date whenSend) {
        org.hibernate.query.Query q = namedQuery("Notification.findByWhenSend");
		return list(q.setParameter("whenSend", whenSend).setCacheable(true));
	}
	public Notification findByTag(String tag) {
		return uniqueResult(namedQuery("Notification.findByTag").setParameter("tag", tag).setCacheable(true));
	}




	public List<Notification> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByCreated");

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

	public List<Notification> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByCreated");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByModified");

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

	public List<Notification> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByModified");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByNamePaged(String name, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByName");

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

	public List<Notification> findByNamePaged(String name, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByName");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByDescription");

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

	public List<Notification> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByDescription");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByContentPaged(String content, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByContent");

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

	public List<Notification> findByContentPaged(String content, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByContent");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByExpiresPaged(Date expires, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByExpires");

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

		return list(q.setParameter("expires", expires).setCacheable(true));
	}

	public List<Notification> findByExpiresPaged(Date expires, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByExpires");
        q.setParameter("expires", expires).setCacheable(true);

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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByStatus");

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

	public List<Notification> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByStatus");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByTypePaged(String type, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByType");

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

	public List<Notification> findByTypePaged(String type, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByType");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByVisible");

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

	public List<Notification> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByVisible");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findBySyncPaged(Boolean sync, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findBySync");

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

		return list(q.setParameter("sync", sync).setCacheable(true));
	}

	public List<Notification> findBySyncPaged(Boolean sync, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findBySync");
        q.setParameter("sync", sync).setCacheable(true);

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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByStatusDescriptionPaged(String statusDescription, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByStatusDescription");

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

	public List<Notification> findByStatusDescriptionPaged(String statusDescription, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByStatusDescription");
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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findBySenderPaged(String sender, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findBySender");

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

		return list(q.setParameter("sender", sender).setCacheable(true));
	}

	public List<Notification> findBySenderPaged(String sender, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findBySender");
        q.setParameter("sender", sender).setCacheable(true);

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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByScopePaged(String scope, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByScope");

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

		return list(q.setParameter("scope", scope).setCacheable(true));
	}

	public List<Notification> findByScopePaged(String scope, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByScope");
        q.setParameter("scope", scope).setCacheable(true);

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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByReplyAddressPaged(String replyAddress, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByReplyAddress");

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

		return list(q.setParameter("replyAddress", replyAddress).setCacheable(true));
	}

	public List<Notification> findByReplyAddressPaged(String replyAddress, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByReplyAddress");
        q.setParameter("replyAddress", replyAddress).setCacheable(true);

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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Notification> findByJobIdPaged(String jobId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByJobId");

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

		return list(q.setParameter("jobId", jobId).setCacheable(true));
	}

	public List<Notification> findByJobIdPaged(String jobId, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByJobId");
        q.setParameter("jobId", jobId).setCacheable(true);

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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Notification> findByWhenSendPaged(Date whenSend, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Notification.findByWhenSend");

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

		return list(q.setParameter("whenSend", whenSend).setCacheable(true));
	}

	public List<Notification> findByWhenSendPaged(Date whenSend, Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findByWhenSend");
        q.setParameter("whenSend", whenSend).setCacheable(true);

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

        List<Notification> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }





	public List<Notification> findAll() {
		return list(namedQuery("Notification.findAll").setCacheable(true));
	}

	public List<Notification> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Notification.findAll");
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

        List<Notification> res = list(q);

        return res;
    }

	public List<Notification> findAllPaged(Integer page, Integer limit, PagedResults<Notification> results) {
        org.hibernate.query.Query q = namedQuery("Notification.findAll");
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

        List<Notification> res = list(q);
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

	public void delete(Notification transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Notification transientInstance) {
        //log.debug("editing Notification instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Notification transientInstance) {
        //log.debug("creating Notification instance");
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
    
    public Notification findById( Integer id) {
        //log.debug("getting Notification instance with id: " + id);
        try {
            Notification instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

