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
 * Home object for domain model class Fellowship.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractFellowshipDAO extends AbstractDAO<Fellowship> {

    private final Logger log = new LoggerContext().getLogger(AbstractFellowshipDAO.class);

	public AbstractFellowshipDAO(SessionFactory session) {
		super(session);
	}

	public Fellowship findByUid(String uid) {
		return uniqueResult(namedQuery("Fellowship.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Fellowship> findByName(String name) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByName");
		return list(q.setParameter("name", name).setCacheable(true));
	}
	public List<Fellowship> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<Fellowship> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Fellowship> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Fellowship> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Fellowship> findByAccountid(int accountid) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public List<Fellowship> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}




	public List<Fellowship> findByNamePaged(String name, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Fellowship.findByName");

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

	public List<Fellowship> findByNamePaged(String name, Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByName");
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

        List<Fellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Fellowship> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Fellowship.findByDescription");

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

	public List<Fellowship> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByDescription");
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

        List<Fellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Fellowship> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Fellowship.findByCreated");

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

	public List<Fellowship> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByCreated");
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

        List<Fellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Fellowship> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Fellowship.findByModified");

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

	public List<Fellowship> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByModified");
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

        List<Fellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Fellowship> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Fellowship.findByVisible");

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

	public List<Fellowship> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByVisible");
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

        List<Fellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Fellowship> findByAccountidPaged(int accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Fellowship.findByAccountid");

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

	public List<Fellowship> findByAccountidPaged(int accountid, Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByAccountid");
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

        List<Fellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Fellowship> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Fellowship.findByStatus");

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

	public List<Fellowship> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findByStatus");
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

        List<Fellowship> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Fellowship> findAll() {
		return list(namedQuery("Fellowship.findAll").setCacheable(true));
	}

	public List<Fellowship> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findAll");
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

        List<Fellowship> res = list(q);

        return res;
    }

	public List<Fellowship> findAllPaged(Integer page, Integer limit, PagedResults<Fellowship> results) {
        org.hibernate.query.Query q = namedQuery("Fellowship.findAll");
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

        List<Fellowship> res = list(q);
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

	public void delete(Fellowship transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Fellowship transientInstance) {
        //log.debug("editing Fellowship instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Fellowship transientInstance) {
        //log.debug("creating Fellowship instance");
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
    
    public Fellowship findById( Integer id) {
        //log.debug("getting Fellowship instance with id: " + id);
        try {
            Fellowship instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

