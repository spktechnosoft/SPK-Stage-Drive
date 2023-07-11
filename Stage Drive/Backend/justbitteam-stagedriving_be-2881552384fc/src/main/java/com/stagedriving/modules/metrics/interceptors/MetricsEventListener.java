package com.stagedriving.modules.metrics.interceptors;

import com.github.rollingmetrics.counter.ResetOnSnapshotCounter;
import com.google.inject.Inject;
import com.justbit.sque.SqueController;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.ds.AbstractEventListener;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.utils.MetricsHelper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.*;
import org.hibernate.persister.entity.EntityPersister;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MetricsEventListener extends AbstractEventListener implements PostCommitInsertEventListener, PostCommitUpdateEventListener, PostCommitDeleteEventListener {

    @Inject
    private SqueController squeController;
    @Inject
    private MetricsHelper metricsHelper;

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        if (postInsertEvent.getEntity() instanceof Account) {
            Account account = (Account) postInsertEvent.getEntity();
            processAccount(account, null);
        } else if (postInsertEvent.getEntity() instanceof AccountHasGroup) {
            AccountHasGroup accountHasGroup = (AccountHasGroup) postInsertEvent.getEntity();
            AccountGroup group = accountHasGroup.getAccountGroup();

            metricsHelper.resetOnSnapshotCounter(Account.class, "groups", group.getName()).add(1);
        } else if (postInsertEvent.getEntity() instanceof Event) {
            processEvent(postInsertEvent, null);
        } else if (postInsertEvent.getEntity() instanceof Ride) {
            processRide(postInsertEvent, null);
        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
        if (postUpdateEvent.getEntity() instanceof Account) {
            Account account = (Account) postUpdateEvent.getEntity();
            processAccount(account, postUpdateEvent);
        } else if (postUpdateEvent.getEntity() instanceof Event) {
            processEvent(null, postUpdateEvent);
        } else if (postUpdateEvent.getEntity() instanceof Ride) {
            processRide(null, postUpdateEvent);
        }
    }

    @Override
    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
        if (postDeleteEvent.getEntity() instanceof Account) {
            Account account = (Account) postDeleteEvent.getEntity();
//            processAccount(account, null);
        } else if (postDeleteEvent.getEntity() instanceof AccountHasGroup) {
            AccountHasGroup accountHasGroup = (AccountHasGroup) postDeleteEvent.getEntity();
            AccountGroup group = accountHasGroup.getAccountGroup();

            metricsHelper.resetOnSnapshotCounter(Account.class, "groups", group.getName()).add(-1);
        }
    }

    @Override
    public void onPostDeleteCommitFailed(PostDeleteEvent event) {

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

    /**
     * Account
     */
    private void processAccount(Account newAccount, PostUpdateEvent postUpdateEvent) {
        if (newAccount == null) {
            return;
        }

        if (postUpdateEvent != null) {
            if (checkChanges(postUpdateEvent, "favCategory")) {
                String oldFavCategory = (String) getOldValue(postUpdateEvent, "favCategory");
                String favCategory = newAccount.getFavCategory();
                metricsHelper.resetOnSnapshotCounter(Account.class, "favCategory", oldFavCategory).add(-1);
                metricsHelper.resetOnSnapshotCounter(Account.class, "favCategory", favCategory).add(1);
            }
            if (checkChanges(postUpdateEvent, "favStyle")) {
                String oldFavStyle = (String) getOldValue(postUpdateEvent, "favStyle");
                String favStyle = newAccount.getFavStyle();
                metricsHelper.resetOnSnapshotCounter(Account.class, "favStyle", oldFavStyle).add(-1);
                metricsHelper.resetOnSnapshotCounter(Account.class, "favStyle", favStyle).add(1);
            }

        } else {
            metricsHelper.resetOnSnapshotCounter(Account.class, "count").add(1);

            metricsHelper.resetOnSnapshotCounter(Account.class, "favCategory", newAccount.getFavCategory()).add(1);
            metricsHelper.resetOnSnapshotCounter(Account.class, "favStyle", newAccount.getFavStyle()).add(1);
        }

        if (newAccount.getGender() != null) {
            ResetOnSnapshotCounter maleMetric = metricsHelper.resetOnSnapshotCounter(Account.class, "gender", StgdrvData.AccountGenders.MALE);
            ResetOnSnapshotCounter femaleMetric = metricsHelper.resetOnSnapshotCounter(Account.class, "gender", StgdrvData.AccountGenders.FEMALE);

            Integer maleValue = 0;
            Integer femaleValue = 0;

            if (postUpdateEvent != null) {
                if (checkChanges(postUpdateEvent, "gender", StgdrvData.AccountGenders.MALE)) {
                    maleValue = 1;
                    femaleValue = -1;
                } else if (checkChanges(postUpdateEvent, "gender", StgdrvData.AccountGenders.FEMALE)) {
                    maleValue = -1;
                    femaleValue = 1;
                }
            } else {

                if (newAccount.getGender().equals(StgdrvData.AccountGenders.MALE)) {
                    maleValue = 1;
                } else if (newAccount.getGender().equals(StgdrvData.AccountGenders.FEMALE)) {
                    femaleValue = 1;
                }
            }

            maleMetric.add(maleValue);
            femaleMetric.add(femaleValue);
        }

        if (newAccount.getBirthday() != null) {

            if (postUpdateEvent != null) {
                if (checkChanges(postUpdateEvent, "birthday")) {

                    Date oldBirthday = (Date) getOldValue(postUpdateEvent, "birthday");
                    if (oldBirthday != null) {
                        DateTime oldBirthdate = new DateTime(oldBirthday);
                        String oldAge = getAgeFromDate(oldBirthdate);
                        metricsHelper.resetOnSnapshotCounter(Account.class, "age", oldAge).add(-1);
                    }

                    DateTime birthdate = new DateTime(newAccount.getBirthday());
                    String age = getAgeFromDate(birthdate);
                    metricsHelper.resetOnSnapshotCounter(Account.class, "age", age).add(1);
                }
            } else {
                DateTime birthdate = new DateTime(newAccount.getBirthday());
                String age = getAgeFromDate(birthdate);
                metricsHelper.resetOnSnapshotCounter(Account.class, "age", age).add(1);
            }
        }

    }

    /**
     * Event
     */
    private void processEvent(PostInsertEvent postInsertEvent, PostUpdateEvent postUpdateEvent) {

        if (postUpdateEvent != null) {
            updateValue(postUpdateEvent, Event.class, "category");
            updateValue(postUpdateEvent, Event.class, "city");
//            updateValue(postUpdateEvent, Event.class, "address");

            updateValueWithVal(postUpdateEvent, Event.class, "ncheckin");
            updateValueWithVal(postUpdateEvent, Event.class, "nlike");
            updateValueWithVal(postUpdateEvent, Event.class, "nride");
            updateValueWithVal(postUpdateEvent, Event.class, "nbooking");
            updateValueWithVal(postUpdateEvent, Event.class, "nbookingticket");
            updateValueWithVal(postUpdateEvent, Event.class, "ncomment");
        } else if (postInsertEvent != null) {
            metricsHelper.resetOnSnapshotCounter(Event.class, "count").add(1);

            updateValue(postInsertEvent, Event.class, "category");
            updateValue(postInsertEvent, Event.class, "city");
//            updateValue(postInsertEvent, Event.class, "address");

            updateValueWithVal(postInsertEvent, Event.class, "ncheckin");
            updateValueWithVal(postInsertEvent, Event.class, "nlike");
            updateValueWithVal(postInsertEvent, Event.class, "nride");
            updateValueWithVal(postInsertEvent, Event.class, "nbooking");
            updateValueWithVal(postInsertEvent, Event.class, "nbookingticket");
            updateValueWithVal(postInsertEvent, Event.class, "ncomment");
        }
    }

    /**
     * Ride
     */
    private void processRide(PostInsertEvent postInsertEvent, PostUpdateEvent postUpdateEvent) {

        if (postUpdateEvent != null) {
            updateIntegerValue(postUpdateEvent, Ride.class, "seats");
            updatePriceValue(postUpdateEvent, Ride.class, "price");
            updatePriceValue(postUpdateEvent, Ride.class, "totalPrice");
            updatePriceValue(postUpdateEvent, Ride.class, "fee");

        } else if (postInsertEvent != null) {
            metricsHelper.resetOnSnapshotCounter(Ride.class, "count").add(1);

            updateIntegerValue(postInsertEvent, Ride.class, "seats");
            updatePriceValue(postInsertEvent, Ride.class, "price");
            updatePriceValue(postInsertEvent, Ride.class, "totalPrice");
            updatePriceValue(postInsertEvent, Ride.class, "fee");
        }
    }

    private void updateValueWithVal(PostUpdateEvent postUpdateEvent, Class clazz, String name) {
        if (checkChanges(postUpdateEvent, name)) {
            Integer oldValue = (Integer) getOldValue(postUpdateEvent, name);
            Integer value = (Integer) getValue(postUpdateEvent, name);

            if (value != null) {
                if (oldValue == null) {
                    oldValue = 0;
                }

                Integer newValue = value - oldValue;

                metricsHelper.resetOnSnapshotCounter(clazz, name).add(newValue);
            }
        }
    }

    private void updateValue(PostUpdateEvent postUpdateEvent, Class clazz, String name) {
        if (checkChanges(postUpdateEvent, name)) {
            String oldValue = (String) getOldValue(postUpdateEvent, name);
            String value = (String) getValue(postUpdateEvent, name);
            if (oldValue != null)
                metricsHelper.resetOnSnapshotCounter(clazz, name, oldValue).add(-1);
            if (value != null)
                metricsHelper.resetOnSnapshotCounter(clazz, name, value).add(1);
        }
    }

    private void updateValue(PostInsertEvent postInsertEvent, Class clazz, String name) {
        String value = (String) getValue(postInsertEvent, name);
        if (value != null)
            metricsHelper.resetOnSnapshotCounter(clazz, name, value).add(1);
    }

    private void updateIntegerValue(PostInsertEvent postInsertEvent, Class clazz, String name) {
        Integer value = (Integer) getValue(postInsertEvent, name);
        if (value != null)
            metricsHelper.resetOnSnapshotCounter(clazz, name, String.valueOf(value)).add(1);
    }

    private void updateIntegerValue(PostUpdateEvent postUpdateEvent, Class clazz, String name) {
        if (checkChanges(postUpdateEvent, name)) {
            Integer oldValue = (Integer) getOldValue(postUpdateEvent, name);
            Integer value = (Integer) getValue(postUpdateEvent, name);
            if (oldValue != null)
                metricsHelper.resetOnSnapshotCounter(clazz, name, String.valueOf(oldValue)).add(-1);
            if (value != null)
                metricsHelper.resetOnSnapshotCounter(clazz, name, String.valueOf(value)).add(1);
        }
    }

    private void updateValueWithVal(PostInsertEvent postInsertEvent, Class clazz, String name) {
        Integer value = (Integer) getValue(postInsertEvent, name);
        if (value != null)
            metricsHelper.resetOnSnapshotCounter(clazz, name).add(value);
    }

    private void updatePriceValue(PostUpdateEvent postUpdateEvent, Class clazz, String name) {
        if (checkChanges(postUpdateEvent, name)) {
            Double oldValue = (Double) getOldValue(postUpdateEvent, name);
            Double value = (Double) getValue(postUpdateEvent, name);

            if (oldValue != null)
                metricsHelper.resetOnSnapshotCounter(clazz, name, getPriceLevel(oldValue)).add(-1);
            if (value != null)
                metricsHelper.resetOnSnapshotCounter(clazz, name, getPriceLevel(value)).add(1);
        }
    }

    private void updatePriceValue(PostInsertEvent postInsertEvent, Class clazz, String name) {
        Double value = (Double) getValue(postInsertEvent, name);
        if (value != null)
            metricsHelper.resetOnSnapshotCounter(clazz, name, getPriceLevel(value)).add(1);
    }

    private String getPriceLevel(Double price) {

        String priceLevel = "0-1";
        if (price >= 0 && price <= 1) {
            priceLevel = "0-1";
        } else if (price > 1 && price <= 2) {
            priceLevel = "1-2";
        } else if (price > 2 && price <= 4) {
            priceLevel = "2-4";
        } else if (price > 4 && price <= 8) {
            priceLevel = "4-8";
        } else if (price > 8 && price <= 16) {
            priceLevel = "8-16";
        } else if (price > 16 && price <= 32) {
            priceLevel = "16-32";
        } else {
            priceLevel = "32+";
        }

        return priceLevel;
    }

    private String getAgeFromDate(DateTime birthdate) {
        DateTime now = DateTime.now();

        int years = now.getYear() - birthdate.getYear();
        String index = null;

        Map<String, Double> ages = new HashMap<>();
        if (years >= 13 && years <= 17) {
            index = "13-17";
        } else if (years >= 18 && years <= 24) {
            index = "18-24";
        } else if (years >= 25 && years <= 34) {
            index = "25-34";
        } else if (years >= 35 && years <= 44) {
            index = "35-44";
        } else if (years >= 45 && years <= 54) {
            index = "45-54";
        } else if (years >= 55 && years <= 64) {
            index = "55-64";
        } else if (years >= 65) {
            index = "65+";
        } else {
            index = "13-";
        }

        return index;
    }
}
