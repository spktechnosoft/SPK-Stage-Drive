package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractEventHasBuyerDAO;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasBuyer;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class EventHasBuyer.
 *
 * @author Hibernate Tools
 */

@Singleton
public class EventHasBuyerDAO extends AbstractEventHasBuyerDAO {

    private final Logger log = new LoggerContext().getLogger(EventHasBuyerDAO.class);

    @Inject
    public EventHasBuyerDAO(/*@Named("dataevent1") */SessionFactory session) {
        super(session);
    }

    public EventHasBuyer findByEventByAccountId(Event event, Integer accountId) {

        EventHasBuyer eventHasBuyer = null;

        StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM EventHasBuyer acc ");

        queryBuilder.append(" WHERE acc.event = :event ");
        queryBuilder.append(" AND acc.accountid = :accountid ");

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());
            query.setParameter("event", event);
            query.setParameter("accountid", accountId);
            query.setCacheable(true);
            eventHasBuyer = (EventHasBuyer) query.uniqueResult();
        } catch (Exception ex) {
        } finally {
            return eventHasBuyer;
        }
    }

}

