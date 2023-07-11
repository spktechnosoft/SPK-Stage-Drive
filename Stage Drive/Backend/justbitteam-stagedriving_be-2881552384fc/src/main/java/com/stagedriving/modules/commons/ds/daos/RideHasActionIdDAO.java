package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractRideHasActionIdDAO;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class RideHasActionId.
 * @author Hibernate Tools
 */
 
@Singleton
public class RideHasActionIdDAO extends AbstractRideHasActionIdDAO {

    private final Logger log = new LoggerContext().getLogger(RideHasActionIdDAO.class);

	@Inject
	public RideHasActionIdDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

}

