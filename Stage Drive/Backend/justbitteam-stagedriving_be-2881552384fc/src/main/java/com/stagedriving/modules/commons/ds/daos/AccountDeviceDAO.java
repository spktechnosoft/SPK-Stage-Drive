package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractAccountDeviceDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountDevice;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class AccountDevice.
 * @author Hibernate Tools
 */
 
@Singleton
public class AccountDeviceDAO extends AbstractAccountDeviceDAO {

    private final Logger log = new LoggerContext().getLogger(AccountDeviceDAO.class);

	@Inject
	public AccountDeviceDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<AccountDevice> findByAccountAndDeviceId(Account account, String deviceId, String os) {

		List<AccountDevice> accountDevices = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM AccountDevice acc ");

		queryBuilder.append(" WHERE acc.account = :account ");
		if (deviceId != null) {
			queryBuilder.append(" AND acc.deviceid = :deviceId ");
		}
		if (os != null) {
			queryBuilder.append(" AND acc.os = :os ");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("account", account);
			if (deviceId != null) {
				query.setParameter("deviceId", deviceId);
			}
			if (os != null) {
				query.setParameter("os", os);
			}
			query.setCacheable(true);
			accountDevices = query.list();
		} catch (Exception ex) {
			log.error("Oops", ex);
		} finally {
			return accountDevices;
		}
	}
}

