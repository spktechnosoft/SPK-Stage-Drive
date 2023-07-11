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
 * Home object for domain model class AccountBilling.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountBillingDAO extends AbstractDAO<AccountBilling> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountBillingDAO.class);

	public AbstractAccountBillingDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountBilling> findByAccount(Account account) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByAccount");
		return list(q.setParameter("account", account).setCacheable(true));
	}
	public AccountBilling findByUid(String uid) {
		return uniqueResult(namedQuery("AccountBilling.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountBilling> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountBilling> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountBilling> findByProvider(String provider) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByProvider");
		return list(q.setParameter("provider", provider).setCacheable(true));
	}
	public List<AccountBilling> findByCoordinate(String coordinate) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByCoordinate");
		return list(q.setParameter("coordinate", coordinate).setCacheable(true));
	}
	public List<AccountBilling> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<AccountBilling> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<AccountBilling> findByIban(String iban) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByIban");
		return list(q.setParameter("iban", iban).setCacheable(true));
	}
	public List<AccountBilling> findBySwift(String swift) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findBySwift");
		return list(q.setParameter("swift", swift).setCacheable(true));
	}
	public List<AccountBilling> findByNote(String note) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByNote");
		return list(q.setParameter("note", note).setCacheable(true));
	}


	public List<AccountBilling> findByAccountPaged(Account account, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByAccount");

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

	public List<AccountBilling> findByAccountPaged(Account account, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByAccount");
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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountBilling> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByCreated");

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

	public List<AccountBilling> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByCreated");
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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByModified");

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

	public List<AccountBilling> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByModified");
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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findByProviderPaged(String provider, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByProvider");

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

		return list(q.setParameter("provider", provider).setCacheable(true));
	}

	public List<AccountBilling> findByProviderPaged(String provider, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByProvider");
        q.setParameter("provider", provider).setCacheable(true);

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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findByCoordinatePaged(String coordinate, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByCoordinate");

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

		return list(q.setParameter("coordinate", coordinate).setCacheable(true));
	}

	public List<AccountBilling> findByCoordinatePaged(String coordinate, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByCoordinate");
        q.setParameter("coordinate", coordinate).setCacheable(true);

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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByStatus");

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

	public List<AccountBilling> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByStatus");
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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByVisible");

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

	public List<AccountBilling> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByVisible");
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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findByIbanPaged(String iban, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByIban");

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

		return list(q.setParameter("iban", iban).setCacheable(true));
	}

	public List<AccountBilling> findByIbanPaged(String iban, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByIban");
        q.setParameter("iban", iban).setCacheable(true);

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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findBySwiftPaged(String swift, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findBySwift");

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

		return list(q.setParameter("swift", swift).setCacheable(true));
	}

	public List<AccountBilling> findBySwiftPaged(String swift, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findBySwift");
        q.setParameter("swift", swift).setCacheable(true);

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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountBilling> findByNotePaged(String note, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountBilling.findByNote");

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

		return list(q.setParameter("note", note).setCacheable(true));
	}

	public List<AccountBilling> findByNotePaged(String note, Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findByNote");
        q.setParameter("note", note).setCacheable(true);

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

        List<AccountBilling> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountBilling> findAll() {
		return list(namedQuery("AccountBilling.findAll").setCacheable(true));
	}

	public List<AccountBilling> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findAll");
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

        List<AccountBilling> res = list(q);

        return res;
    }

	public List<AccountBilling> findAllPaged(Integer page, Integer limit, PagedResults<AccountBilling> results) {
        org.hibernate.query.Query q = namedQuery("AccountBilling.findAll");
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

        List<AccountBilling> res = list(q);
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

	public void delete(AccountBilling transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountBilling transientInstance) {
        //log.debug("editing AccountBilling instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountBilling transientInstance) {
        //log.debug("creating AccountBilling instance");
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
    
    public AccountBilling findById( AccountBillingId id) {
        //log.debug("getting AccountBilling instance with id: " + id);
        try {
            AccountBilling instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

