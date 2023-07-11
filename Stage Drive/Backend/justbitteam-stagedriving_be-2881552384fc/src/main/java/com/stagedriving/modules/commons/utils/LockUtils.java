package com.stagedriving.modules.commons.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.jedis.JedisUtils;
import com.justbit.sque.SqueUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by simone on 01/12/14.
 */
@Singleton
public class LockUtils {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LockUtils.class);

    @Inject
    private JedisPool jedisPool;

    public boolean syncLock(String resource, String id) {
        return syncLock(resource, id, 260);
    }

    public boolean syncLock(String resource, String id, Integer timeout) {
        Jedis session = jedisPool.getResource();
        boolean ret = false;
        try {
            ret = JedisUtils.syncLock(session, "scx", resource+"#"+id, SqueUtils.getLockHolder(), timeout);
            jedisPool.returnResource(session);
        } catch (Exception ex) {
            jedisPool.returnResource(session);
        }

        return ret;
    }

    public boolean lock(String resource, String id) {
        return lock(resource, id, 260);
    }

    public boolean lock(String resource, String id, Integer timeout) {
        Jedis session = jedisPool.getResource();
        boolean ret = false;
        try {
            ret = JedisUtils.lock(session, "scx", resource+"#"+id, SqueUtils.getLockHolder(), timeout);
            jedisPool.returnResource(session);
        } catch (Exception ex) {
            jedisPool.returnResource(session);
        }

        return ret;
    }

    public void unlock(String resource, String id) {
        Jedis session = jedisPool.getResource();
        try {
            JedisUtils.unlock(session, "scx", resource+"#"+id, SqueUtils.getLockHolder());
            jedisPool.returnResource(session);
        } catch (Exception ex) {
            jedisPool.returnResource(session);
        }
    }
}
