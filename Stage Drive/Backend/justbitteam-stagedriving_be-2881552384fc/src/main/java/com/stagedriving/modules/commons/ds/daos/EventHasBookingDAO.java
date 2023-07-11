package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractEventHasBookingDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasBooking;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class EventHasBooking.
 * @author Hibernate Tools
 */
 
@Singleton
public class EventHasBookingDAO extends AbstractEventHasBookingDAO {

    private final Logger log = new LoggerContext().getLogger(EventHasBookingDAO.class);

	@Inject
	public EventHasBookingDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<EventHasBooking> findByEventAndAccount(Event event, int accountId) {

		List<EventHasBooking> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasBooking acc ");

		queryBuilder.append(" WHERE acc.accountid = :accountId AND acc.event = :event ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);
			query.setParameter("accountId", accountId);

			query.setCacheable(true);
			items = (List<EventHasBooking>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

	public List<EventHasBooking> findByFilters(boolean isAdmin, int page, int limit, Event event, Integer accountId) {

		List<EventHasBooking> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasBooking acc ");

		queryBuilder.append(" WHERE 1=1 ");
		if (accountId != null) {
			queryBuilder.append(" AND acc.accountid = :accountId ");
		}
		if (event != null) {
			queryBuilder.append(" AND acc.event = :event ");
		}

		queryBuilder.append(" AND acc.event.visible = 1 ");

		if (isAdmin) {
//			queryBuilder.append(" AND ev.status != 'deleted' ");
		} else {
			queryBuilder.append(" AND acc.event.status = 'published' ");
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
			items = (List<EventHasBooking>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

}

