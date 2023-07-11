package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractItemHasBrandDAO;
import com.stagedriving.modules.commons.ds.entities.Brand;
import com.stagedriving.modules.commons.ds.entities.Item;
import com.stagedriving.modules.commons.ds.entities.ItemHasBrand;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class ItemHasBrand.
 * @author Hibernate Tools
 */
 
@Singleton
public class ItemHasBrandDAO extends AbstractItemHasBrandDAO {

    private final Logger log = new LoggerContext().getLogger(ItemHasBrandDAO.class);

	@Inject
	public ItemHasBrandDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<ItemHasBrand> findByItemBrand(Brand brand, Item item) {
		List<ItemHasBrand> itemHasBrands = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasBrand acc ");

		queryBuilder.append(" WHERE acc.brand = :brand ");
		queryBuilder.append(" AND acc.item = :item ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("item", item);
			query.setParameter("brand", brand);
			query.setCacheable(true);
			itemHasBrands = (List<ItemHasBrand>) query.list();
		} catch (Exception ex) {
		} finally {
			return itemHasBrands;
		}
	}

}

