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
 * Home object for domain model class AccountDevice.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountDeviceDAO extends AbstractDAO<AccountDevice> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountDeviceDAO.class);

	public AbstractAccountDeviceDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountDevice> findByAccount(Account account) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByAccount");
		return list(q.setParameter("account", account).setCacheable(true));
	}
	public AccountDevice findByUid(String uid) {
		return uniqueResult(namedQuery("AccountDevice.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountDevice> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountDevice> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountDevice> findByOs(String os) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByOs");
		return list(q.setParameter("os", os).setCacheable(true));
	}
	public List<AccountDevice> findByDeviceid(String deviceid) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByDeviceid");
		return list(q.setParameter("deviceid", deviceid).setCacheable(true));
	}


	public List<AccountDevice> findByAccountPaged(Account account, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountDevice.findByAccount");

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

	public List<AccountDevice> findByAccountPaged(Account account, Integer page, Integer limit, PagedResults<AccountDevice> results) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByAccount");
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

        List<AccountDevice> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountDevice> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountDevice.findByCreated");

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

	public List<AccountDevice> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountDevice> results) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByCreated");
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

        List<AccountDevice> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountDevice> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountDevice.findByModified");

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

	public List<AccountDevice> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountDevice> results) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByModified");
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

        List<AccountDevice> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountDevice> findByOsPaged(String os, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountDevice.findByOs");

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

	public List<AccountDevice> findByOsPaged(String os, Integer page, Integer limit, PagedResults<AccountDevice> results) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByOs");
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

        List<AccountDevice> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountDevice> findByDeviceidPaged(String deviceid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountDevice.findByDeviceid");

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

		return list(q.setParameter("deviceid", deviceid).setCacheable(true));
	}

	public List<AccountDevice> findByDeviceidPaged(String deviceid, Integer page, Integer limit, PagedResults<AccountDevice> results) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findByDeviceid");
        q.setParameter("deviceid", deviceid).setCacheable(true);

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

        List<AccountDevice> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountDevice> findAll() {
		return list(namedQuery("AccountDevice.findAll").setCacheable(true));
	}

	public List<AccountDevice> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findAll");
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

        List<AccountDevice> res = list(q);

        return res;
    }

	public List<AccountDevice> findAllPaged(Integer page, Integer limit, PagedResults<AccountDevice> results) {
        org.hibernate.query.Query q = namedQuery("AccountDevice.findAll");
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

        List<AccountDevice> res = list(q);
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

	public void delete(AccountDevice transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountDevice transientInstance) {
        //log.debug("editing AccountDevice instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountDevice transientInstance) {
        //log.debug("creating AccountDevice instance");
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
    
    public AccountDevice findById( Integer id) {
        //log.debug("getting AccountDevice instance with id: " + id);
        try {
            AccountDevice instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

