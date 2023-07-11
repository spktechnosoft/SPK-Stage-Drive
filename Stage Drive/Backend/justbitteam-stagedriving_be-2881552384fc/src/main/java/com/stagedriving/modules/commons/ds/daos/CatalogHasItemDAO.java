package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.commons.v1.resources.ItemDTO;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.model.AbstractCatalogHasItemDAO;
import com.stagedriving.modules.commons.ds.entities.Catalog;
import com.stagedriving.modules.commons.ds.entities.CatalogHasItem;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class CatalogHasItem.
 * @author Hibernate Tools
 */
 
@Singleton
public class CatalogHasItemDAO extends AbstractCatalogHasItemDAO {

    private final Logger log = new LoggerContext().getLogger(CatalogHasItemDAO.class);

	@Inject
	public CatalogHasItemDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<CatalogHasItem> findByCatalog(Catalog catalog) {
		return list(namedQuery("CatalogHasItem.findByCatalog").setParameter("catalog", catalog).setCacheable(true));
	}

	public List<CatalogHasItem> findByCatalogByPageByLimit(Catalog catalog, int page, int limit, PagedResults<ItemDTO> results) {

		List<CatalogHasItem> catalogHasItems = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM CatalogHasItem acc ");

		queryBuilder.append(" WHERE acc.catalog = :catalog ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());

			query.setParameter("catalog", catalog);

			if (results != null) {
				fillResults(results, query, page, limit);
			}

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			catalogHasItems = (List<CatalogHasItem>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return catalogHasItems;
		}
	}

}

