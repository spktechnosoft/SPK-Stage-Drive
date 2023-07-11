package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractAccountConnectionDAO;
import com.stagedriving.modules.commons.ds.entities.AccountConnection;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AccountConnection.
 * @author Hibernate Tools
 */
 
@Singleton
public class AccountConnectionDAO extends AbstractAccountConnectionDAO {

    private final Logger log = new LoggerContext().getLogger(AccountConnectionDAO.class);

	@Inject
	public AccountConnectionDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public AccountConnection findByTokenByProvider(String token, String provider) {

		AccountConnection connection = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM AccountConnection acc ");

		queryBuilder.append(" WHERE acc.token = :token ");
		queryBuilder.append(" AND acc.provider = :provider ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("token", token);
			query.setParameter("provider", provider);
			query.setCacheable(true);
			connection = (AccountConnection) query.uniqueResult();
		} catch (Exception ex) {
		} finally {
			return connection;
		}
	}

	public AccountConnection findByConnectionIdByProvider(String connectionId, String provider) {

		AccountConnection connection = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM AccountConnection acc ");

		queryBuilder.append(" WHERE acc.id = :id ");
		queryBuilder.append(" AND acc.provider = :provider ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("id", connectionId);
			query.setParameter("provider", provider);
			query.setCacheable(true);
			connection = (AccountConnection) query.uniqueResult();
		} catch (Exception ex) {
		} finally {
			return connection;
		}
	}

}

