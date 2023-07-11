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
 * Home object for domain model class AccountVehicle.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountVehicleDAO extends AbstractDAO<AccountVehicle> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountVehicleDAO.class);

	public AbstractAccountVehicleDAO(SessionFactory session) {
		super(session);
	}

	public List<AccountVehicle> findByAccount(Account account) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByAccount");
		return list(q.setParameter("account", account).setCacheable(true));
	}
	public AccountVehicle findByUid(String uid) {
		return uniqueResult(namedQuery("AccountVehicle.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<AccountVehicle> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<AccountVehicle> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<AccountVehicle> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<AccountVehicle> findByManufacturer(String manufacturer) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByManufacturer");
		return list(q.setParameter("manufacturer", manufacturer).setCacheable(true));
	}
	public List<AccountVehicle> findByName(String name) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByName");
		return list(q.setParameter("name", name).setCacheable(true));
	}
	public List<AccountVehicle> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<AccountVehicle> findByFeatures(String features) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByFeatures");
		return list(q.setParameter("features", features).setCacheable(true));
	}


	public List<AccountVehicle> findByAccountPaged(Account account, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByAccount");

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

	public List<AccountVehicle> findByAccountPaged(Account account, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByAccount");
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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountVehicle> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByCreated");

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

	public List<AccountVehicle> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByCreated");
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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountVehicle> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByModified");

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

	public List<AccountVehicle> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByModified");
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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountVehicle> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByStatus");

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

	public List<AccountVehicle> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByStatus");
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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountVehicle> findByManufacturerPaged(String manufacturer, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByManufacturer");

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

		return list(q.setParameter("manufacturer", manufacturer).setCacheable(true));
	}

	public List<AccountVehicle> findByManufacturerPaged(String manufacturer, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByManufacturer");
        q.setParameter("manufacturer", manufacturer).setCacheable(true);

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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountVehicle> findByNamePaged(String name, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByName");

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

	public List<AccountVehicle> findByNamePaged(String name, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByName");
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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountVehicle> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByDescription");

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

	public List<AccountVehicle> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByDescription");
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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<AccountVehicle> findByFeaturesPaged(String features, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("AccountVehicle.findByFeatures");

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

		return list(q.setParameter("features", features).setCacheable(true));
	}

	public List<AccountVehicle> findByFeaturesPaged(String features, Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findByFeatures");
        q.setParameter("features", features).setCacheable(true);

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

        List<AccountVehicle> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<AccountVehicle> findAll() {
		return list(namedQuery("AccountVehicle.findAll").setCacheable(true));
	}

	public List<AccountVehicle> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findAll");
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

        List<AccountVehicle> res = list(q);

        return res;
    }

	public List<AccountVehicle> findAllPaged(Integer page, Integer limit, PagedResults<AccountVehicle> results) {
        org.hibernate.query.Query q = namedQuery("AccountVehicle.findAll");
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

        List<AccountVehicle> res = list(q);
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

	public void delete(AccountVehicle transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(AccountVehicle transientInstance) {
        //log.debug("editing AccountVehicle instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(AccountVehicle transientInstance) {
        //log.debug("creating AccountVehicle instance");
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
    
    public AccountVehicle findById( Integer id) {
        //log.debug("getting AccountVehicle instance with id: " + id);
        try {
            AccountVehicle instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

