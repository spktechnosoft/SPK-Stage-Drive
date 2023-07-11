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
 * Home object for domain model class Ride.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractRideDAO extends AbstractDAO<Ride> {

    private final Logger log = new LoggerContext().getLogger(AbstractRideDAO.class);

	public AbstractRideDAO(SessionFactory session) {
		super(session);
	}

	public Ride findByUid(String uid) {
		return uniqueResult(namedQuery("Ride.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Ride> findByFromLat(double fromLat) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromLat");
		return list(q.setParameter("fromLat", fromLat).setCacheable(true));
	}
	public List<Ride> findByFromLon(double fromLon) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromLon");
		return list(q.setParameter("fromLon", fromLon).setCacheable(true));
	}
	public List<Ride> findByToLat(double toLat) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToLat");
		return list(q.setParameter("toLat", toLat).setCacheable(true));
	}
	public List<Ride> findByToLon(double toLon) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToLon");
		return list(q.setParameter("toLon", toLon).setCacheable(true));
	}
	public List<Ride> findByEventid(String eventid) {
        org.hibernate.query.Query q = namedQuery("Ride.findByEventid");
		return list(q.setParameter("eventid", eventid).setCacheable(true));
	}
	public List<Ride> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Ride.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Ride> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Ride.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Ride> findByAccountid(String accountid) {
        org.hibernate.query.Query q = namedQuery("Ride.findByAccountid");
		return list(q.setParameter("accountid", accountid).setCacheable(true));
	}
	public List<Ride> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Ride.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Ride> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Ride.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Ride> findByWithTickets(Boolean withTickets) {
        org.hibernate.query.Query q = namedQuery("Ride.findByWithTickets");
		return list(q.setParameter("withTickets", withTickets).setCacheable(true));
	}
	public List<Ride> findByWithBookings(Boolean withBookings) {
        org.hibernate.query.Query q = namedQuery("Ride.findByWithBookings");
		return list(q.setParameter("withBookings", withBookings).setCacheable(true));
	}
	public List<Ride> findByGoingDepartureDate(Date goingDepartureDate) {
        org.hibernate.query.Query q = namedQuery("Ride.findByGoingDepartureDate");
		return list(q.setParameter("goingDepartureDate", goingDepartureDate).setCacheable(true));
	}
	public List<Ride> findByGoingArrivalDate(Date goingArrivalDate) {
        org.hibernate.query.Query q = namedQuery("Ride.findByGoingArrivalDate");
		return list(q.setParameter("goingArrivalDate", goingArrivalDate).setCacheable(true));
	}
	public List<Ride> findByReturnDepartureDate(Date returnDepartureDate) {
        org.hibernate.query.Query q = namedQuery("Ride.findByReturnDepartureDate");
		return list(q.setParameter("returnDepartureDate", returnDepartureDate).setCacheable(true));
	}
	public List<Ride> findByReturnArrivalDate(Date returnArrivalDate) {
        org.hibernate.query.Query q = namedQuery("Ride.findByReturnArrivalDate");
		return list(q.setParameter("returnArrivalDate", returnArrivalDate).setCacheable(true));
	}
	public List<Ride> findByType(String type) {
        org.hibernate.query.Query q = namedQuery("Ride.findByType");
		return list(q.setParameter("type", type).setCacheable(true));
	}
	public List<Ride> findByHasReturn(Boolean hasReturn) {
        org.hibernate.query.Query q = namedQuery("Ride.findByHasReturn");
		return list(q.setParameter("hasReturn", hasReturn).setCacheable(true));
	}
	public List<Ride> findByTotalPrice(double totalPrice) {
        org.hibernate.query.Query q = namedQuery("Ride.findByTotalPrice");
		return list(q.setParameter("totalPrice", totalPrice).setCacheable(true));
	}
	public List<Ride> findByPrice(double price) {
        org.hibernate.query.Query q = namedQuery("Ride.findByPrice");
		return list(q.setParameter("price", price).setCacheable(true));
	}
	public List<Ride> findByFee(double fee) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFee");
		return list(q.setParameter("fee", fee).setCacheable(true));
	}
	public List<Ride> findByCurrency(String currency) {
        org.hibernate.query.Query q = namedQuery("Ride.findByCurrency");
		return list(q.setParameter("currency", currency).setCacheable(true));
	}
	public List<Ride> findBySeats(Integer seats) {
        org.hibernate.query.Query q = namedQuery("Ride.findBySeats");
		return list(q.setParameter("seats", seats).setCacheable(true));
	}
	public List<Ride> findByFromEventId(String fromEventId) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromEventId");
		return list(q.setParameter("fromEventId", fromEventId).setCacheable(true));
	}
	public List<Ride> findByToEventId(String toEventId) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToEventId");
		return list(q.setParameter("toEventId", toEventId).setCacheable(true));
	}
	public List<Ride> findByFromAddress(String fromAddress) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromAddress");
		return list(q.setParameter("fromAddress", fromAddress).setCacheable(true));
	}
	public List<Ride> findByToAddress(String toAddress) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToAddress");
		return list(q.setParameter("toAddress", toAddress).setCacheable(true));
	}




	public List<Ride> findByFromLatPaged(double fromLat, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByFromLat");

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

		return list(q.setParameter("fromLat", fromLat).setCacheable(true));
	}

	public List<Ride> findByFromLatPaged(double fromLat, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromLat");
        q.setParameter("fromLat", fromLat).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByFromLonPaged(double fromLon, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByFromLon");

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

		return list(q.setParameter("fromLon", fromLon).setCacheable(true));
	}

	public List<Ride> findByFromLonPaged(double fromLon, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromLon");
        q.setParameter("fromLon", fromLon).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByToLatPaged(double toLat, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByToLat");

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

		return list(q.setParameter("toLat", toLat).setCacheable(true));
	}

	public List<Ride> findByToLatPaged(double toLat, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToLat");
        q.setParameter("toLat", toLat).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByToLonPaged(double toLon, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByToLon");

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

		return list(q.setParameter("toLon", toLon).setCacheable(true));
	}

	public List<Ride> findByToLonPaged(double toLon, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToLon");
        q.setParameter("toLon", toLon).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByEventidPaged(String eventid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByEventid");

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

		return list(q.setParameter("eventid", eventid).setCacheable(true));
	}

	public List<Ride> findByEventidPaged(String eventid, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByEventid");
        q.setParameter("eventid", eventid).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByStatus");

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

	public List<Ride> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByStatus");
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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByVisible");

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

	public List<Ride> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByVisible");
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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByAccountidPaged(String accountid, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByAccountid");

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

	public List<Ride> findByAccountidPaged(String accountid, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByAccountid");
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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByCreated");

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

	public List<Ride> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByCreated");
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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByModified");

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

	public List<Ride> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByModified");
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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByWithTicketsPaged(Boolean withTickets, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByWithTickets");

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

		return list(q.setParameter("withTickets", withTickets).setCacheable(true));
	}

	public List<Ride> findByWithTicketsPaged(Boolean withTickets, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByWithTickets");
        q.setParameter("withTickets", withTickets).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByWithBookingsPaged(Boolean withBookings, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByWithBookings");

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

		return list(q.setParameter("withBookings", withBookings).setCacheable(true));
	}

	public List<Ride> findByWithBookingsPaged(Boolean withBookings, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByWithBookings");
        q.setParameter("withBookings", withBookings).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByGoingDepartureDatePaged(Date goingDepartureDate, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByGoingDepartureDate");

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

		return list(q.setParameter("goingDepartureDate", goingDepartureDate).setCacheable(true));
	}

	public List<Ride> findByGoingDepartureDatePaged(Date goingDepartureDate, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByGoingDepartureDate");
        q.setParameter("goingDepartureDate", goingDepartureDate).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByGoingArrivalDatePaged(Date goingArrivalDate, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByGoingArrivalDate");

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

		return list(q.setParameter("goingArrivalDate", goingArrivalDate).setCacheable(true));
	}

	public List<Ride> findByGoingArrivalDatePaged(Date goingArrivalDate, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByGoingArrivalDate");
        q.setParameter("goingArrivalDate", goingArrivalDate).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByReturnDepartureDatePaged(Date returnDepartureDate, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByReturnDepartureDate");

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

		return list(q.setParameter("returnDepartureDate", returnDepartureDate).setCacheable(true));
	}

	public List<Ride> findByReturnDepartureDatePaged(Date returnDepartureDate, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByReturnDepartureDate");
        q.setParameter("returnDepartureDate", returnDepartureDate).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByReturnArrivalDatePaged(Date returnArrivalDate, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByReturnArrivalDate");

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

		return list(q.setParameter("returnArrivalDate", returnArrivalDate).setCacheable(true));
	}

	public List<Ride> findByReturnArrivalDatePaged(Date returnArrivalDate, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByReturnArrivalDate");
        q.setParameter("returnArrivalDate", returnArrivalDate).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByTypePaged(String type, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByType");

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

		return list(q.setParameter("type", type).setCacheable(true));
	}

	public List<Ride> findByTypePaged(String type, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByType");
        q.setParameter("type", type).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByHasReturnPaged(Boolean hasReturn, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByHasReturn");

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

		return list(q.setParameter("hasReturn", hasReturn).setCacheable(true));
	}

	public List<Ride> findByHasReturnPaged(Boolean hasReturn, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByHasReturn");
        q.setParameter("hasReturn", hasReturn).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByTotalPricePaged(double totalPrice, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByTotalPrice");

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

		return list(q.setParameter("totalPrice", totalPrice).setCacheable(true));
	}

	public List<Ride> findByTotalPricePaged(double totalPrice, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByTotalPrice");
        q.setParameter("totalPrice", totalPrice).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByPricePaged(double price, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByPrice");

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

		return list(q.setParameter("price", price).setCacheable(true));
	}

	public List<Ride> findByPricePaged(double price, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByPrice");
        q.setParameter("price", price).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByFeePaged(double fee, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByFee");

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

		return list(q.setParameter("fee", fee).setCacheable(true));
	}

	public List<Ride> findByFeePaged(double fee, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFee");
        q.setParameter("fee", fee).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByCurrencyPaged(String currency, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByCurrency");

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

		return list(q.setParameter("currency", currency).setCacheable(true));
	}

	public List<Ride> findByCurrencyPaged(String currency, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByCurrency");
        q.setParameter("currency", currency).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findBySeatsPaged(Integer seats, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findBySeats");

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

		return list(q.setParameter("seats", seats).setCacheable(true));
	}

	public List<Ride> findBySeatsPaged(Integer seats, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findBySeats");
        q.setParameter("seats", seats).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByFromEventIdPaged(String fromEventId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByFromEventId");

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

		return list(q.setParameter("fromEventId", fromEventId).setCacheable(true));
	}

	public List<Ride> findByFromEventIdPaged(String fromEventId, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromEventId");
        q.setParameter("fromEventId", fromEventId).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByToEventIdPaged(String toEventId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByToEventId");

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

		return list(q.setParameter("toEventId", toEventId).setCacheable(true));
	}

	public List<Ride> findByToEventIdPaged(String toEventId, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToEventId");
        q.setParameter("toEventId", toEventId).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByFromAddressPaged(String fromAddress, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByFromAddress");

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

		return list(q.setParameter("fromAddress", fromAddress).setCacheable(true));
	}

	public List<Ride> findByFromAddressPaged(String fromAddress, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByFromAddress");
        q.setParameter("fromAddress", fromAddress).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Ride> findByToAddressPaged(String toAddress, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Ride.findByToAddress");

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

		return list(q.setParameter("toAddress", toAddress).setCacheable(true));
	}

	public List<Ride> findByToAddressPaged(String toAddress, Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findByToAddress");
        q.setParameter("toAddress", toAddress).setCacheable(true);

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

        List<Ride> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Ride> findAll() {
		return list(namedQuery("Ride.findAll").setCacheable(true));
	}

	public List<Ride> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Ride.findAll");
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

        List<Ride> res = list(q);

        return res;
    }

	public List<Ride> findAllPaged(Integer page, Integer limit, PagedResults<Ride> results) {
        org.hibernate.query.Query q = namedQuery("Ride.findAll");
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

        List<Ride> res = list(q);
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

	public void delete(Ride transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Ride transientInstance) {
        //log.debug("editing Ride instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Ride transientInstance) {
        //log.debug("creating Ride instance");
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
    
    public Ride findById( Integer id) {
        //log.debug("getting Ride instance with id: " + id);
        try {
            Ride instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

