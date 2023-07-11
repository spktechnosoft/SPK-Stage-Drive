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
 * Home object for domain model class Color.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractColorDAO extends AbstractDAO<Color> {

    private final Logger log = new LoggerContext().getLogger(AbstractColorDAO.class);

	public AbstractColorDAO(SessionFactory session) {
		super(session);
	}

	public Color findByUid(String uid) {
		return uniqueResult(namedQuery("Color.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public Color findByCode(String code) {
		return uniqueResult(namedQuery("Color.findByCode").setParameter("code", code).setCacheable(true));
	}
	public List<Color> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Color.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Color> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Color.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public Color findByName(String name) {
		return uniqueResult(namedQuery("Color.findByName").setParameter("name", name).setCacheable(true));
	}
	public List<Color> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("Color.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<Color> findByTag(String tag) {
        org.hibernate.query.Query q = namedQuery("Color.findByTag");
		return list(q.setParameter("tag", tag).setCacheable(true));
	}
	public List<Color> findByPosition(Integer position) {
        org.hibernate.query.Query q = namedQuery("Color.findByPosition");
		return list(q.setParameter("position", position).setCacheable(true));
	}






	public List<Color> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Color.findByCreated");

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

	public List<Color> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Color> results) {
        org.hibernate.query.Query q = namedQuery("Color.findByCreated");
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

        List<Color> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Color> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Color.findByModified");

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

	public List<Color> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Color> results) {
        org.hibernate.query.Query q = namedQuery("Color.findByModified");
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

        List<Color> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Color> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Color.findByDescription");

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

	public List<Color> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<Color> results) {
        org.hibernate.query.Query q = namedQuery("Color.findByDescription");
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

        List<Color> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Color> findByTagPaged(String tag, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Color.findByTag");

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

		return list(q.setParameter("tag", tag).setCacheable(true));
	}

	public List<Color> findByTagPaged(String tag, Integer page, Integer limit, PagedResults<Color> results) {
        org.hibernate.query.Query q = namedQuery("Color.findByTag");
        q.setParameter("tag", tag).setCacheable(true);

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

        List<Color> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Color> findByPositionPaged(Integer position, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Color.findByPosition");

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

		return list(q.setParameter("position", position).setCacheable(true));
	}

	public List<Color> findByPositionPaged(Integer position, Integer page, Integer limit, PagedResults<Color> results) {
        org.hibernate.query.Query q = namedQuery("Color.findByPosition");
        q.setParameter("position", position).setCacheable(true);

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

        List<Color> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Color> findAll() {
		return list(namedQuery("Color.findAll").setCacheable(true));
	}

	public List<Color> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Color.findAll");
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

        List<Color> res = list(q);

        return res;
    }

	public List<Color> findAllPaged(Integer page, Integer limit, PagedResults<Color> results) {
        org.hibernate.query.Query q = namedQuery("Color.findAll");
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

        List<Color> res = list(q);
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

	public void delete(Color transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Color transientInstance) {
        //log.debug("editing Color instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Color transientInstance) {
        //log.debug("creating Color instance");
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
    
    public Color findById( Integer id) {
        //log.debug("getting Color instance with id: " + id);
        try {
            Color instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

