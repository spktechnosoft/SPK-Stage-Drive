package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractCatalogHasBrandDAO;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class CatalogHasBrand.
 * @author Hibernate Tools
 */
 
@Singleton
public class CatalogHasBrandDAO extends AbstractCatalogHasBrandDAO {

    private final Logger log = new LoggerContext().getLogger(CatalogHasBrandDAO.class);

	@Inject
	public CatalogHasBrandDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

}

