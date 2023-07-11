package com.stagedriving.modules.auth.controller;

import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.auth.model.UserAuthDAO;
import com.stagedriving.modules.auth.model.UserAuthEntity;

import java.util.HashMap;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class UserAuthController {

    @Inject
    private UserAuthDAO userAuthDAO;

    @Inject
    public UserAuthController() {
    }

    public UserAuthEntity build(String userId, String userToken, String userTokenExpires) throws Exception {
        UserAuthEntity entity = new UserAuthEntity();
        entity.setId(userToken);

        HashMap<String, String> data = new HashMap<String, String>();
        data.put(StgdrvData.Auth.USER_ID, userId);
        data.put(StgdrvData.Auth.USER_TOKEN_EXPIRES, userTokenExpires);
        entity.setData(data);

        return entity;
    }

    public String save(UserAuthEntity entity, String token) throws Exception {
        entity.setId(token);
        userAuthDAO.create(entity);

        return token;
    }

    public String buildAndSave(String userId, String userToken, String userTokenExpires) throws Exception {
        UserAuthEntity entity = build(userId, userToken, userTokenExpires);
        save(entity, userToken);

        return userToken;
    }

    public void delete(String token) throws Exception {
        userAuthDAO.remove(token);
    }

    public UserAuthEntity fetch(String token) throws Exception {
        return userAuthDAO.load(token, UserAuthEntity.class);
    }

    public void update(UserAuthEntity entity) throws Exception {
        userAuthDAO.update(entity);
    }

    public String fetchAppId(String token) throws Exception {
        UserAuthEntity userAuthEntity = userAuthDAO.load(token, UserAuthEntity.class);

        if (userAuthEntity != null && userAuthEntity.getData() != null)
            if (userAuthEntity.getData().get(StgdrvData.Auth.APP_ID) != null)
                return userAuthEntity.getData().get(StgdrvData.Auth.APP_ID);

        return null;
    }

    public String fetchUserId(String token) throws Exception {
        UserAuthEntity userAuthEntity = userAuthDAO.load(token, UserAuthEntity.class);

        if (userAuthEntity != null && userAuthEntity.getData() != null)
            if (userAuthEntity.getData().get(StgdrvData.Auth.USER_ID) != null)
                return userAuthEntity.getData().get(StgdrvData.Auth.USER_ID);

        return null;
    }

    public String fetchAppSecret(String token) throws Exception {
        UserAuthEntity userAuthEntity = userAuthDAO.load(token, UserAuthEntity.class);

        if (userAuthEntity != null && userAuthEntity.getData() != null)
            if (userAuthEntity.getData().get(StgdrvData.Auth.APP_SECRET) != null)
                return userAuthEntity.getData().get(StgdrvData.Auth.APP_SECRET);

        return null;
    }

    public String fetchTokenExpire(String token) throws Exception {
        UserAuthEntity userAuthEntity = userAuthDAO.load(token, UserAuthEntity.class);
        if (userAuthEntity.getData().get(StgdrvData.Auth.USER_TOKEN_EXPIRES) != null)
            return userAuthEntity.getData().get(StgdrvData.Auth.USER_TOKEN_EXPIRES);

        return null;
    }
}
