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
 * Home object for domain model class AccountReview.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountReviewDAO extends AbstractDAO<AccountReview> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountReviewDAO.class);

	public AbstractAccountReviewDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountReview> findByAccountByAccountId(Account accountByAccountId) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByAccountByAccountId");
		return list(q.setParameter("accountByAccountId", accountByAccountId).setCacheable(true));
	}
	public List<AccountReview> findByAccountByAuthorAccountId(Account accountByAuthorAccountId) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByAccountByAuthorAccountId");
		return list(q.setParameter("accountByAuthorAccountId", accountByAuthorAccountId).setCacheable(true));
	}
	public AccountReview findByUid(String uid) {
		return uniqueResult(namedQuery("AccountReview.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountReview> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountReview> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountReview> findByTitle(String title) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByTitle");
		return list(q.setParameter("title", title).setCacheable(true));
	}
	public List<AccountReview> findByContent(String content) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByContent");
		return list(q.setParameter("content", content).setCacheable(true));
	}
	public List<AccountReview> findByStar(Double star) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByStar");
		return list(q.setParameter("star", star).setCacheable(true));
	}


	public List<AccountReview> findByAccountByAccountIdPaged(Account accountByAccountId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountReview.findByAccountByAccountId");

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

		return list(q.setParameter("accountByAccountId", accountByAccountId).setCacheable(true));
	}

	public List<AccountReview> findByAccountByAccountIdPaged(Account accountByAccountId, Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByAccountByAccountId");
        q.setParameter("accountByAccountId", accountByAccountId).setCacheable(true);

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

        List<AccountReview> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountReview> findByAccountByAuthorAccountIdPaged(Account accountByAuthorAccountId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountReview.findByAccountByAuthorAccountId");

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

		return list(q.setParameter("accountByAuthorAccountId", accountByAuthorAccountId).setCacheable(true));
	}

	public List<AccountReview> findByAccountByAuthorAccountIdPaged(Account accountByAuthorAccountId, Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByAccountByAuthorAccountId");
        q.setParameter("accountByAuthorAccountId", accountByAuthorAccountId).setCacheable(true);

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

        List<AccountReview> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountReview> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountReview.findByCreated");

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

	public List<AccountReview> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByCreated");
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

        List<AccountReview> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountReview> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountReview.findByModified");

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

	public List<AccountReview> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByModified");
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

        List<AccountReview> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountReview> findByTitlePaged(String title, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountReview.findByTitle");

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

		return list(q.setParameter("title", title).setCacheable(true));
	}

	public List<AccountReview> findByTitlePaged(String title, Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByTitle");
        q.setParameter("title", title).setCacheable(true);

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

        List<AccountReview> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountReview> findByContentPaged(String content, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountReview.findByContent");

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

	public List<AccountReview> findByContentPaged(String content, Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByContent");
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

        List<AccountReview> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountReview> findByStarPaged(Double star, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountReview.findByStar");

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

		return list(q.setParameter("star", star).setCacheable(true));
	}

	public List<AccountReview> findByStarPaged(Double star, Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findByStar");
        q.setParameter("star", star).setCacheable(true);

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

        List<AccountReview> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountReview> findAll() {
		return list(namedQuery("AccountReview.findAll").setCacheable(true));
	}

	public List<AccountReview> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findAll");
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

        List<AccountReview> res = list(q);

        return res;
    }

	public List<AccountReview> findAllPaged(Integer page, Integer limit, PagedResults<AccountReview> results) {
        org.hibernate.query.Query q = namedQuery("AccountReview.findAll");
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

        List<AccountReview> res = list(q);
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

	public void delete(AccountReview transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountReview transientInstance) {
        //log.debug("editing AccountReview instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountReview transientInstance) {
        //log.debug("creating AccountReview instance");
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
    
    public AccountReview findById( Integer id) {
        //log.debug("getting AccountReview instance with id: " + id);
        try {
            AccountReview instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

