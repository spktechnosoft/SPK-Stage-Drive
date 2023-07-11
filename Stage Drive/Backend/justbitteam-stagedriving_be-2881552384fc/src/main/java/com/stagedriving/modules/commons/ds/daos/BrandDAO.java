package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.commons.v1.resources.BrandDTO;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.model.AbstractBrandDAO;
import com.stagedriving.modules.commons.ds.entities.Brand;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class Brand.
 * @author Hibernate Tools
 */
 
@Singleton
public class BrandDAO extends AbstractBrandDAO {

    private final Logger log = new LoggerContext().getLogger(BrandDAO.class);

	@Inject
	public BrandDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}

	public List<Brand> findBrandByName(String brand, int page, int limit) {

		List<Brand> brands = null;

		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Brand acc ");

		brand = brand.toLowerCase() + "%";
		queryBuilder.append("WHERE LOWER(acc.name) LIKE :brand ");

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			query.setParameter("brand", brand);
			brands = (List<Brand>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return brands;
		}
	}

	public List<Brand> findBrandByVisible(boolean visible, int page, int limit, PagedResults<BrandDTO> results) {

		List<Brand> brands = null;
		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Brand acc ");
		queryBuilder.append("WHERE acc.visible = :visible");
		try {
			Query query = currentSession().createQuery(queryBuilder.toString());

			query.setParameter("visible", visible);

			if (results != null) {
				fillResults(results, query, page, limit);
			}

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);

			brands = (List<Brand>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return brands;
		}
	}

	public List<Brand> findBrandByBase(boolean base) {

		List<Brand> brands = null;
		StringBuilder queryBuilder = new StringBuilder("SELECT acc FROM Brand acc ");
		queryBuilder.append(" WHERE acc.base =:base ");
		queryBuilder.append(" AND acc.visible = true ");
		try {
			Query query = currentSession().createQuery(queryBuilder.toString());
			query.setCacheable(true);
			query.setParameter("base", base);
			brands = (List<Brand>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return brands;
		}
	}

}

