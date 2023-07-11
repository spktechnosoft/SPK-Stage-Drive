package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractItemHasCategoryDAO;
import com.stagedriving.modules.commons.ds.entities.Item;
import com.stagedriving.modules.commons.ds.entities.ItemCategory;
import com.stagedriving.modules.commons.ds.entities.ItemHasCategory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ItemHasCategory.
 * @author Hibernate Tools
 */
 
@Singleton
public class ItemHasCategoryDAO extends AbstractItemHasCategoryDAO {

    private final Logger log = new LoggerContext().getLogger(ItemHasCategoryDAO.class);

	@Inject
	public ItemHasCategoryDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public ItemHasCategory findByItemCategoryByItem(ItemCategory itemCategory, Item item) {
		ItemHasCategory itemHasCategory = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasCategory acc ");

		queryBuilder.append(" WHERE acc.itemCategory = :itemCategory ");
		queryBuilder.append(" AND acc.item = :item ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("itemCategory", itemCategory);
			query.setParameter("item", item);
			query.setCacheable(true);
			itemHasCategory = (ItemHasCategory) query.uniqueResult();
		} catch (Exception ex) {
		} finally {
			return itemHasCategory;
		}
	}

}

