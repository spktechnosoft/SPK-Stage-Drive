package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.modules.commons.ds.daos.model.AbstractColorDAO;
import com.stagedriving.modules.commons.ds.entities.Color;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class Color.
 * @author Hibernate Tools
 */
 
@Singleton
public class ColorDAO extends AbstractColorDAO {

    private final Logger log = new LoggerContext().getLogger(ColorDAO.class);

	@Inject
	public ColorDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<Color> findColorByName(String color, int page, int limit) {

		List<Color> colors = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Color acc ");

		color = color.toLowerCase() + "%";
		queryBuilder.append("WHERE LOWER(acc.name) LIKE :color ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			query.setParameter("color", color);
			colors = (List<Color>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return colors;
		}
	}

}

