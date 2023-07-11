package com.stagedriving.modules.commons.ds.daos.model;
// Generated 12-giu-2020 12.45.52 by Hibernate Tools 3.4.0.CR1


import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.entities.*;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * Home object for domain model class Transaction.
 * @author Hibernate Tools
 */
 
 
 
public abstract class AbstractTransactionDAO extends AbstractDAO<Transaction> {

    private final Logger log = new LoggerContext().getLogger(AbstractTransactionDAO.class);

	public AbstractTransactionDAO(SessionFactory session) {
		super(session);
	}

	public Transaction findByUid(String uid) {
		return uniqueResult(namedQuery("Transaction.findByUid").setParameter("uid", uid).setCacheable(true));
	}
	public List<Transaction> findByStatus(String status) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByStatus");
		return list(q.setParameter("status", status).setCacheable(true));
	}
	public List<Transaction> findByVisible(Boolean visible) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByVisible");
		return list(q.setParameter("visible", visible).setCacheable(true));
	}
	public List<Transaction> findByCreated(Date created) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByCreated");
		return list(q.setParameter("created", created).setCacheable(true));
	}
	public List<Transaction> findByModified(Date modified) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByModified");
		return list(q.setParameter("modified", modified).setCacheable(true));
	}
	public List<Transaction> findByAccountIdFrom(Integer accountIdFrom) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByAccountIdFrom");
		return list(q.setParameter("accountIdFrom", accountIdFrom).setCacheable(true));
	}
	public List<Transaction> findByAccountIdTo(Integer accountIdTo) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByAccountIdTo");
		return list(q.setParameter("accountIdTo", accountIdTo).setCacheable(true));
	}
	public List<Transaction> findByRideId(Integer rideId) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByRideId");
		return list(q.setParameter("rideId", rideId).setCacheable(true));
	}
	public List<Transaction> findByEventId(Integer eventId) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByEventId");
		return list(q.setParameter("eventId", eventId).setCacheable(true));
	}
	public List<Transaction> findByProvider(String provider) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProvider");
		return list(q.setParameter("provider", provider).setCacheable(true));
	}
	public List<Transaction> findByProviderToken(String providerToken) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProviderToken");
		return list(q.setParameter("providerToken", providerToken).setCacheable(true));
	}
	public List<Transaction> findByProviderId(String providerId) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProviderId");
		return list(q.setParameter("providerId", providerId).setCacheable(true));
	}
	public List<Transaction> findByProviderFee(Double providerFee) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProviderFee");
		return list(q.setParameter("providerFee", providerFee).setCacheable(true));
	}
	public List<Transaction> findByAmount(Double amount) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByAmount");
		return list(q.setParameter("amount", amount).setCacheable(true));
	}
	public List<Transaction> findByCurrency(String currency) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByCurrency");
		return list(q.setParameter("currency", currency).setCacheable(true));
	}
	public List<Transaction> findByFee(Double fee) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByFee");
		return list(q.setParameter("fee", fee).setCacheable(true));
	}
	public List<Transaction> findByTotalAmount(Double totalAmount) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByTotalAmount");
		return list(q.setParameter("totalAmount", totalAmount).setCacheable(true));
	}
	public List<Transaction> findByRefundedAmount(Double refundedAmount) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByRefundedAmount");
		return list(q.setParameter("refundedAmount", refundedAmount).setCacheable(true));
	}
	public List<Transaction> findByPayedAt(Date payedAt) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByPayedAt");
		return list(q.setParameter("payedAt", payedAt).setCacheable(true));
	}
	public List<Transaction> findByRefundedAt(Date refundedAt) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByRefundedAt");
		return list(q.setParameter("refundedAt", refundedAt).setCacheable(true));
	}
	public List<Transaction> findByStatusMessage(String statusMessage) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByStatusMessage");
		return list(q.setParameter("statusMessage", statusMessage).setCacheable(true));
	}




	public List<Transaction> findByStatusPaged(String status, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByStatus");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("status", status).setCacheable(true));
	}

	public List<Transaction> findByStatusPaged(String status, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByStatus");
        q.setParameter("status", status).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByVisiblePaged(Boolean visible, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByVisible");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("visible", visible).setCacheable(true));
	}

	public List<Transaction> findByVisiblePaged(Boolean visible, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByVisible");
        q.setParameter("visible", visible).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByCreatedPaged(Date created, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByCreated");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("created", created).setCacheable(true));
	}

	public List<Transaction> findByCreatedPaged(Date created, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByCreated");
        q.setParameter("created", created).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByModifiedPaged(Date modified, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByModified");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("modified", modified).setCacheable(true));
	}

	public List<Transaction> findByModifiedPaged(Date modified, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByModified");
        q.setParameter("modified", modified).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByAccountIdFromPaged(Integer accountIdFrom, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByAccountIdFrom");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("accountIdFrom", accountIdFrom).setCacheable(true));
	}

	public List<Transaction> findByAccountIdFromPaged(Integer accountIdFrom, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByAccountIdFrom");
        q.setParameter("accountIdFrom", accountIdFrom).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByAccountIdToPaged(Integer accountIdTo, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByAccountIdTo");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("accountIdTo", accountIdTo).setCacheable(true));
	}

	public List<Transaction> findByAccountIdToPaged(Integer accountIdTo, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByAccountIdTo");
        q.setParameter("accountIdTo", accountIdTo).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByRideIdPaged(Integer rideId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByRideId");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("rideId", rideId).setCacheable(true));
	}

	public List<Transaction> findByRideIdPaged(Integer rideId, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByRideId");
        q.setParameter("rideId", rideId).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByEventIdPaged(Integer eventId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByEventId");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("eventId", eventId).setCacheable(true));
	}

	public List<Transaction> findByEventIdPaged(Integer eventId, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByEventId");
        q.setParameter("eventId", eventId).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByProviderPaged(String provider, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByProvider");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("provider", provider).setCacheable(true));
	}

	public List<Transaction> findByProviderPaged(String provider, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProvider");
        q.setParameter("provider", provider).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByProviderTokenPaged(String providerToken, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByProviderToken");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("providerToken", providerToken).setCacheable(true));
	}

	public List<Transaction> findByProviderTokenPaged(String providerToken, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProviderToken");
        q.setParameter("providerToken", providerToken).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByProviderIdPaged(String providerId, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByProviderId");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("providerId", providerId).setCacheable(true));
	}

	public List<Transaction> findByProviderIdPaged(String providerId, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProviderId");
        q.setParameter("providerId", providerId).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByProviderFeePaged(Double providerFee, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByProviderFee");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("providerFee", providerFee).setCacheable(true));
	}

	public List<Transaction> findByProviderFeePaged(Double providerFee, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByProviderFee");
        q.setParameter("providerFee", providerFee).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByAmountPaged(Double amount, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByAmount");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("amount", amount).setCacheable(true));
	}

	public List<Transaction> findByAmountPaged(Double amount, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByAmount");
        q.setParameter("amount", amount).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByCurrencyPaged(String currency, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByCurrency");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("currency", currency).setCacheable(true));
	}

	public List<Transaction> findByCurrencyPaged(String currency, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByCurrency");
        q.setParameter("currency", currency).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByFeePaged(Double fee, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByFee");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("fee", fee).setCacheable(true));
	}

	public List<Transaction> findByFeePaged(Double fee, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByFee");
        q.setParameter("fee", fee).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByTotalAmountPaged(Double totalAmount, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByTotalAmount");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("totalAmount", totalAmount).setCacheable(true));
	}

	public List<Transaction> findByTotalAmountPaged(Double totalAmount, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByTotalAmount");
        q.setParameter("totalAmount", totalAmount).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByRefundedAmountPaged(Double refundedAmount, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByRefundedAmount");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("refundedAmount", refundedAmount).setCacheable(true));
	}

	public List<Transaction> findByRefundedAmountPaged(Double refundedAmount, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByRefundedAmount");
        q.setParameter("refundedAmount", refundedAmount).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByPayedAtPaged(Date payedAt, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByPayedAt");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("payedAt", payedAt).setCacheable(true));
	}

	public List<Transaction> findByPayedAtPaged(Date payedAt, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByPayedAt");
        q.setParameter("payedAt", payedAt).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByRefundedAtPaged(Date refundedAt, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByRefundedAt");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("refundedAt", refundedAt).setCacheable(true));
	}

	public List<Transaction> findByRefundedAtPaged(Date refundedAt, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByRefundedAt");
        q.setParameter("refundedAt", refundedAt).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }

	public List<Transaction> findByStatusMessagePaged(String statusMessage, Integer page, Integer limit) {
	    org.hibernate.query.Query q = namedQuery("Transaction.findByStatusMessage");

	    if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

	    if (page != null && limit != null) {
	        q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

		return list(q.setParameter("statusMessage", statusMessage).setCacheable(true));
	}

	public List<Transaction> findByStatusMessagePaged(String statusMessage, Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findByStatusMessage");
        q.setParameter("statusMessage", statusMessage).setCacheable(true);

        if (page == null) {
           page = 0;
        }
        if (limit == null) {
           limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }



	public List<Transaction> findAll() {
		return list(namedQuery("Transaction.findAll").setCacheable(true));
	}

	public List<Transaction> findAllPaged(Integer page, Integer limit) {
        org.hibernate.query.Query q = namedQuery("Transaction.findAll");
        q.setCacheable(true);

        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);

        return res;
    }

	public List<Transaction> findAllPaged(Integer page, Integer limit, PagedResults<Transaction> results) {
        org.hibernate.query.Query q = namedQuery("Transaction.findAll");
        q.setCacheable(true);

        if (page == null) {
            page = 0;
        }
        if (limit == null) {
            limit = 50;
        }

        if (results != null) {
            fillResults(results, q, page, limit);
        }

        if (page != null && limit != null) {
            q.setFirstResult(page * limit);
            q.setMaxResults(limit);
        }

        List<Transaction> res = list(q);
        if (results != null) {
            results.setData(res);
        }

        return res;
    }


	public Session getCurrentSession() {
        return currentSession();
    }

	public void beginTransaction() {
        currentSession().beginTransaction();
    }

    public void endTransaction() {
        final org.hibernate.Transaction txn = currentSession().getTransaction();
            if (txn != null && txn.getStatus().isOneOf(TransactionStatus.ACTIVE)) {
                txn.commit();
            }
    }

	public void delete(Transaction transientInstance) {
        try {
            currentSession().delete(transientInstance);
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public void edit(Transaction transientInstance) {
        //log.debug("editing Transaction instance");
        try {
            persist(transientInstance);
            //log.debug("edit successful");
        }
        catch (RuntimeException re) {
            log.error("edit failed", re);
            throw re;
        }
    }
    
    public void create(Transaction transientInstance) {
        //log.debug("creating Transaction instance");
        try {
            persist(transientInstance);
            //log.debug("create successful");
        }
        catch (RuntimeException re) {
            log.error("create failed", re);
            throw re;
        }
    }

    public int fillResults(PagedResults results, Query query, String page, String limit) {
        if (results != null) {
            ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
            scrollableResults.last();
            results.setSize(scrollableResults.getRowNumber() + 1);

            if (page != null) {
                results.setPage(Integer.valueOf(page));
            }
            if (limit != null) {
                results.setLimit(Integer.valueOf(limit));
            }

            return results.getSize();
        }

        return -1;
    }

    public int fillResults(PagedResults results, Query query, Integer page, Integer limit) {
        if (results != null) {
            ScrollableResults scrollableResults = query.scroll(ScrollMode.FORWARD_ONLY);
            scrollableResults.last();
            results.setSize(scrollableResults.getRowNumber() + 1);

            if (page != null) {
                results.setPage(page);
            }
            if (limit != null) {
                results.setLimit(limit);
            }

            return results.getSize();
        }

        return -1;
    }
    
    public Transaction findById( Integer id) {
        //log.debug("getting Transaction instance with id: " + id);
        try {
            Transaction instance = get(id);
            //log.debug("get successful");
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
}

