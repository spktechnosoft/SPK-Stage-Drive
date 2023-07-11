package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractEventHasCheckinDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasCheckin;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class EventHasCheckin.
 * @author Hibernate Tools
 */
 
@Singleton
public class EventHasCheckinDAO extends AbstractEventHasCheckinDAO {

    private final Logger log = new LoggerContext().getLogger(EventHasCheckinDAO.class);

	@Inject
	public EventHasCheckinDAO(/*@Named("dataevent1") */SessionFactory session) {
		super(session);
	}

	public List<EventHasCheckin> findUserCheckinsToEvent(Event event, Integer accountId) {

		List<EventHasCheckin> eventHasCheckins = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM EventHasCheckin acc ");

		queryBuilder.append(" WHERE acc.event = :event ");
		queryBuilder.append(" AND acc.checkin.accountid = :accountid ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);
			query.setParameter("accountid", accountId);
			query.setCacheable(true);
			eventHasCheckins = (List<EventHasCheckin>) query.list();
		} catch (Exception ex) {
		} finally {
			return eventHasCheckins;
		}
	}

}

