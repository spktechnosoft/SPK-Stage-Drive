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
 * Home object for domain model class Event.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractEventDAO extends AbstractDAO<Event> {

    private final Logger log = new LoggerContext().getLogger(AbstractEventDAO.class);

	public AbstractEventDAO(SessionFactory session) {
		super(session);
	}

	public Event findByUid(String uid) {
		return uniqueResult(namedQuery("Event.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Event> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Event.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Event> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Event.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Event> findByName(String name) {
        org.hibernate.query.Query q = namedQuery("Event.findByName");
		return list(q.setParameter("name", name).setCacheable(true));
	}
	public List<Event> findByDescription(String description) {
        org.hibernate.query.Query q = namedQuery("Event.findByDescription");
		return list(q.setParameter("description", description).setCacheable(true));
	}
	public List<Event> findByLatitude(Double latitude) {
        org.hibernate.query.Query q = namedQuery("Event.findByLatitude");
		return list(q.setParameter("latitude", latitude).setCacheable(true));
	}
	public List<Event> findByLongitude(Double longitude) {
        org.hibernate.query.Query q = namedQuery("Event.findByLongitude");
		return list(q.setParameter("longitude", longitude).setCacheable(true));
	}
	public List<Event> findByCapacity(Integer capacity) {
        org.hibernate.query.Query q = namedQuery("Event.findByCapacity");
		return list(q.setParameter("capacity", capacity).setCacheable(true));
	}
	public List<Event> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Event.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Event> findByCategory(String category) {
        org.hibernate.query.Query q = namedQuery("Event.findByCategory");
		return list(q.setParameter("category", category).setCacheable(true));
	}
	public List<Event> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Event.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Event> findByCode(String code) {
        org.hibernate.query.Query q = namedQuery("Event.findByCode");
		return list(q.setParameter("code", code).setCacheable(true));
	}
	public List<Event> findByLevel(String level) {
        org.hibernate.query.Query q = namedQuery("Event.findByLevel");
		return list(q.setParameter("level", level).setCacheable(true));
	}
	public List<Event> findByPiva(String piva) {
        org.hibernate.query.Query q = namedQuery("Event.findByPiva");
		return list(q.setParameter("piva", piva).setCacheable(true));
	}
	public List<Event> findByMobile(String mobile) {
        org.hibernate.query.Query q = namedQuery("Event.findByMobile");
		return list(q.setParameter("mobile", mobile).setCacheable(true));
	}
	public List<Event> findByPhone(String phone) {
        org.hibernate.query.Query q = namedQuery("Event.findByPhone");
		return list(q.setParameter("phone", phone).setCacheable(true));
	}
	public List<Event> findByCity(String city) {
        org.hibernate.query.Query q = namedQuery("Event.findByCity");
		return list(q.setParameter("city", city).setCacheable(true));
	}
	public List<Event> findByTown(String town) {
        org.hibernate.query.Query q = namedQuery("Event.findByTown");
		return list(q.setParameter("town", town).setCacheable(true));
	}
	public List<Event> findByAddress(String address) {
        org.hibernate.query.Query q = namedQuery("Event.findByAddress");
		return list(q.setParameter("address", address).setCacheable(true));
	}
	public List<Event> findByZipcode(String zipcode) {
        org.hibernate.query.Query q = namedQuery("Event.findByZipcode");
		return list(q.setParameter("zipcode", zipcode).setCacheable(true));
	}
	public List<Event> findByPass(String pass) {
        org.hibernate.query.Query q = namedQuery("Event.findByPass");
		return list(q.setParameter("pass", pass).setCacheable(true));
	}
	public List<Event> findByAccountid(int accountid) {
        org.hibernate.query.Query q = namedQuery("Event.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public List<Event> findByCountry(String country) {
        org.hibernate.query.Query q = namedQuery("Event.findByCountry");
		return list(q.setParameter("country", country).setCacheable(true));
	}
	public List<Event> findByRank(String rank) {
        org.hibernate.query.Query q = namedQuery("Event.findByRank");
		return list(q.setParameter("rank", rank).setCacheable(true));
	}
	public List<Event> findByCover(String cover) {
        org.hibernate.query.Query q = namedQuery("Event.findByCover");
		return list(q.setParameter("cover", cover).setCacheable(true));
	}
	public List<Event> findByAvatar(String avatar) {
        org.hibernate.query.Query q = namedQuery("Event.findByAvatar");
		return list(q.setParameter("avatar", avatar).setCacheable(true));
	}
	public List<Event> findByNcheckin(Integer ncheckin) {
        org.hibernate.query.Query q = namedQuery("Event.findByNcheckin");
		return list(q.setParameter("ncheckin", ncheckin).setCacheable(true));
	}
	public List<Event> findByNlike(Integer nlike) {
        org.hibernate.query.Query q = namedQuery("Event.findByNlike");
		return list(q.setParameter("nlike", nlike).setCacheable(true));
	}
	public List<Event> findByNride(Integer nride) {
        org.hibernate.query.Query q = namedQuery("Event.findByNride");
		return list(q.setParameter("nride", nride).setCacheable(true));
	}
	public List<Event> findByStart(Date start) {
        org.hibernate.query.Query q = namedQuery("Event.findByStart");
		return list(q.setParameter("start", start).setCacheable(true));
	}
	public List<Event> findByFinish(Date finish) {
        org.hibernate.query.Query q = namedQuery("Event.findByFinish");
		return list(q.setParameter("finish", finish).setCacheable(true));
	}
	public List<Event> findByNbooking(Integer nbooking) {
        org.hibernate.query.Query q = namedQuery("Event.findByNbooking");
		return list(q.setParameter("nbooking", nbooking).setCacheable(true));
	}
	public List<Event> findByNbookingticket(Integer nbookingticket) {
        org.hibernate.query.Query q = namedQuery("Event.findByNbookingticket");
		return list(q.setParameter("nbookingticket", nbookingticket).setCacheable(true));
	}
	public List<Event> findByNcomment(Integer ncomment) {
        org.hibernate.query.Query q = namedQuery("Event.findByNcomment");
		return list(q.setParameter("ncomment", ncomment).setCacheable(true));
	}
	public List<Event> findByParking(String parking) {
        org.hibernate.query.Query q = namedQuery("Event.findByParking");
		return list(q.setParameter("parking", parking).setCacheable(true));
	}
	public List<Event> findByOrganizer(String organizer) {
        org.hibernate.query.Query q = namedQuery("Event.findByOrganizer");
		return list(q.setParameter("organizer", organizer).setCacheable(true));
	}
	public List<Event> findByWebsite(String website) {
        org.hibernate.query.Query q = namedQuery("Event.findByWebsite");
		return list(q.setParameter("website", website).setCacheable(true));
	}




	public List<Event> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByCreated");

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

	public List<Event> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByCreated");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByModified");

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

	public List<Event> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByModified");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByNamePaged(String name, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByName");

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

	public List<Event> findByNamePaged(String name, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByName");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByDescriptionPaged(String description, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByDescription");

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

	public List<Event> findByDescriptionPaged(String description, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByDescription");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByLatitudePaged(Double latitude, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByLatitude");

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

		return list(q.setParameter("latitude", latitude).setCacheable(true));
	}

	public List<Event> findByLatitudePaged(Double latitude, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByLatitude");
        q.setParameter("latitude", latitude).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByLongitudePaged(Double longitude, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByLongitude");

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

		return list(q.setParameter("longitude", longitude).setCacheable(true));
	}

	public List<Event> findByLongitudePaged(Double longitude, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByLongitude");
        q.setParameter("longitude", longitude).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByCapacityPaged(Integer capacity, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByCapacity");

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

		return list(q.setParameter("capacity", capacity).setCacheable(true));
	}

	public List<Event> findByCapacityPaged(Integer capacity, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByCapacity");
        q.setParameter("capacity", capacity).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByStatus");

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

	public List<Event> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByStatus");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByCategoryPaged(String category, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByCategory");

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

		return list(q.setParameter("category", category).setCacheable(true));
	}

	public List<Event> findByCategoryPaged(String category, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByCategory");
        q.setParameter("category", category).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByVisible");

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

	public List<Event> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByVisible");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByCodePaged(String code, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByCode");

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

		return list(q.setParameter("code", code).setCacheable(true));
	}

	public List<Event> findByCodePaged(String code, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByCode");
        q.setParameter("code", code).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByLevelPaged(String level, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByLevel");

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

		return list(q.setParameter("level", level).setCacheable(true));
	}

	public List<Event> findByLevelPaged(String level, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByLevel");
        q.setParameter("level", level).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByPivaPaged(String piva, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByPiva");

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

		return list(q.setParameter("piva", piva).setCacheable(true));
	}

	public List<Event> findByPivaPaged(String piva, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByPiva");
        q.setParameter("piva", piva).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByMobilePaged(String mobile, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByMobile");

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

		return list(q.setParameter("mobile", mobile).setCacheable(true));
	}

	public List<Event> findByMobilePaged(String mobile, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByMobile");
        q.setParameter("mobile", mobile).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByPhonePaged(String phone, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByPhone");

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

		return list(q.setParameter("phone", phone).setCacheable(true));
	}

	public List<Event> findByPhonePaged(String phone, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByPhone");
        q.setParameter("phone", phone).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByCityPaged(String city, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByCity");

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

		return list(q.setParameter("city", city).setCacheable(true));
	}

	public List<Event> findByCityPaged(String city, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByCity");
        q.setParameter("city", city).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByTownPaged(String town, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByTown");

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

		return list(q.setParameter("town", town).setCacheable(true));
	}

	public List<Event> findByTownPaged(String town, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByTown");
        q.setParameter("town", town).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByAddressPaged(String address, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByAddress");

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

		return list(q.setParameter("address", address).setCacheable(true));
	}

	public List<Event> findByAddressPaged(String address, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByAddress");
        q.setParameter("address", address).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByZipcodePaged(String zipcode, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByZipcode");

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

		return list(q.setParameter("zipcode", zipcode).setCacheable(true));
	}

	public List<Event> findByZipcodePaged(String zipcode, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByZipcode");
        q.setParameter("zipcode", zipcode).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByPassPaged(String pass, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByPass");

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

		return list(q.setParameter("pass", pass).setCacheable(true));
	}

	public List<Event> findByPassPaged(String pass, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByPass");
        q.setParameter("pass", pass).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByAccountidPaged(int accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByAccountid");

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

	public List<Event> findByAccountidPaged(int accountid, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByAccountid");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByCountryPaged(String country, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByCountry");

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

		return list(q.setParameter("country", country).setCacheable(true));
	}

	public List<Event> findByCountryPaged(String country, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByCountry");
        q.setParameter("country", country).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByRankPaged(String rank, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByRank");

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

		return list(q.setParameter("rank", rank).setCacheable(true));
	}

	public List<Event> findByRankPaged(String rank, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByRank");
        q.setParameter("rank", rank).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByCoverPaged(String cover, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByCover");

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

		return list(q.setParameter("cover", cover).setCacheable(true));
	}

	public List<Event> findByCoverPaged(String cover, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByCover");
        q.setParameter("cover", cover).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByAvatarPaged(String avatar, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByAvatar");

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

		return list(q.setParameter("avatar", avatar).setCacheable(true));
	}

	public List<Event> findByAvatarPaged(String avatar, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByAvatar");
        q.setParameter("avatar", avatar).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByNcheckinPaged(Integer ncheckin, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByNcheckin");

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

		return list(q.setParameter("ncheckin", ncheckin).setCacheable(true));
	}

	public List<Event> findByNcheckinPaged(Integer ncheckin, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByNcheckin");
        q.setParameter("ncheckin", ncheckin).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByNlikePaged(Integer nlike, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByNlike");

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

	public List<Event> findByNlikePaged(Integer nlike, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByNlike");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByNridePaged(Integer nride, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByNride");

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

		return list(q.setParameter("nride", nride).setCacheable(true));
	}

	public List<Event> findByNridePaged(Integer nride, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByNride");
        q.setParameter("nride", nride).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByStartPaged(Date start, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByStart");

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

		return list(q.setParameter("start", start).setCacheable(true));
	}

	public List<Event> findByStartPaged(Date start, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByStart");
        q.setParameter("start", start).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByFinishPaged(Date finish, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByFinish");

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

		return list(q.setParameter("finish", finish).setCacheable(true));
	}

	public List<Event> findByFinishPaged(Date finish, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByFinish");
        q.setParameter("finish", finish).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByNbookingPaged(Integer nbooking, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByNbooking");

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

		return list(q.setParameter("nbooking", nbooking).setCacheable(true));
	}

	public List<Event> findByNbookingPaged(Integer nbooking, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByNbooking");
        q.setParameter("nbooking", nbooking).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByNbookingticketPaged(Integer nbookingticket, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByNbookingticket");

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

		return list(q.setParameter("nbookingticket", nbookingticket).setCacheable(true));
	}

	public List<Event> findByNbookingticketPaged(Integer nbookingticket, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByNbookingticket");
        q.setParameter("nbookingticket", nbookingticket).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByNcommentPaged(Integer ncomment, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByNcomment");

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

	public List<Event> findByNcommentPaged(Integer ncomment, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByNcomment");
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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByParkingPaged(String parking, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByParking");

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

		return list(q.setParameter("parking", parking).setCacheable(true));
	}

	public List<Event> findByParkingPaged(String parking, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByParking");
        q.setParameter("parking", parking).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByOrganizerPaged(String organizer, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByOrganizer");

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

		return list(q.setParameter("organizer", organizer).setCacheable(true));
	}

	public List<Event> findByOrganizerPaged(String organizer, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByOrganizer");
        q.setParameter("organizer", organizer).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Event> findByWebsitePaged(String website, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Event.findByWebsite");

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

		return list(q.setParameter("website", website).setCacheable(true));
	}

	public List<Event> findByWebsitePaged(String website, Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findByWebsite");
        q.setParameter("website", website).setCacheable(true);

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

        List<Event> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Event> findAll() {
		return list(namedQuery("Event.findAll").setCacheable(true));
	}

	public List<Event> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Event.findAll");
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

        List<Event> res = list(q);

        return res;
    }

	public List<Event> findAllPaged(Integer page, Integer limit, PagedResults<Event> results) {
        org.hibernate.query.Query q = namedQuery("Event.findAll");
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

        List<Event> res = list(q);
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

	public void delete(Event transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Event transientInstance) {
        //log.debug("editing Event instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Event transientInstance) {
        //log.debug("creating Event instance");
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
    
    public Event findById( Integer id) {
        //log.debug("getting Event instance with id: " + id);
        try {
            Event instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

