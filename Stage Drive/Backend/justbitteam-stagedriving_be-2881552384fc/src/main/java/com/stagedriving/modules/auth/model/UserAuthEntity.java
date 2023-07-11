package com.stagedriving.modules.auth.model;

import com.justbit.jedis.AbstractJedisEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class UserAuthEntity extends AbstractJedisEntity {

    private Map<String, String> data = new HashMap<>();

    public UserAuthEntity() {
    }

    public UserAuthEntity(Map<String, String> data) {
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
