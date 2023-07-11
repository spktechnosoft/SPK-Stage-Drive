package com.stagedriving.modules.event.interceptors;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasInterestDAO;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostCommitUpdateEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.persister.entity.EntityPersister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventInterestInterceptor extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventInterestInterceptor.class);

    @Inject
    private AccountDAO accountDAO;
    @Inject
    private EventHasInterestDAO eventHasInterestDAO;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {

        return;
//        if (postInsertEvent.getEntity() instanceof EventHasBooking) {
//            LOGGER.info("Intercepted booking changes");
//
//            EventHasBooking eventHasBooking = (EventHasBooking) postInsertEvent.getEntity();
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
//              //  event.getEventHasInterests().add(eventHasInterest);
//            }
//
////            if (eventHasBooking.getBooking().getWithTicket() && eventHasBooking.getBooking().getWithTicket()) {
////                eventHasInterest.setBookingTicketId(eventHasBooking.getBooking().getId());
////            }
////            eventHasInterest.setBookingId(eventHasBooking.getBooking().getId());
//
//            //eventHasInterestDAO.edit(eventHasInterest);
//        }
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
