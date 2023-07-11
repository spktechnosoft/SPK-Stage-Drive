package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.daos.model.AbstractItemHasActionDAO;
import com.stagedriving.modules.commons.ds.entities.Item;
import com.stagedriving.modules.commons.ds.entities.ItemHasAction;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class ItemHasAction.
 * @author Hibernate Tools
 */
 
@Singleton
public class ItemHasActionDAO extends AbstractItemHasActionDAO {

    private final Logger log = new LoggerContext().getLogger(ItemHasActionDAO.class);

	@Inject
	public ItemHasActionDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public ItemHasAction findByItemByAccountId(Item item, Integer accountId) {

		ItemHasAction itemHasAction = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasAction acc ");

		queryBuilder.append(" WHERE acc.item = :item ");
		queryBuilder.append(" AND acc.accountid = :accountid ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("item", item);
			query.setParameter("accountid", accountId);
			query.setCacheable(true);
			itemHasAction = (ItemHasAction) query.uniqueResult();
		} catch (Exception ex) {
		} finally {
			return itemHasAction;
		}
	}

	public ItemHasAction findByItemByAccountIdByTaxonomy(Item item, Integer accountId, String taxonomy) {

		ItemHasAction itemHasAction = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasAction acc ");

		queryBuilder.append(" WHERE acc.item = :item ");
		queryBuilder.append(" AND acc.accountid = :accountid ");
		queryBuilder.append(" AND acc.taxonomy = :taxonomy ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("item", item);
			query.setParameter("accountid", accountId);
			query.setParameter("taxonomy", taxonomy);
			query.setCacheable(true);
			itemHasAction = (ItemHasAction) query.uniqueResult();
		} catch (Exception ex) {
		} finally {
			return itemHasAction;
		}
	}

	public List<ItemHasAction> findByTaxonomyByAccountId(int page, int limit, String taxonomy, Integer accountId) {

		List<ItemHasAction> itemHasActions = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasAction acc ");

		queryBuilder.append(" WHERE acc.taxonomy = :taxonomy ");
		queryBuilder.append(" AND acc.accountid = :accountid ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("taxonomy", taxonomy);
			query.setParameter("accountid", accountId);

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			itemHasActions = (List<ItemHasAction>) query.list();
		} catch (Exception ex) {
		} finally {
			return itemHasActions;
		}
	}

	public List<ItemHasAction> findByTaxonomyByAccountIdByItem(int page, int limit, String taxonomy, Integer accountId, Item item) {

		List<ItemHasAction> itemHasActions = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasAction acc ");

		queryBuilder.append(" WHERE acc.taxonomy = :taxonomy ");
		queryBuilder.append(" AND acc.accountid = :accountid ");
		queryBuilder.append(" AND acc.item = :item ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("taxonomy", taxonomy);
			query.setParameter("accountid", accountId);
			query.setParameter("item", item);

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			itemHasActions = (List<ItemHasAction>) query.list();
		} catch (Exception ex) {
		} finally {
			return itemHasActions;
		}
	}

	public List<ItemHasAction> findByTaxonomyByAccountIdByItemOrdered(int page, int limit, String taxonomy, Integer accountId, Item item, String mode) {

		List<ItemHasAction> itemHasActions = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasAction acc ");

		queryBuilder.append(" WHERE acc.taxonomy = :taxonomy ");
		queryBuilder.append(" AND acc.accountid = :accountid ");
		queryBuilder.append(" AND acc.item = :item ");

		if(mode.equalsIgnoreCase(StgdrvData.Ordered.ASCENDING)){
			queryBuilder.append(" ORDER BY acc.created ASC");
		}else{
			queryBuilder.append(" ORDER BY acc.created DESC");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("taxonomy", taxonomy);
			query.setParameter("accountid", accountId);
			query.setParameter("item", item);

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			itemHasActions = (List<ItemHasAction>) query.list();
		} catch (Exception ex) {
		} finally {
			return itemHasActions;
		}
	}

	public List<ItemHasAction> findByTaxonomyByItemOrdered(int page, int limit, String taxonomy, Item item, String mode) {

		List<ItemHasAction> itemHasActions = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemHasAction acc ");

		queryBuilder.append(" WHERE acc.taxonomy = :taxonomy ");
		queryBuilder.append(" AND acc.item = :item ");

		if(mode.equalsIgnoreCase(StgdrvData.Ordered.ASCENDING)){
			queryBuilder.append(" ORDER BY acc.created ASC");
		}else{
			queryBuilder.append(" ORDER BY acc.created DESC");
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("taxonomy", taxonomy);
			query.setParameter("item", item);

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			itemHasActions = (List<ItemHasAction>) query.list();
		} catch (Exception ex) {
		} finally {
			return itemHasActions;
		}
	}

}

