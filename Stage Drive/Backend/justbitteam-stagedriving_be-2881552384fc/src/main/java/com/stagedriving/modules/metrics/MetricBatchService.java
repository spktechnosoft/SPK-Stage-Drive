package com.stagedriving.modules.metrics;

import com.codahale.metrics.Counter;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.justbit.sque.SqueController;
import com.justbit.sque.SqueEventBus;
import com.justbit.sque.SqueServiceAbstract;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.notification.controllers.model.NotificationSchedulerEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class MetricBatchService extends SqueServiceAbstract<NotificationSchedulerEvent, Boolean> {

    @Inject
    private AccountDAO accountDAO;


    private Map<String, Counter> gender;

    @Inject
    public MetricBatchService(SessionFactory sessionFactory, SqueEventBus eventBusService, SqueController squeController) {
        super(sessionFactory, squeController);

        eventBusService.registerReceiver(this);

//        Counter maleCounter = metrics.counter(MetricRegistry.name(Account.class, "gender", StgdrvData.AccountGenders.MALE));
//        Counter femaleCounter = metrics.counter(MetricRegistry.name(Account.class, "gender", StgdrvData.AccountGenders.FEMALE));
//        this.gender = new ImmutableMap.Builder<String, Counter>()
//                .put(StgdrvData.AccountGenders.MALE, maleCounter)
//                .put(StgdrvData.AccountGenders.FEMALE, femaleCounter)
//                .build();

    }

    @Subscribe
    public void onMessage(NotificationSchedulerEvent item) {
        super.onMessage(item);
    }

    @Override
    protected String execute(Session session, NotificationSchedulerEvent item) throws Exception {

        List<Account> accountList = accountDAO.findAll();
        for (Account acc : accountList) {
            if (acc.getGender() != null) {
                //this.gender.get(acc.getGender()).
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Boolean item) {

    }
}
