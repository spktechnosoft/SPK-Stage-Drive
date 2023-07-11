package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.v1.resources.EventDTO;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.model.AbstractEventDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Brand;
import com.stagedriving.modules.commons.ds.entities.Event;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Home object for domain model class Event.
 * @author Hibernate Tools
 */
 
@Singleton
public class EventDAO extends AbstractEventDAO {

    private final Logger log = new LoggerContext().getLogger(EventDAO.class);

	@Inject
	public EventDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<Event> findByHaversine(Account me, Double latitude, Double longitude, int distanceType, int page, int limit, String filter) {

		List<Event> stores = new ArrayList<Event>();

		StringBuilder queryBuilder = new StringBuilder();
		String haversineFormula = "( _distanceType * acos( cos( radians(_lat) ) * cos( radians( c.userByUserIdTo.latitude ) ) * cos( radians( c.userByUserIdTo.longitude ) - radians(_lng) ) + sin( radians(_lat) ) * sin( radians( c.userByUserIdTo.latitude ) ) ) )";
		haversineFormula = haversineFormula.replaceAll("_distanceType", "" + distanceType);
		haversineFormula = haversineFormula.replaceAll("_lat", "" + latitude);
		haversineFormula = haversineFormula.replaceAll("_lng", "" + longitude);

		// Tornano quelli che io non ho bloccato
		queryBuilder.append("SELECT c FROM UserHasUser c ");
		queryBuilder.append(" WHERE c.userByUserIdFrom = :from ");
		queryBuilder.append(" AND c.userByUserIdTo.lastTime IS NOT NULL ");
		queryBuilder.append(" AND c.userByUserIdTo.latitude IS NOT NULL ");
		queryBuilder.append(" AND c.userByUserIdTo.longitude IS NOT NULL ");
		queryBuilder.append(" AND c.status = 'accepted' ");
		queryBuilder.append(" AND c.status IN (SELECT c1.status FROM UserHasUser c1 WHERE c1.userByUserIdTo = :from AND c1.userByUserIdFrom = c.userByUserIdTo) ");
		if (filter != null) {
			filter = "%" + filter.toLowerCase() + "%";
			queryBuilder.append("AND (LOWER(c.userByUserIdTo.firstName) LIKE :filter ");
			queryBuilder.append("OR LOWER(c.userByUserIdTo.lastName) LIKE :filter) ");
		}
		queryBuilder.append(" ORDER BY ");
		queryBuilder.append(haversineFormula);

		System.err.println(queryBuilder);

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			if (filter != null) {
				query.setParameter("filter", filter);
			}
			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);
			query.setParameter("from", me);

			stores = (List<Event>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			stores = null;
		} finally {
			return stores;
		}
	}

	public List<Event> findByDistanceRadius(Double latitude, Double longitude, int distanceType, int distanceForRadius, int page, int limit) {
		List<Event> stores = new ArrayList<Event>();
		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append(" SELECT *,_distanceType*2*ASIN(SQRT(POWER(SIN((_lat-abs(dest.latitude))*pi()/180/2 ), 2) + COS(_lat*pi()/180)*COS(abs(dest.latitude) *pi()/180)*POWER(SIN((_lng-dest.longitude)*pi()/180/2 ),2))) AS distance ");
		queryBuilder.append(" FROM store dest ");
		queryBuilder.append(" WHERE true = true ");
		queryBuilder.append(" AND dest.latitude IS NOT NULL ");
		queryBuilder.append(" AND dest.longitude IS NOT NULL ");
		queryBuilder.append(" having distance < _radius");
		queryBuilder.append(" ORDER BY distance");

		String queryString = queryBuilder.toString();
		queryString = queryString.replaceAll("_distanceType", "" + distanceType);
		queryString = queryString.replaceAll("_lat", "" + latitude);
		queryString = queryString.replaceAll("_lng", "" + longitude);
		queryString = queryString.replaceAll("_radius", "" + distanceForRadius);

		try {
			Query q = currentSession().createSQLQuery(queryString).addEntity(Event.class);
			q.setFirstResult(page * limit);
			q.setMaxResults(limit);
			q.setCacheable(true);

			stores = (List<Event>) q.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			stores = new ArrayList();
		} finally {
			return stores;
		}
	}

	public List<Event> findByFilters(int page, int limit, String search, List<Brand> brands) {

		List<Event> stores = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM Event acc ");

		queryBuilder.append(" WHERE true = true ");
		if (search != null) {
			queryBuilder.append(" AND ( LOWER ( acc.name ) LIKE :search ");
			queryBuilder.append(" OR LOWER ( acc.description ) LIKE :search ");

//            if (brands != null && !brands.isEmpty()) {
//                for (int k = 0; k < brands.size(); k++) {
//                    queryBuilder.append(" OR LOWER ( acc.brand.name ) LIKE :brand" + k + " ");
//                    k++;
//                }
//            }
			queryBuilder.append(") ");
//            if (brands != null && !brands.isEmpty() && search == null) {
//                if (brands.size() > 1) {
//                    queryBuilder.append(" AND ( LOWER ( acc.brand.name ) = :brand0 ");
//                    for (int k = 1; k < brands.size(); k++) {
//                        queryBuilder.append(" OR ( LOWER ( acc.brand.name ) = :brand" + k + " ");
//                        k++;
//                    }
//                    queryBuilder.append(" ) ");
//                } else {
//                    queryBuilder.append(" AND LOWER ( acc.brand.name ) = :brand0 ");
//                }
//            }
		}
		queryBuilder.append(" AND acc.visible = 1 ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			if (search != null) {
				query.setParameter("search", search.toLowerCase() + "%");
			}
//            if (brands != null && !brands.isEmpty()) {
//                for (int k = 0; k < brands.size(); k++) {
//                    query.setParameter("brand" + k, brands.get(k).toString().toLowerCase() + "%");
//                    k++;
//                }
//            }

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);
			stores = (List<Event>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return stores;
		}
	}


	public List<Event> findByFilters(boolean isAdmin, int page, int limit, String search, Date startDate,
									 Date finishDate, Double latitude, Double longitude, Double distance,
									 String categories, int rideId, String filter, Integer accountId,
									 String sort, String order, String idLike, String nameLike,
									 String categoryLike, PagedResults<EventDTO> results) {

		List<Event> events = null;

		String subquery = "";
		if (filter != null && filter.equals(StgdrvData.EventFilters.DISTANCE)) {
//			subquery = ", (111.045*haversine(ev.latitude, :lat, :lon, ev.longitude, ev.latitude, :lat)) AS distance ";
		}
		StringBuilder queryBuilder = new StringBuilder(" SELECT ev "+subquery+" FROM Event ev ");

		queryBuilder.append(" WHERE ev.visible = 1 ");

		if (isAdmin || StgdrvData.EventFilters.CREATED.equals(filter)) {
			queryBuilder.append(" AND ev.status != 'deleted' ");
		} else {
			queryBuilder.append(" AND ev.status = 'published' ");
		}

		if (search != null) {
			queryBuilder.append(" AND ( LOWER ( ev.name ) LIKE :search ");
			queryBuilder.append(" OR LOWER ( ev.description ) LIKE :search ");
			queryBuilder.append(") ");
		}

		if (startDate != null) {
			queryBuilder.append(" AND ( ev.start >= :startDate ) ");
		}

		if (finishDate != null) {
			queryBuilder.append(" AND ( ev.finish <= :finishDate ) ");
		}

		if (latitude != null && longitude != null) {
//			DEGREES(ACOS(
//					COS(RADIANS(lat1)) *
//							COS(RADIANS(lat2)) *
//							COS(RADIANS(lon2) - RADIANS(lon1)) +
//							SIN(RADIANS(lat1)) * SIN(RADIANS(lat2))
//			));
			queryBuilder.append(" AND (111.045*haversine(ev.latitude, :lat, :lon, ev.longitude, ev.latitude, :lat) < :distance) ");
		}

		if (categories != null) {
			queryBuilder.append(" AND ev.category IN :cats ");
		}

		if (!isAdmin && (startDate == null && finishDate == null) && (filter == null || !filter.equals(StgdrvData.EventFilters.CREATED))) {
			queryBuilder.append(" AND ( ev.finish > :today ) ");
		}

		if (idLike != null) {
			queryBuilder.append(" AND ev.uid LIKE :idLike ");
		}
		if (nameLike != null) {
			queryBuilder.append(" AND ev.name LIKE :nameLike ");
		}
		if (categoryLike != null) {
			queryBuilder.append(" AND ev.category LIKE :categoryLike ");
		}

		if (filter != null) {
			if (filter.equals(StgdrvData.EventFilters.TRENDING)) {
				queryBuilder.append(" ORDER BY ev.nlike DESC, ev.nride DESC ");
			} else if (filter.equals(StgdrvData.EventFilters.BIGGER)) {
				queryBuilder.append(" ORDER BY ev.nbooking DESC ");
			} else if (filter.equals(StgdrvData.EventFilters.PERSONAL)) {
				// TODO filter using category
			} else if (filter.equals(StgdrvData.EventFilters.DISTANCE)) {
//				queryBuilder.append(" ORDER BY distance DESC ");
			} else if (filter.equals(StgdrvData.EventFilters.CREATED)) {
				queryBuilder.append(" AND ev.accountid = :accountId ");
				queryBuilder.append(" ORDER BY ev.start DESC ");
			}
		} else {

			if (sort != null) {
				queryBuilder.append(" ORDER BY ev."+sort+" ");
				if (order != null) {
					queryBuilder.append(order);
				}
			} else {
				queryBuilder.append(" ORDER BY ev.start DESC ");
			}
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			if (search != null) {
				query.setParameter("search", "%" + search.toLowerCase() + "%");
			}
			if (startDate != null) {
				query.setParameter("startDate", startDate);
			}
			if (finishDate != null) {
				query.setParameter("finishDate", finishDate);
			}
			if (!isAdmin && (startDate == null && finishDate == null) && (filter == null || !filter.equals(StgdrvData.EventFilters.CREATED))) {
				query.setParameter("today", new Date());
			}
			if (latitude != null && longitude != null) {
				query.setParameter("lat", latitude);
				query.setParameter("lon", longitude);
				query.setParameter("distance", distance);
			}
			if (categories != null) {
				query.setParameter("cats", categories);
			}
			if (filter != null) {
				if (filter.equals(StgdrvData.EventFilters.CREATED) && accountId != null) {
					query.setParameter("accountId", accountId);
				}
			}
			if (idLike != null) {
				query.setParameter("idLike", "%"+idLike+"%");
			}
			if (nameLike != null) {
				query.setParameter("nameLike", "%"+nameLike+"%");
			}
			if (categoryLike != null) {
				query.setParameter("categoryLike", categoryLike);
			}

			if (results != null) {
				fillResults(results, query, page, limit);
			}

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);
			events = (List<Event>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return events;
		}
	}

}

