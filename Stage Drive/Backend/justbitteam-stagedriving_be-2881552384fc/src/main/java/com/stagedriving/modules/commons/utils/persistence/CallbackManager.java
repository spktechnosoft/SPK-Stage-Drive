package com.stagedriving.modules.commons.utils.persistence;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.commons.TokenUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by simone on 14/12/15.
 */

@Singleton
public class CallbackManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CallbackManager.class);

    private Map<String, ConcurrentLinkedQueue<Runnable>> queues;

    @Inject
    private SessionFactory sessionFactory;

    public CallbackManager() {
        queues = new ConcurrentHashMap<>();
    }

    private String getKey() {
        if (sessionFactory.getCurrentSession() != null) {
            return String.valueOf(sessionFactory.getCurrentSession().hashCode());
        } else {
            return "SCX:"+TokenUtils.generateUid();
        }
    }

    public void addCallback(Runnable runnable) {
        String key = getKey();

        ConcurrentLinkedQueue<Runnable> queue = queues.get(key);
        if (queue == null) {
            queue = new ConcurrentLinkedQueue<Runnable>();
            queues.put(key, queue);

            LOGGER.info("Adding queue on "+key);
        }
        LOGGER.info("Adding to queue on "+key);

        queue.add(runnable);

        if (key.startsWith("SCX:")) {
            this.processCallbacks();
        }
    }

    public void processCallbacks() {
        String key = getKey();
        if (key.startsWith("SCX:")) {
            return;
        }

        ConcurrentLinkedQueue<Runnable> queue = queues.get(key);

        if (queue != null) {
            LOGGER.info("Processing callbacks on "+key);
            while(!queue.isEmpty()) {
                Runnable future = queue.poll();
                future.run();

            }
        }
    }
}
