package com.stagedriving.modules.commons.ds.daos;
// Porquoi?Generated 16-ott-2018 11.30.06 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.stagedriving.commons.v1.resources.TransactionDTO;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.model.AbstractTransactionDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.Ride;
import com.stagedriving.modules.commons.ds.entities.Transaction;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Home object for domain model class Transaction.
 * @author Hibernate Tools
 */
 
@Singleton
public class TransactionDAO extends AbstractTransactionDAO {

    private final Logger log = new LoggerContext().getLogger(TransactionDAO.class);

	@Inject
	public TransactionDAO(/*@Named("datastore1") */SessionFactory session) {
		super(session);
	}


	public List<Transaction> findByFilters(Account fromAccount, Account toAccount, Event event, Ride ride, String sort, String order, int page, int limit, String idLike, String amountLike, String feeLike, String totalAmountLike, String statusLike, String providerLike, PagedResults<TransactionDTO> results, boolean isAdmin) {

		List<Transaction> transactions = null;

		StringBuilder queryBuilder = new StringBuilder(" SELECT ev FROM Transaction ev ");

		if (!isAdmin) {
			queryBuilder.append(" WHERE ev.visible = 1 AND ev.status != 'deleted' ");
		} else {
			queryBuilder.append(" WHERE 1 = 1 ");
		}

		if (fromAccount != null) {
			queryBuilder.append(" AND ev.accountIdFrom = :fromAccountId ");
		}

		if (toAccount != null) {
			queryBuilder.append(" AND ev.accountIdTo = :toAccountId ");
		}

		if (ride != null) {
			queryBuilder.append(" AND ev.rideId = :rideId ");
		}

		if (event != null) {
			queryBuilder.append(" AND ev.eventId = :eventId ");
		}

		if (idLike != null) {
			queryBuilder.append(" AND ev.uid LIKE :idLike ");
		}
		if (amountLike != null) {
			queryBuilder.append(" AND ev.amount <= :amountLike ");
		}
		if (feeLike != null) {
			queryBuilder.append(" AND ev.fee <= :feeLike ");
		}
		if (totalAmountLike != null) {
			queryBuilder.append(" AND ev.totalAmount <= :totalAmountLike ");
		}
		if (statusLike != null) {
			queryBuilder.append(" AND ev.status = :statusLike ");
		}
		if (providerLike != null) {
			queryBuilder.append(" AND ev.provider = :providerLike ");
		}

		if (sort != null) {
			queryBuilder.append(" ORDER BY ev."+sort+" ");
		}
		if (order != null) {
			queryBuilder.append(order);
		}

		try {
			Query query = currentSession().createQuery(queryBuilder.toString());

			if (fromAccount != null) {
				query.setParameter("fromAccountId", fromAccount.getId());
			}

			if (toAccount != null) {
				query.setParameter("toAccountId", toAccount.getId());
			}

			if (ride != null) {
				query.setParameter("rideId", ride.getId());
			}

			if (event != null) {
				query.setParameter("eventId", event.getId());
			}

			if (idLike != null) {
				query.setParameter("idLike", "%"+idLike+"%");
			}
			if (amountLike != null) {
				query.setParameter("amountLike", Double.valueOf(amountLike));
			}
			if (feeLike != null) {
				query.setParameter("feeLike", Double.valueOf(feeLike));
			}
			if (totalAmountLike != null) {
				query.setParameter("totalAmountLike", Double.valueOf(totalAmountLike));
			}
			if (statusLike != null) {
				query.setParameter("statusLike", statusLike);
			}
			if (providerLike != null) {
				query.setParameter("providerLike", providerLike);
			}

			if (results != null) {
				fillResults(results, query, page, limit);
			}

			query.setFirstResult(page * limit);
			query.setMaxResults(limit);
			query.setCacheable(true);
			transactions = (List<Transaction>) query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return transactions;
		}
	}
}

