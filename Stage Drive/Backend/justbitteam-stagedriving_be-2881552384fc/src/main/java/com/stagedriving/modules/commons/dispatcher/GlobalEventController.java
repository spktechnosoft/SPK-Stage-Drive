package com.stagedriving.modules.commons.dispatcher;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.sque.SqueController;
import com.justbit.sque.ds.entities.SqueJob;
import com.stagedriving.modules.commons.dispatcher.model.GlobalEvent;
import com.stagedriving.modules.commons.ds.daos.NotificationDAO;
import com.stagedriving.modules.commons.ds.entities.Notification;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;

@Singleton
@Slf4j
public class GlobalEventController {

    private static String QUEUE = "events";

    public static class GlobalEventTypes {
        public static String EVENT_STARTED = "com.stagedriving.events.started";
        public static String EVENT_FINISHED = "com.stagedriving.events.finished";
        public static String RIDE_STARTED = "com.stagedriving.rides.started";
        public static String RIDE_FINISHED = "com.stagedriving.rides.finished";
        public static String RIDE_CANCELLED = "com.stagedriving.rides.cancelled";
        public static String EVENT_NEW_ORG_COMMENT = "com.stagedriving.events.neworgcomment";
        public static String EVENT_NEW_RIDE = "com.stagedriving.events.newride";
        public static String EVENT_NEW = "com.stagedriving.events.new";
        public static String EVENT_FRIEND_JOIN = "com.stagedriving.events.friendjoin";
        public static String EVENT_CANCELLED = "com.stagedriving.events.cancelled";
        public static String RIDE_NEW_PASSENGER = "com.stagedriving.rides.newpassenger";
        public static String RIDE_CANCEL_PASSENGER = "com.stagedriving.rides.cancelpassenger";
    }

    public static class GlobalEventCategories {
        public static String EVENT = "event";
        public static String RIDE = "ride";
    }

    @Inject
    private SqueController squeController;
    @Inject
    private JedisPool jedisPool;
    @Inject
    private NotificationDAO notificationDAO;

    public void unscheduleEvent(String id, String type) {

        try {

            String key = type + "-" + id;

            Jedis jedis = jedisPool.getResource();
            String existingJobId = jedis.get(key);
            if (existingJobId != null) {
                log.info("Unscheduling existing job " + existingJobId);
                squeController.unschedule(existingJobId);
                log.info("-- Unscheduled global event {} for {}", type, id);
                jedis.del(key);
            }

            jedisPool.returnResource(jedis);
        } catch (Exception ex) {
            log.error("Oops", ex);
        }
    }

    public void scheduleEvent(String id, String type, Date when) {

        try {

            String key = type + "-" + id;

            Notification notification = notificationDAO.findByTag(key);
            if (notification != null) {
                log.info("Notification already sent on {}", notification.getCreated());
                return;
            }

            Jedis jedis = jedisPool.getResource();

            String existingJobId = jedis.get(key);
            if (existingJobId != null) {
                log.info("Unscheduling existing job " + existingJobId);
                squeController.unschedule(existingJobId);
                jedis.del(key);
            }

            if (when != null && when.before(new Date())) {
                log.info("Skipping global event scheduled at {} for {} of type {}", when, id, type);
                return;
            }

            GlobalEvent globalEvent = new GlobalEvent();
            globalEvent.setId(id);
            globalEvent.setType(type);

            SqueJob job = squeController.enqueueScheduled(globalEvent, QUEUE, null, when);
            jedis.set(key, job.getId());

            jedisPool.returnResource(jedis);

            if (when != null) {
                log.info("Scheduled global event {} for {} at {}", type, id, when);
            } else {
                log.info("Scheduled global event {} for {}", type, id);
            }
        } catch (Exception ex) {
            log.error("Oops", ex);
        }
    }

}
