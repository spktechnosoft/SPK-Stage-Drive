package com.stagedriving.modules.transaction.interceptors;

import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;

import java.util.List;

@Slf4j
public class TransactionInterceptor extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener {

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private TransactionDAO transactionDAO;
    @Inject
    private RidePassengerDAO ridePassengerDAO;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {

        if (postInsertEvent.getEntity() instanceof Transaction) {
            log.info("Transaction updated");

            Transaction transaction = (Transaction) postInsertEvent.getEntity();

            Ride ride = rideDAO.findById(transaction.getRideId());
            Event event = eventDAO.findById(transaction.getEventId());
            Account fromAccount = accountDAO.findById(transaction.getAccountIdFrom());
            List<RidePassenger> ridePassenger = ridePassengerDAO.findByAccountAndRide(fromAccount, ride);

            for (RidePassenger ridePass : ridePassenger) {

                if (transaction.getStatus().equals(StgdrvData.TransactionStatusses.PROCESSED)) {
                    log.info("Setting ride passenger "+ridePass.getUid());
                    ridePass.setStatus(StgdrvData.RidePassengersStatusses.ENABLED);
                    ridePass.setTransactionId(transaction.getUid());
                }
                ridePassengerDAO.edit(ridePass);
                ridePassengerDAO.getCurrentSession().flush();
            }


        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {

//        if (postUpdateEvent.getEntity() instanceof EventHasBooking) {
//            LOGGER.info("Intercepted booking changes");
//
//            EventHasBooking eventHasBooking = (EventHasBooking) postUpdateEvent.getEntity();
//
//            Integer accountId = eventHasBooking.getAccountid();
//            Account account = accountDAO.findById(accountId);
//            if (account == null) {
//                // Throw error
//                return;
//            }
//            Event event = eventHasBooking.getEvent();
//
//            List<EventHasInterest> eventHasInterests = eventHasInterestDAO.findByEventAndAccount(event, accountId);
//            EventHasInterest eventHasInterest = null;
//            if (!eventHasInterests.isEmpty()) {
//                eventHasInterest = eventHasInterests.get(0);
//            } else {
//                eventHasInterest = new EventHasInterest();
//                eventHasInterest.setUid(TokenUtils.generateUid());
//                eventHasInterest.setCreated(new Date());
//                eventHasInterest.setModified(new Date());
//                eventHasInterest.setEvent(event);
//                eventHasInterest.setAccountId(accountId);
//                event.getEventHasInterests().add(eventHasInterest);
//            }
//
//            if (eventHasBooking.getBooking().getWithTicket() && eventHasBooking.getBooking().getWithTicket()) {
//                eventHasInterest.setBookingTicketId(eventHasBooking.getBooking().getId());
//            }
//            eventHasInterest.setBookingId(eventHasBooking.getBooking().getId());
//
//            eventHasInterestDAO.edit(eventHasInterest);
//        }

    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return true;
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent event) {

    }

    @Override
    public void onPostUpdateCommitFailed(PostUpdateEvent event) {

    }
}
