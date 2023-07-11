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
 * Home object for domain model class Item.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractItemDAO extends AbstractDAO<Item> {

    private final Logger log = new LoggerContext().getLogger(AbstractItemDAO.class);

	public AbstractItemDAO(SessionFactory session) {
		super(session);
	}

	public Item findByUid(String uid) {
		return uniqueResult(namedQuery("Item.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Item> findByName(String name) {
        org.hibernate.query.Query q = namedQuery("Item.findByName");
		return list(q.setParameter("name", name).setCacheable(true));
	}
	public List<Item> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("Item.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<Item> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Item.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Item> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Item.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Item> findByTag(String tag) {
        org.hibernate.query.Query q = namedQuery("Item.findByTag");
		return list(q.setParameter("tag", tag).setCacheable(true));
	}
	public List<Item> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Item.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Item> findByColor(String color) {
        org.hibernate.query.Query q = namedQuery("Item.findByColor");
		return list(q.setParameter("color", color).setCacheable(true));
	}
	public List<Item> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Item.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Item> findByMale(Boolean male) {
        org.hibernate.query.Query q = namedQuery("Item.findByMale");
		return list(q.setParameter("male", male).setCacheable(true));
	}
	public List<Item> findByFemale(Boolean female) {
        org.hibernate.query.Query q = namedQuery("Item.findByFemale");
		return list(q.setParameter("female", female).setCacheable(true));
	}
	public List<Item> findByChildren(Boolean children) {
        org.hibernate.query.Query q = namedQuery("Item.findByChildren");
		return list(q.setParameter("children", children).setCacheable(true));
	}
	public List<Item> findByPicture(String picture) {
        org.hibernate.query.Query q = namedQuery("Item.findByPicture");
		return list(q.setParameter("picture", picture).setCacheable(true));
	}
	public List<Item> findByOutfit(Boolean outfit) {
        org.hibernate.query.Query q = namedQuery("Item.findByOutfit");
		return list(q.setParameter("outfit", outfit).setCacheable(true));
	}
	public List<Item> findByPublished(Boolean published) {
        org.hibernate.query.Query q = namedQuery("Item.findByPublished");
		return list(q.setParameter("published", published).setCacheable(true));
	}
	public List<Item> findByNlike(Integer nlike) {
        org.hibernate.query.Query q = namedQuery("Item.findByNlike");
		return list(q.setParameter("nlike", nlike).setCacheable(true));
	}
	public List<Item> findByNcomment(Integer ncomment) {
        org.hibernate.query.Query q = namedQuery("Item.findByNcomment");
		return list(q.setParameter("ncomment", ncomment).setCacheable(true));
	}




	public List<Item> findByNamePaged(String name, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByName");

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

	public List<Item> findByNamePaged(String name, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByName");
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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByDescription");

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

	public List<Item> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByDescription");
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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByCreated");

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

	public List<Item> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByCreated");
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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByModified");

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

	public List<Item> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByModified");
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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByTagPaged(String tag, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByTag");

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

	public List<Item> findByTagPaged(String tag, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByTag");
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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByVisible");

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

	public List<Item> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByVisible");
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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByColorPaged(String color, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByColor");

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

		return list(q.setParameter("color", color).setCacheable(true));
	}

	public List<Item> findByColorPaged(String color, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByColor");
        q.setParameter("color", color).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByStatus");

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

	public List<Item> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByStatus");
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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByMalePaged(Boolean male, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByMale");

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

		return list(q.setParameter("male", male).setCacheable(true));
	}

	public List<Item> findByMalePaged(Boolean male, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByMale");
        q.setParameter("male", male).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByFemalePaged(Boolean female, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByFemale");

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

		return list(q.setParameter("female", female).setCacheable(true));
	}

	public List<Item> findByFemalePaged(Boolean female, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByFemale");
        q.setParameter("female", female).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByChildrenPaged(Boolean children, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByChildren");

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

		return list(q.setParameter("children", children).setCacheable(true));
	}

	public List<Item> findByChildrenPaged(Boolean children, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByChildren");
        q.setParameter("children", children).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByPicturePaged(String picture, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByPicture");

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

		return list(q.setParameter("picture", picture).setCacheable(true));
	}

	public List<Item> findByPicturePaged(String picture, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByPicture");
        q.setParameter("picture", picture).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByOutfitPaged(Boolean outfit, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByOutfit");

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

		return list(q.setParameter("outfit", outfit).setCacheable(true));
	}

	public List<Item> findByOutfitPaged(Boolean outfit, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByOutfit");
        q.setParameter("outfit", outfit).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByPublishedPaged(Boolean published, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByPublished");

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

		return list(q.setParameter("published", published).setCacheable(true));
	}

	public List<Item> findByPublishedPaged(Boolean published, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByPublished");
        q.setParameter("published", published).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByNlikePaged(Integer nlike, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByNlike");

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

		return list(q.setParameter("nlike", nlike).setCacheable(true));
	}

	public List<Item> findByNlikePaged(Integer nlike, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByNlike");
        q.setParameter("nlike", nlike).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Item> findByNcommentPaged(Integer ncomment, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Item.findByNcomment");

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

		return list(q.setParameter("ncomment", ncomment).setCacheable(true));
	}

	public List<Item> findByNcommentPaged(Integer ncomment, Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findByNcomment");
        q.setParameter("ncomment", ncomment).setCacheable(true);

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

        List<Item> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Item> findAll() {
		return list(namedQuery("Item.findAll").setCacheable(true));
	}

	public List<Item> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Item.findAll");
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

        List<Item> res = list(q);

        return res;
    }

	public List<Item> findAllPaged(Integer page, Integer limit, PagedResults<Item> results) {
        org.hibernate.query.Query q = namedQuery("Item.findAll");
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

        List<Item> res = list(q);
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

	public void delete(Item transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Item transientInstance) {
        //log.debug("editing Item instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Item transientInstance) {
        //log.debug("creating Item instance");
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
    
    public Item findById( Integer id) {
        //log.debug("getting Item instance with id: " + id);
        try {
            Item instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

