package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractItemHasColorDAO;
import com.stagedriving.modules.commons.ds.entities.Color;
import com.stagedriving.modules.commons.ds.entities.Item;
import com.stagedriving.modules.commons.ds.entities.ItemHasColor;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class ItemHasColor.
 * @author Hibernate Tools
 */
 
@Singleton
public class ItemHasColorDAO extends AbstractItemHasColorDAO {

    private final Logger log = new LoggerContext().getLogger(ItemHasColorDAO.class);

	@Inject
	public ItemHasColorDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<ItemHasColor> findByItemColor(Color color, Item item) {
		List<ItemHasColor> itemHasColors = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasColor acc ");

		queryBuilder.append(" WHERE acc.color = :color ");
		queryBuilder.append(" AND acc.item = :item ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("item", item);
			query.setParameter("color", color);
			query.setCacheable(true);
			itemHasColors = (List<ItemHasColor>) query.list();
		} catch (Exception ex) {
		} finally {
			return itemHasColors;
		}
	}

}

