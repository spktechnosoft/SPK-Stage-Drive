/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.batches;

import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.modules.commons.dispatcher.GlobalEventController;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasInterestDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountDevice;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.event.batches.model.RecommendedEventBatchEvent;
import com.stagedriving.modules.event.controllers.EventController;
import com.stagedriving.modules.event.views.EventNewPushNotificationView;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.notification.controllers.model.NotificationRecipientHolder;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpiringMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author simone
 */
@Singleton
@Slf4j
public class RecommendedEventBatchService extends SqueServiceAbstract<RecommendedEventBatchEvent, Boolean> {

    @Inject
    private SqueController squeController;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private EventHasInterestDAO eventHasInterestDAO;
    @Inject
    private EventController eventController;
    @Inject
    private NotificationController notificationController;

    public static Map<String, String> users = ExpiringMap.builder()
            .maxSize(20000)
            .expiration(12, TimeUnit.HOURS)
            .build();
    private List<Event> events;

    @Inject
    public RecommendedEventBatchService(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        this.withoutTransaction = true;
        eventBusService.registerReceiver(this);
    }

    @Subscribe
    public void onMessage(RecommendedEventBatchEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, RecommendedEventBatchEvent item) throws Exception {

        squeController.logInfo(item.getJobId(), "Processing events");

        // Fetch all events that will start next week (7 days) and finish in the next 67 days
        events = eventDAO.findByFilters(false, 0, 100, null, DateTime.now().plusDays(7).toDate(), DateTime.now().plusDays(7).plusMonths(2).toDate(), null, null, null, null, -1, null, null, null, null, null, null, null, null);

        for (Event event : events) {
            processEvent(event, item);
        }

        return null;
    }

    private void processEvent(Event event, RecommendedEventBatchEvent item) throws Exception {
        squeController.logInfo(item.getJobId(), "Updating event " + event.getUid());

        if (event.getCategory() == null) {
            return;
        }

        List<NotificationRecipientHolder> devices = new ArrayList<>();
        List<Account> accounts = accountDAO.findByFavCategory(event.getCategory());
        for (Account account : accounts) {
            if (users.containsKey(account.getUid())) {
                continue;
            }
            List<AccountDevice> deviceList = account.getAccountDevices();
            for (AccountDevice accountDevice : deviceList) {
                if (accountDevice.getDeviceid() != null) {
                    devices.add(new NotificationRecipientHolder(accountDevice.getOs(), accountDevice.getDeviceid()));
                }
            }
            users.put(account.getUid(), account.getUid());

            squeController.logInfo(item.getJobId(), "Sending to "+account.getEmail());
        }

        EventNewPushNotificationView eventNewPushNotificationView = new EventNewPushNotificationView();
        eventNewPushNotificationView.setTitle("Nuovo evento in arrivo");
        eventNewPushNotificationView.setEventName(event.getName());
        eventNewPushNotificationView.fillData("com.stagedriving.events.new", GlobalEventController.GlobalEventCategories.EVENT, event.getEventImages().get(0).getImage(), event.getUid());

        notificationController.sendPushNotification(devices, eventNewPushNotificationView, null);

    }

    @Override
    protected void onPostExecute(Boolean item) {

    }
}
