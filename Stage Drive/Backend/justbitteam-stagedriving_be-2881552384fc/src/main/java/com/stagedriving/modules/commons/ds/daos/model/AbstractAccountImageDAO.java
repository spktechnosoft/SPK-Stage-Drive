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
 * Home object for domain model class AccountImage.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountImageDAO extends AbstractDAO<AccountImage> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountImageDAO.class);

	public AbstractAccountImageDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountImage> findByAccount(Account account) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByAccount");
		return list(q.setParameter("account", account).setCacheable(true));
	}
	public AccountImage findByUid(String uid) {
		return uniqueResult(namedQuery("AccountImage.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountImage> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountImage> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountImage> findByTaxonomy(String taxonomy) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByTaxonomy");
		return list(q.setParameter("taxonomy", taxonomy).setCacheable(true));
	}
	public List<AccountImage> findByImage(String image) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByImage");
		return list(q.setParameter("image", image).setCacheable(true));
	}


	public List<AccountImage> findByAccountPaged(Account account, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountImage.findByAccount");

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

		return list(q.setParameter("account", account).setCacheable(true));
	}

	public List<AccountImage> findByAccountPaged(Account account, Integer page, Integer limit, PagedResults<AccountImage> results) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByAccount");
        q.setParameter("account", account).setCacheable(true);

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

        List<AccountImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountImage> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountImage.findByCreated");

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

	public List<AccountImage> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountImage> results) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByCreated");
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

        List<AccountImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountImage> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountImage.findByModified");

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

	public List<AccountImage> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountImage> results) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByModified");
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

        List<AccountImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountImage> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountImage.findByTaxonomy");

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

	public List<AccountImage> findByTaxonomyPaged(String taxonomy, Integer page, Integer limit, PagedResults<AccountImage> results) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByTaxonomy");
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

        List<AccountImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountImage> findByImagePaged(String image, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountImage.findByImage");

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

		return list(q.setParameter("image", image).setCacheable(true));
	}

	public List<AccountImage> findByImagePaged(String image, Integer page, Integer limit, PagedResults<AccountImage> results) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findByImage");
        q.setParameter("image", image).setCacheable(true);

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

        List<AccountImage> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountImage> findAll() {
		return list(namedQuery("AccountImage.findAll").setCacheable(true));
	}

	public List<AccountImage> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findAll");
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

        List<AccountImage> res = list(q);

        return res;
    }

	public List<AccountImage> findAllPaged(Integer page, Integer limit, PagedResults<AccountImage> results) {
        org.hibernate.query.Query q = namedQuery("AccountImage.findAll");
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

        List<AccountImage> res = list(q);
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

	public void delete(AccountImage transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountImage transientInstance) {
        //log.debug("editing AccountImage instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountImage transientInstance) {
        //log.debug("creating AccountImage instance");
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
    
    public AccountImage findById( Integer id) {
        //log.debug("getting AccountImage instance with id: " + id);
        try {
            AccountImage instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

