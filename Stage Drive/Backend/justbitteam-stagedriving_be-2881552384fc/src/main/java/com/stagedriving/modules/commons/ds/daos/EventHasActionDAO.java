package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractEventHasActionDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasAction;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

/**
 * Home object for domain model class EventHasAction.
 * @author Hibernate Tools
 */
 
@Singleton
public class EventHasActionDAO extends AbstractEventHasActionDAO {

    private final Logger log = new LoggerContext().getLogger(EventHasActionDAO.class);

	@Inject
	public EventHasActionDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<EventHasAction> findByFilters(boolean isAdmin, int page, int limit, Event event, String search, Date createdDate, String taxonomy, Integer accountId) {

		List<EventHasAction> actions = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT ev FROM EventHasAction ev ");

		queryBuilder.append(" WHERE ev.visible = 1 ");
		if (search != null) {
			queryBuilder.append(" AND ( LOWER ( ev.content ) LIKE :search ) ");
		}

		if (event != null) {
			queryBuilder.append(" AND ( ev.event = :event) ");
		}

		if (createdDate != null) {
			queryBuilder.append(" AND ( ev.created >= :createdDate ) ");
		}

		if (taxonomy != null && !taxonomy.isEmpty()) {
			queryBuilder.append(" AND ( ev.taxonomy = :taxonomy ) ");
		}

		if (accountId != null) {
			queryBuilder.append(" AND ev.accountid = :accId ");
		}

		queryBuilder.append(" AND ev.event.visible = 1 ");

		if (isAdmin) {
//			queryBuilder.append(" AND ev.status != 'deleted' ");
		} else {
			queryBuilder.append(" AND ev.event.status = 'published' ");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			if (search != null) {
				query.setParameter("search", "%" + search.toLowerCase() + "%");
			}
			if (createdDate != null) {
				query.setParameter("createdDate", createdDate);
			}
			if (taxonomy != null) {
				query.setParameter("taxonomy", taxonomy);
			}
			if (event != null) {
				query.setParameter("event", event);
			}
			if (accountId != null) {
				query.setParameter("accId", accountId);
			}

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);
			actions = (List<EventHasAction>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return actions;
		}
	}

	public List<EventHasAction> findByEventAndAccount(Event event, int accountId, String taxonomy) {

		List<EventHasAction> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasAction acc ");

		queryBuilder.append(" WHERE acc.accountid = :accountId AND acc.event = :event AND acc.taxonomy = :taxonomy ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);
			query.setParameter("accountId", accountId);
			query.setParameter("taxonomy", taxonomy);

			query.setCacheable(true);
			items = (List<EventHasAction>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

	public int countByEvent(Event event, String taxonomy) {

		int size = 0;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM EventHasAction acc ");

		queryBuilder.append(" WHERE acc.event = :event ");
		queryBuilder.append(" AND acc.taxonomy = :taxonomy ");
		queryBuilder.append(" AND (acc.status = NULL OR acc.status != 'deleted') ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("event", event);
			query.setParameter("taxonomy", taxonomy);

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

}

