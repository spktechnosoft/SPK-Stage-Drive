package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractAccountGroupDAO;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AccountGroup.
 * @author Hibernate Tools
 */
 
@Singleton
public class AccountGroupDAO extends AbstractAccountGroupDAO {

    private final Logger log = new LoggerContext().getLogger(AccountGroupDAO.class);

	@Inject
	public AccountGroupDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

}

