package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.common.base.CaseFormat;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.commons.v1.resources.RideDTO;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.model.AbstractRideDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.Ride;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

/**
 * Home object for domain model class Account.
 *
 * @author Hibernate Tools
 */

@Singleton
public class RideDAO extends AbstractRideDAO {

    private final Logger log = new LoggerContext().getLogger(RideDAO.class);

    @Inject
    public RideDAO(/*@Named("datastore1") */SessionFactory session) {
        super(session);
    }

    public int countByEventAvailable(Event event) {

        int size = 0;

        StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM Ride acc ");

        queryBuilder.append(" WHERE (acc.toEventId = :event ) ");/*OR acc.fromEventId = :event*/
        queryBuilder.append(" AND (acc.goingDepartureDate > :today) ");

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());
            query.setParameter("event", event.getUid());
            query.setParameter("today", new Date());

            query.setCacheable(true);

            ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
            scrollableResults.last();
            size = scrollableResults.getRowNumber() + 1;
            return size;
        } catch (Exception ex) {
            log.error("Oops", ex);
        } finally {
            return size;
        }
    }

    public List<Ride> findByFilter(String fromCoordinate, String toCoordinate,
                                   Double distance,
                                   String goingDepartureDate, String goingArrivalDate,
                                   String returnDepartureDate, String returnArrivalDate,
                                   String fromEventId,
                                   String toEventId,
                                   Boolean withTickets,
                                   Boolean withBookings,
                                   Boolean withFriends,
                                   Boolean hasReturn,
                                   Integer seats,
                                   Integer bookedSeats,
                                   String driverAccountId,
                                   Integer passengerAccountId,
                                   String friends,
                                   Double totalPrice,
                                   Double price,
//                                   Boolean onlyAvailable,
                                   Integer page,
                                   Integer limit,
                                   String sort,
                                   String order,
                                   String idLike,
                                   String fromEventIdLike,
                                   String toEventIdLike,
                                   PagedResults<RideDTO> results) {


        List<Ride> rides = null;

        String subquery = "";
        if (friends != null && !friends.equals("")) {
            subquery = ", (SELECT 1000-COUNT(rp.ride_id) FROM `ride_passenger` rp WHERE rp.`account_id` IN (" + friends + ") AND rp.ride_id = acc.id) AS friends";
        }

        StringBuilder queryBuilder = new StringBuilder("SELECT {acc.*}, (1000-acc.with_tickets) AS tickets, (1000-acc.with_bookings) AS bookings " + subquery + " FROM `ride` acc ");

        queryBuilder.append("WHERE (acc.status IS NULL OR acc.status <> 'deleted') ");
        if (withTickets != null) {
            queryBuilder.append("AND acc.with_tickets = :withTickets ");
        }
        if (withBookings != null) {
            queryBuilder.append("AND acc.with_bookings = :withBookings ");
        }
//        if (withFriends != null) {
//            queryBuilder.append("AND acc.withFriends = :withFriends ");
//        }
        if (hasReturn != null) {
            queryBuilder.append("AND acc.has_return = :hasReturn ");
        }
        if (fromEventId != null) {
            queryBuilder.append("AND acc.from_event_id = :fromEventId ");
        }
        if (toEventId != null) {
            queryBuilder.append("AND acc.to_event_id = :toEventId ");
        }
        if (seats != null) {
            queryBuilder.append("AND acc.seats >= :seats ");
        }
        if (price != null) {
            queryBuilder.append(" AND acc.price <= :price ");
        }
        if (totalPrice != null) {
            queryBuilder.append(" AND acc.total_price <= :totalPrice ");
        }
        if (goingDepartureDate != null && goingArrivalDate == null) {
            queryBuilder.append("AND DATE_FORMAT(acc.going_departure_date, \"%Y-%m-%dT%TZ\") >= :goingDepartureDate ");
        } else if (goingDepartureDate != null && goingArrivalDate != null) {
            queryBuilder.append("AND DATE_FORMAT(acc.going_departure_date, \"%Y-%m-%dT%TZ\") >= :goingDepartureDate ");
            queryBuilder.append("AND DATE_FORMAT(acc.going_arrival_date, \"%Y-%m-%dT%TZ\") >= :goingArrivalDate ");
        }
        if (returnDepartureDate != null && returnArrivalDate == null) {
            queryBuilder.append("AND DATE_FORMAT(acc.return_departure_date, \"%Y-%m-%dT%TZ\") >= :returnDepartureDate ");
        } else if (returnDepartureDate != null && returnArrivalDate != null) {
            queryBuilder.append("AND DATE_FORMAT(acc.return_departure_date, \"%Y-%m-%dT%TZ\") >= :returnDepartureDate ");
            queryBuilder.append("AND DATE_FORMAT(acc.return_arrival_date, \"%Y-%m-%dT%TZ\") >= :returnArrivalDate ");
        }
        if (driverAccountId != null) {
            queryBuilder.append("AND acc.accountid = :driverAccountId ");
        }
        if (passengerAccountId != null) {
            queryBuilder.append("AND :passengerAccountId IN (SELECT pp.account_id FROM ride_passenger pp WHERE pp.ride_id = acc.id) ");
        }
        if (fromCoordinate != null) {
//			DEGREES(ACOS(
//					COS(RADIANS(lat1)) *
//							COS(RADIANS(lat2)) *
//							COS(RADIANS(lon2) - RADIANS(lon1)) +
//							SIN(RADIANS(lat1)) * SIN(RADIANS(lat2))
//			));
//            queryBuilder.append(" AND (111.045*haversine(acc.fromLat, :fromLat, :fromLon, acc.fromLon, acc.fromLat, :fromLat) < :distance) ");
            queryBuilder.append(" AND (111.045*" +
                    "DEGREES(" +
                    "ACOS(" +
                    "COS(RADIANS(acc.from_lat)) * " +
                    "COS(RADIANS(:fromLat)) * " +
                    "COS(RADIANS(:fromLon) - RADIANS(acc.from_lon)) + " +
                    "SIN(RADIANS(acc.from_lat)) * SIN(RADIANS(:fromLat))" +
                    ")" +
                    ")" +
                    " < :distance) ");
        }
        if (toCoordinate != null) {
//			DEGREES(ACOS(
//					COS(RADIANS(lat1)) *
//							COS(RADIANS(lat2)) *
//							COS(RADIANS(lon2) - RADIANS(lon1)) +
//							SIN(RADIANS(lat1)) * SIN(RADIANS(lat2))
//			));
//            queryBuilder.append(" AND (111.045*haversine(acc.toLat, :toLat, :toLon, acc.toLon, acc.toLat, :toLat) < :distance) ");
            queryBuilder.append(" AND (111.045*" +
                    "DEGREES(" +
                    "ACOS(" +
                    "COS(RADIANS(acc.to_lat)) * " +
                    "COS(RADIANS(:toLat)) * " +
                    "COS(RADIANS(:toLon) - RADIANS(acc.to_lon)) + " +
                    "SIN(RADIANS(acc.to_lat)) * SIN(RADIANS(:toLat))" +
                    ")" +
                    ")" +
                    " < :distance) ");
        }

        if (idLike != null) {
            queryBuilder.append(" AND acc.uid LIKE :idLike ");
        }
        if (fromEventIdLike != null) {
            queryBuilder.append(" AND acc.from_event_id LIKE :fromEventIdLike ");
        }
        if (toEventIdLike != null) {
            queryBuilder.append(" AND acc.to_event_id LIKE :toEventIdLike ");
        }

//        if (onlyAvailable != null && onlyAvailable) {
//            queryBuilder.append(" AND date(acc.going_arrival_date) >= :goingArrivalDate ");
//        }

        if (sort != null) {
            sort = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sort);
            queryBuilder.append(" ORDER BY acc." + sort + " ");
            if (order != null) {
                queryBuilder.append(order);
            }
        } else {
            queryBuilder.append(" ORDER BY acc.going_departure_date,tickets,bookings");
            if (friends != null && !friends.equals("")) {
                queryBuilder.append(",friends");
            }
            queryBuilder.append(" ASC ");
        }

        try {
            Query query = currentSession().createNativeQuery(queryBuilder.toString()).addEntity("acc", Ride.class);
            if (withTickets != null) {
                query.setParameter("withTickets", withTickets);
            }
            if (withBookings != null) {
                query.setParameter("withBookings", withBookings);
            }
//            if (withFriends != null) {
//                query.setParameter("withFriends", withFriends);
//            }
            if (seats != null) {
                query.setParameter("seats", seats);
            }
            if (fromEventId != null) {
                query.setParameter("fromEventId", fromEventId);
            }
            if (toEventId != null) {
                query.setParameter("toEventId", toEventId);
            }
            if (hasReturn != null) {
                query.setParameter("hasReturn", hasReturn);
            }
            if (goingDepartureDate != null) {
                //Date startDate = DateUtils.stringToDate(goingDepartureDate);
                query.setParameter("goingDepartureDate", goingDepartureDate);
            }
            if (goingArrivalDate != null) {
//                Date endDate = DateUtils.stringToDate(goingArrivalDate);
                query.setParameter("goingArrivalDate", goingArrivalDate);
            }
            if (returnDepartureDate != null) {
//                Date startDate = DateUtils.stringToDate(returnDepartureDate);
                query.setParameter("returnDepartureDate", returnDepartureDate);
            }
            if (returnArrivalDate != null) {
//                Date endDate = DateUtils.stringToDate(returnArrivalDate);
                query.setParameter("returnArrivalDate", returnArrivalDate);
            }
            if (driverAccountId != null) {
                query.setParameter("driverAccountId", driverAccountId);
            }
            if (passengerAccountId != null) {
                query.setParameter("passengerAccountId", passengerAccountId);
            }
            if (fromCoordinate != null) {
                String[] coords = fromCoordinate.split(",");
                query.setParameter("fromLat", coords[0]);
                query.setParameter("fromLon", coords[1]);
                query.setParameter("distance", distance);
            }
            if (toCoordinate != null) {
                String[] coords = toCoordinate.split(",");
                query.setParameter("toLat", coords[0]);
                query.setParameter("toLon", coords[1]);
                query.setParameter("distance", distance);
            }
            if (price != null) {
                query.setParameter("price", price);
            }
            if (totalPrice != null) {
                query.setParameter("totalPrice", totalPrice);
            }
            if (idLike != null) {
                query.setParameter("idLike", "%"+idLike+"%");
            }
            if (fromEventIdLike != null) {
                query.setParameter("fromEventIdLike", "%"+fromEventIdLike+"%");
            }
            if (toEventIdLike != null) {
                query.setParameter("toEventIdLike", "%"+toEventIdLike+"%");
            }

            if (page == null) {
                page = 0;
            }
            if (limit == null) {
                limit = 50;
            }

            if (results != null) {
                fillResults(results, query, page, limit);
            }

            if (page != null && limit != null) {
                query.setFirstResult(page * limit);
                query.setMaxResults(limit);
            }

            query.setCacheable(true);
            rides = query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return rides;
        }
    }
}

