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
 * Home object for domain model class Account.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractAccountDAO extends AbstractDAO<Account> {

    private final Logger log = new LoggerContext().getLogger(AbstractAccountDAO.class);

	public AbstractAccountDAO(SessionFactory session) {
		super(session);
	}

	public Account findByUid(String uid) {
		return uniqueResult(namedQuery("Account.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Account> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Account.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Account> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Account.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Account> findByFirstname(String firstname) {
        org.hibernate.query.Query q = namedQuery("Account.findByFirstname");
		return list(q.setParameter("firstname", firstname).setCacheable(true));
	}
	public List<Account> findByMiddlename(String middlename) {
        org.hibernate.query.Query q = namedQuery("Account.findByMiddlename");
		return list(q.setParameter("middlename", middlename).setCacheable(true));
	}
	public List<Account> findByLastname(String lastname) {
        org.hibernate.query.Query q = namedQuery("Account.findByLastname");
		return list(q.setParameter("lastname", lastname).setCacheable(true));
	}
	public Account findByToken(String token) {
		return uniqueResult(namedQuery("Account.findByToken").setParameter("token", token).setCacheable(true));
	}
	public Account findByEmail(String email) {
		return uniqueResult(namedQuery("Account.findByEmail").setParameter("email", email).setCacheable(true));
	}
	public List<Account> findByAddress(String address) {
        org.hibernate.query.Query q = namedQuery("Account.findByAddress");
		return list(q.setParameter("address", address).setCacheable(true));
	}
	public List<Account> findByCountry(String country) {
        org.hibernate.query.Query q = namedQuery("Account.findByCountry");
		return list(q.setParameter("country", country).setCacheable(true));
	}
	public List<Account> findByTown(String town) {
        org.hibernate.query.Query q = namedQuery("Account.findByTown");
		return list(q.setParameter("town", town).setCacheable(true));
	}
	public List<Account> findByCity(String city) {
        org.hibernate.query.Query q = namedQuery("Account.findByCity");
		return list(q.setParameter("city", city).setCacheable(true));
	}
	public List<Account> findByZipcode(String zipcode) {
        org.hibernate.query.Query q = namedQuery("Account.findByZipcode");
		return list(q.setParameter("zipcode", zipcode).setCacheable(true));
	}
	public List<Account> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Account.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Account> findByGender(String gender) {
        org.hibernate.query.Query q = namedQuery("Account.findByGender");
		return list(q.setParameter("gender", gender).setCacheable(true));
	}
	public List<Account> findByBirthday(Date birthday) {
        org.hibernate.query.Query q = namedQuery("Account.findByBirthday");
		return list(q.setParameter("birthday", birthday).setCacheable(true));
	}
	public List<Account> findByTelephone(String telephone) {
        org.hibernate.query.Query q = namedQuery("Account.findByTelephone");
		return list(q.setParameter("telephone", telephone).setCacheable(true));
	}
	public List<Account> findByMobile(String mobile) {
        org.hibernate.query.Query q = namedQuery("Account.findByMobile");
		return list(q.setParameter("mobile", mobile).setCacheable(true));
	}
	public List<Account> findByPec(String pec) {
        org.hibernate.query.Query q = namedQuery("Account.findByPec");
		return list(q.setParameter("pec", pec).setCacheable(true));
	}
	public List<Account> findByNote(String note) {
        org.hibernate.query.Query q = namedQuery("Account.findByNote");
		return list(q.setParameter("note", note).setCacheable(true));
	}
	public List<Account> findByPassword(String password) {
        org.hibernate.query.Query q = namedQuery("Account.findByPassword");
		return list(q.setParameter("password", password).setCacheable(true));
	}
	public List<Account> findByExpires(Date expires) {
        org.hibernate.query.Query q = namedQuery("Account.findByExpires");
		return list(q.setParameter("expires", expires).setCacheable(true));
	}
	public List<Account> findByRole(String role) {
        org.hibernate.query.Query q = namedQuery("Account.findByRole");
		return list(q.setParameter("role", role).setCacheable(true));
	}
	public List<Account> findByUsername(String username) {
        org.hibernate.query.Query q = namedQuery("Account.findByUsername");
		return list(q.setParameter("username", username).setCacheable(true));
	}
	public List<Account> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Account.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Account> findByActive(Boolean active) {
        org.hibernate.query.Query q = namedQuery("Account.findByActive");
		return list(q.setParameter("active", active).setCacheable(true));
	}
	public List<Account> findByRating(Double rating) {
        org.hibernate.query.Query q = namedQuery("Account.findByRating");
		return list(q.setParameter("rating", rating).setCacheable(true));
	}
	public List<Account> findByFavStyle(String favStyle) {
        org.hibernate.query.Query q = namedQuery("Account.findByFavStyle");
		return list(q.setParameter("favStyle", favStyle).setCacheable(true));
	}
	public List<Account> findByFavCategory(String favCategory) {
        org.hibernate.query.Query q = namedQuery("Account.findByFavCategory");
		return list(q.setParameter("favCategory", favCategory).setCacheable(true));
	}
	public List<Account> findByCompanyVatId(String companyVatId) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyVatId");
		return list(q.setParameter("companyVatId", companyVatId).setCacheable(true));
	}
	public List<Account> findByCompanyName(String companyName) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyName");
		return list(q.setParameter("companyName", companyName).setCacheable(true));
	}
	public List<Account> findByCompanyAddress(String companyAddress) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyAddress");
		return list(q.setParameter("companyAddress", companyAddress).setCacheable(true));
	}
	public List<Account> findByCompanyZipcode(String companyZipcode) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyZipcode");
		return list(q.setParameter("companyZipcode", companyZipcode).setCacheable(true));
	}
	public List<Account> findByCompanyCountry(String companyCountry) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyCountry");
		return list(q.setParameter("companyCountry", companyCountry).setCacheable(true));
	}
	public List<Account> findByCompanyCity(String companyCity) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyCity");
		return list(q.setParameter("companyCity", companyCity).setCacheable(true));
	}
	public List<Account> findByCompanyRef(String companyRef) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyRef");
		return list(q.setParameter("companyRef", companyRef).setCacheable(true));
	}




	public List<Account> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCreated");

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

	public List<Account> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCreated");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByModified");

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

	public List<Account> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByModified");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByFirstnamePaged(String firstname, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByFirstname");

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

		return list(q.setParameter("firstname", firstname).setCacheable(true));
	}

	public List<Account> findByFirstnamePaged(String firstname, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByFirstname");
        q.setParameter("firstname", firstname).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByMiddlenamePaged(String middlename, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByMiddlename");

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

		return list(q.setParameter("middlename", middlename).setCacheable(true));
	}

	public List<Account> findByMiddlenamePaged(String middlename, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByMiddlename");
        q.setParameter("middlename", middlename).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByLastnamePaged(String lastname, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByLastname");

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

		return list(q.setParameter("lastname", lastname).setCacheable(true));
	}

	public List<Account> findByLastnamePaged(String lastname, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByLastname");
        q.setParameter("lastname", lastname).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }





	public List<Account> findByAddressPaged(String address, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByAddress");

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

	public List<Account> findByAddressPaged(String address, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByAddress");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCountryPaged(String country, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCountry");

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

	public List<Account> findByCountryPaged(String country, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCountry");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByTownPaged(String town, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByTown");

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

	public List<Account> findByTownPaged(String town, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByTown");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCityPaged(String city, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCity");

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

	public List<Account> findByCityPaged(String city, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCity");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByZipcodePaged(String zipcode, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByZipcode");

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

	public List<Account> findByZipcodePaged(String zipcode, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByZipcode");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByStatus");

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

	public List<Account> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByStatus");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByGenderPaged(String gender, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByGender");

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

		return list(q.setParameter("gender", gender).setCacheable(true));
	}

	public List<Account> findByGenderPaged(String gender, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByGender");
        q.setParameter("gender", gender).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByBirthdayPaged(Date birthday, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByBirthday");

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

		return list(q.setParameter("birthday", birthday).setCacheable(true));
	}

	public List<Account> findByBirthdayPaged(Date birthday, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByBirthday");
        q.setParameter("birthday", birthday).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByTelephonePaged(String telephone, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByTelephone");

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

		return list(q.setParameter("telephone", telephone).setCacheable(true));
	}

	public List<Account> findByTelephonePaged(String telephone, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByTelephone");
        q.setParameter("telephone", telephone).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByMobilePaged(String mobile, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByMobile");

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

	public List<Account> findByMobilePaged(String mobile, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByMobile");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByPecPaged(String pec, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByPec");

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

		return list(q.setParameter("pec", pec).setCacheable(true));
	}

	public List<Account> findByPecPaged(String pec, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByPec");
        q.setParameter("pec", pec).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByNotePaged(String note, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByNote");

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

	public List<Account> findByNotePaged(String note, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByNote");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByPasswordPaged(String password, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByPassword");

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

		return list(q.setParameter("password", password).setCacheable(true));
	}

	public List<Account> findByPasswordPaged(String password, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByPassword");
        q.setParameter("password", password).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByExpiresPaged(Date expires, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByExpires");

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

	public List<Account> findByExpiresPaged(Date expires, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByExpires");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByRolePaged(String role, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByRole");

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

		return list(q.setParameter("role", role).setCacheable(true));
	}

	public List<Account> findByRolePaged(String role, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByRole");
        q.setParameter("role", role).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByUsernamePaged(String username, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByUsername");

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

		return list(q.setParameter("username", username).setCacheable(true));
	}

	public List<Account> findByUsernamePaged(String username, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByUsername");
        q.setParameter("username", username).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByVisible");

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

	public List<Account> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByVisible");
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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByActivePaged(Boolean active, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByActive");

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

		return list(q.setParameter("active", active).setCacheable(true));
	}

	public List<Account> findByActivePaged(Boolean active, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByActive");
        q.setParameter("active", active).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByRatingPaged(Double rating, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByRating");

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

		return list(q.setParameter("rating", rating).setCacheable(true));
	}

	public List<Account> findByRatingPaged(Double rating, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByRating");
        q.setParameter("rating", rating).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByFavStylePaged(String favStyle, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByFavStyle");

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

		return list(q.setParameter("favStyle", favStyle).setCacheable(true));
	}

	public List<Account> findByFavStylePaged(String favStyle, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByFavStyle");
        q.setParameter("favStyle", favStyle).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByFavCategoryPaged(String favCategory, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByFavCategory");

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

		return list(q.setParameter("favCategory", favCategory).setCacheable(true));
	}

	public List<Account> findByFavCategoryPaged(String favCategory, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByFavCategory");
        q.setParameter("favCategory", favCategory).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCompanyVatIdPaged(String companyVatId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCompanyVatId");

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

		return list(q.setParameter("companyVatId", companyVatId).setCacheable(true));
	}

	public List<Account> findByCompanyVatIdPaged(String companyVatId, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyVatId");
        q.setParameter("companyVatId", companyVatId).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCompanyNamePaged(String companyName, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCompanyName");

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

		return list(q.setParameter("companyName", companyName).setCacheable(true));
	}

	public List<Account> findByCompanyNamePaged(String companyName, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyName");
        q.setParameter("companyName", companyName).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCompanyAddressPaged(String companyAddress, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCompanyAddress");

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

		return list(q.setParameter("companyAddress", companyAddress).setCacheable(true));
	}

	public List<Account> findByCompanyAddressPaged(String companyAddress, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyAddress");
        q.setParameter("companyAddress", companyAddress).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCompanyZipcodePaged(String companyZipcode, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCompanyZipcode");

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

		return list(q.setParameter("companyZipcode", companyZipcode).setCacheable(true));
	}

	public List<Account> findByCompanyZipcodePaged(String companyZipcode, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyZipcode");
        q.setParameter("companyZipcode", companyZipcode).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCompanyCountryPaged(String companyCountry, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCompanyCountry");

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

		return list(q.setParameter("companyCountry", companyCountry).setCacheable(true));
	}

	public List<Account> findByCompanyCountryPaged(String companyCountry, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyCountry");
        q.setParameter("companyCountry", companyCountry).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCompanyCityPaged(String companyCity, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCompanyCity");

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

		return list(q.setParameter("companyCity", companyCity).setCacheable(true));
	}

	public List<Account> findByCompanyCityPaged(String companyCity, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyCity");
        q.setParameter("companyCity", companyCity).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Account> findByCompanyRefPaged(String companyRef, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Account.findByCompanyRef");

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

		return list(q.setParameter("companyRef", companyRef).setCacheable(true));
	}

	public List<Account> findByCompanyRefPaged(String companyRef, Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findByCompanyRef");
        q.setParameter("companyRef", companyRef).setCacheable(true);

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

        List<Account> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Account> findAll() {
		return list(namedQuery("Account.findAll").setCacheable(true));
	}

	public List<Account> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Account.findAll");
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

        List<Account> res = list(q);

        return res;
    }

	public List<Account> findAllPaged(Integer page, Integer limit, PagedResults<Account> results) {
        org.hibernate.query.Query q = namedQuery("Account.findAll");
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

        List<Account> res = list(q);
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

	public void delete(Account transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Account transientInstance) {
        //log.debug("editing Account instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Account transientInstance) {
        //log.debug("creating Account instance");
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
    
    public Account findById( Integer id) {
        //log.debug("getting Account instance with id: " + id);
        try {
            Account instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

