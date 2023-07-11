package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractRidePassengerDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Ride;
import com.stagedriving.modules.commons.ds.entities.RidePassenger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class RidePassenger.
 * @author Hibernate Tools
 */
 
@Singleton
public class RidePassengerDAO extends AbstractRidePassengerDAO {

    private final Logger log = new LoggerContext().getLogger(RidePassengerDAO.class);

	@Inject
	public RidePassengerDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<RidePassenger> findByAccountAndRide(Account account, Ride ride) {

		List<RidePassenger> ridePassengers = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT rp FROM RidePassenger rp ");

		queryBuilder.append(" WHERE rp.accountId = :accountId ");
		queryBuilder.append(" AND rp.ride = :ride ");
		queryBuilder.append(" AND rp.status != 'deleted' ");
		queryBuilder.append(" AND rp.status != 'pending' ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("accountId", account.getId());
			query.setParameter("ride", ride);
			query.setCacheable(true);
			ridePassengers = query.list();
		} catch (Exception ex) {
			log.error("Oops", ex);
		} finally {
			return ridePassengers;
		}
	}

	public List<RidePassenger> findByRide(Ride ride, boolean isAdmin) {

		List<RidePassenger> ridePassengers = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT rp FROM RidePassenger rp ");

		queryBuilder.append(" WHERE rp.ride = :ride ");
		queryBuilder.append(" AND rp.status != 'deleted' ");
		if (!isAdmin) {
			queryBuilder.append(" AND rp.status != 'pending' ");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("ride", ride);
			query.setCacheable(true);
			ridePassengers = query.list();
		} catch (Exception ex) {
			log.error("Oops", ex);
		} finally {
			return ridePassengers;
		}
	}

	public List<RidePassenger> findByRideAndAccountIds(Ride ride, String accountIds) {

		List<RidePassenger> ridePassengers = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT rp FROM RidePassenger rp ");

		queryBuilder.append(" WHERE rp.ride = :ride ");
		queryBuilder.append(" AND rp.status != 'deleted' ");
		queryBuilder.append(" AND rp.status != 'pending' ");
		queryBuilder.append(" AND rp.accountId IN ("+accountIds+") ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("ride", ride);
//			query.setParameter("accountIds", accountIds);
			query.setCacheable(true);
			ridePassengers = query.list();
		} catch (Exception ex) {
			log.error("Oops", ex);
		} finally {
			return ridePassengers;
		}
	}
}

