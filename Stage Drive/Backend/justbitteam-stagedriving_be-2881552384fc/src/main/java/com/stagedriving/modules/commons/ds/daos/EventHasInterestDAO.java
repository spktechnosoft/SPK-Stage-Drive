package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractEventHasInterestDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasInterest;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class Booking.
 * @author Hibernate Tools
 */
 
@Singleton
public class EventHasInterestDAO extends AbstractEventHasInterestDAO {

    private final Logger log = new LoggerContext().getLogger(EventHasInterestDAO.class);

	@Inject
	public EventHasInterestDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public int countByEventAndMetric(Event event, boolean withBookingTicket, boolean withBooking, boolean withLike, boolean withComment, boolean withRide) {

		int size = 0;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasInterest acc ");

		queryBuilder.append(" WHERE acc.event = :event ");

		if (withBookingTicket) {
			queryBuilder.append(" AND acc.bookingTicketId IS NOT NULL ");
		}
		if (withBooking) {
			queryBuilder.append(" AND acc.bookingId IS NOT NULL ");
		}
		if (withLike) {
			queryBuilder.append(" AND acc.actionLikeId IS NOT NULL ");
		}
		if (withComment) {
			queryBuilder.append(" AND acc.actionCommentId != NULL ");
		}
		if (withRide) {
			queryBuilder.append(" AND acc.actionRideId IS NOT NULL ");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);

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

	public List<EventHasInterest> findByEventAndMetric(Event event, boolean withBookingTicket, boolean withBooking, boolean withLike, boolean withComment, boolean withRide) {

		List<EventHasInterest> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasInterest acc ");

		queryBuilder.append(" WHERE acc.event = :event ");

		if (withBookingTicket) {
			queryBuilder.append(" AND acc.bookingTicketId IS NOT NULL ");
		}
		if (withBooking) {
			queryBuilder.append(" AND acc.bookingId IS NOT NULL ");
		}
		if (withLike) {
			queryBuilder.append(" AND acc.actionLikeId IS NOT NULL ");
		}
		if (withComment) {
			queryBuilder.append(" AND acc.actionCommentId IS NOT NULL ");
		}
		if (withRide) {
			queryBuilder.append(" AND acc.rideId IS NOT NULL ");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);

			query.setCacheable(true);

			items = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

	public List<EventHasInterest> findByEventAndAccount(Event event, int accountId) {

		List<EventHasInterest> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasInterest acc ");

		queryBuilder.append(" WHERE acc.accountId = :accountId AND acc.event = :event ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);
			query.setParameter("accountId", accountId);

			query.setCacheable(true);
			items = (List<EventHasInterest>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

	public List<EventHasInterest> findWithTicketByEventAndAccount(Event event, int accountId) {

		List<EventHasInterest> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasInterest acc ");

		queryBuilder.append(" WHERE acc.accountId = :accountId AND acc.event = :event" +
				" AND acc.bookingTicketId IS NOT NULL ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);
			query.setParameter("accountId", accountId);

			query.setCacheable(true);
			items = (List<EventHasInterest>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

	public List<EventHasInterest> findByFilters(int page, int limit, Event event, Integer accountId) {

		List<EventHasInterest> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasInterest acc ");

		queryBuilder.append(" WHERE 1=1 ");
		if (accountId != null) {
			queryBuilder.append(" AND acc.accountId = :accountId ");
		}
		if (event != null) {
			queryBuilder.append(" AND acc.event = :event ");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			if (event != null) {
				query.setParameter("event", event);
			}
			if (accountId != null) {
				query.setParameter("accountId", accountId);
			}

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);
			items = (List<EventHasInterest>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

}

