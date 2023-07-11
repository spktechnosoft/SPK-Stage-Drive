package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractItemCategoryDAO;
import com.stagedriving.modules.commons.ds.entities.ItemCategory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class ItemCategory.
 *
 * @author Hibernate Tools
 */

@Singleton
public class ItemCategoryDAO extends AbstractItemCategoryDAO {

    private final Logger log = new LoggerContext().getLogger(ItemCategoryDAO.class);

    @Inject
    public ItemCategoryDAO(/*@Named("datastore1") */SessionFactory session) {
        super(session);
    }

    public List<ItemCategory> findLikeByName(String name) {

        List<ItemCategory> itemCategories = null;

        StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM ItemCategory acc ");

        queryBuilder.append("WHERE true = true ");
        if (name != null) {
            name = name.toLowerCase() + "%";
            queryBuilder.append("AND (LOWER(acc.name) LIKE :name ");
            queryBuilder.append("OR LOWER(acc.description) LIKE :name )");
        }

        try {
            Query query = currentSession().createQuery(queryBuilder.toString());
            if (name != null) {
                query.setParameter("name", name);
            }
            query.setCacheable(true);
            itemCategories = (List<ItemCategory>) query.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return itemCategories;
        }
    }

}

