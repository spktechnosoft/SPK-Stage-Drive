package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractItemDAO;
import com.stagedriving.modules.commons.ds.entities.Item;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class Item.
 * @author Hibernate Tools
 */
 
@Singleton
public class ItemDAO extends AbstractItemDAO {

    private final Logger log = new LoggerContext().getLogger(ItemDAO.class);

	@Inject
	public ItemDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<Item> findByFilters(int page, int limit, String search, Boolean male, Boolean female, Boolean children) {

		List<Item> items = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT acc FROM Item acc ");

		queryBuilder.append(" WHERE true = true ");
		if (search != null) {
			queryBuilder.append(" AND ( LOWER ( acc.name ) LIKE :search ");
			queryBuilder.append(" OR LOWER ( acc.description ) LIKE :search ");
			queryBuilder.append(") ");
		}
		if (male != null && female != null && children != null) {
			queryBuilder.append(" AND (acc.male = :male OR acc.female = :female OR acc.children = :children)");
		} else if (male != null && female != null && children == null) {
			queryBuilder.append(" AND (acc.male = :male OR acc.female = :female)");
		} else if (male != null && female == null && children != null) {
			queryBuilder.append(" AND (acc.male = :male OR acc.children = :children)");
		} else if (male == null && female != null && children != null) {
			queryBuilder.append(" AND (acc.female = :female OR acc.children = :children)");
		} else if (male != null && female == null && children == null) {
			queryBuilder.append(" AND acc.male = :male ");
		} else if (male == null && female != null && children == null) {
			queryBuilder.append(" AND acc.female = :female ");
		} else if (male == null && female == null && children != null) {
			queryBuilder.append(" AND acc.children = :children ");
		}
		queryBuilder.append(" AND acc.visible = 1 ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			if (search != null) {
				query.setParameter("search", search.toLowerCase() + "%");
			}
			if (male != null) {
				query.setParameter("male", male);
			}
			if (female != null) {
				query.setParameter("female", female);
			}
			if (children != null) {
				query.setParameter("children", children);
			}
			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);
			items = (List<Item>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return items;
		}
	}

	public List<Item> findByStatusByVisible(String status, boolean visible) {

		List<Item> items = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Item acc ");

		queryBuilder.append(" WHERE acc.status = :status ");
		queryBuilder.append(" AND acc.visible = :visible ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setParameter("status", status);
			query.setParameter("visible", visible);
			query.setCacheable(true);
			items = (List<Item>) query.list();
		} catch (Exception ex) {
		} finally {
			return items;
		}
	}

}

