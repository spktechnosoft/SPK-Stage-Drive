package com.stagedriving.modules.auth.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.justbit.jedis.AbstractJedisDAO;
import com.justbit.jedis.JedisCacheController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Singleton
public class UserAuthDAO extends AbstractJedisDAO<UserAuthEntity> {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserAuthEntity.class);

    @Inject
    public UserAuthDAO(JedisPool jedisPool, JedisCacheController jedisCacheController) {
        super(jedisPool, jedisCacheController);
    }

    public void create(UserAuthEntity authData) throws Exception {
        boolean leaveOpen = false;
        try {
            Jedis jedis = currentSession();
            if (jedis != null) {
                leaveOpen = true;
            } else {
                jedis = beginSession();
            }

            save(authData);

        } catch (Exception ex) {
            rollback();
            throw ex;
        } finally {
            if (!leaveOpen) {
                endSession();
            }
        }
    }

    public void update(UserAuthEntity authData) throws Exception {
        boolean leaveOpen = false;
        try {
            Jedis jedis = currentSession();
            if (jedis != null) {
                leaveOpen = true;
            } else {
                jedis = beginSession();
            }

            save(authData);

        } catch (Exception ex) {
            rollback();
            throw ex;
        } finally {
            if (!leaveOpen) {
                endSession();
            }
        }
    }

    public void remove(UserAuthEntity log) throws Exception {
        boolean leaveOpen = false;
        try {
            Jedis jedis = currentSession();
            if (jedis != null) {
                leaveOpen = true;
            } else {
                jedis = beginSession();
            }

            super.remove(log.getId());

        } catch (Exception ex) {
            rollback();
            throw ex;
        } finally {
            if (!leaveOpen) {
                endSession();
            }
        }
    }

    public List<UserAuthEntity> getUserDescriptor(String token, Integer page, Integer limit) throws Exception {
        List<UserAuthEntity> results = new ArrayList<UserAuthEntity>();
        boolean leaveOpen = false;
        try {
            Jedis jedis = currentSession();
            if (jedis != null) {
                leaveOpen = true;
            } else {
                jedis = beginSession();
            }

            Page pg = getPage(page, limit);

            List<String> items = jedis.lrange(token, pg.page, pg.limit);

            for (String item : items) {

                UserAuthEntity node = load(item, UserAuthEntity.class);
                results.add(node);
            }

        } catch (Exception ex) {
            rollback();
            throw ex;
        } finally {
            if (!leaveOpen) {
                endSession();
            }
        }
        return results;
    }
}
