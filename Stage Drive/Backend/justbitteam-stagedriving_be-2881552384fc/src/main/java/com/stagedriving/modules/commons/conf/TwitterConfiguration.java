package com.stagedriving.modules.commons.conf;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: simone
 * Date: 09/09/13
 * Time: 13:08
 * To change this template use File | Settings | File Templates.
 */
public class TwitterConfiguration {

    @Valid
    @NotNull
    private String appId = "drejbxad3Xjg5dxlyMokX2Y9e";

    @Valid
    @NotNull
    private String appSecret = "9jo3KF68QVuJRvH6gmUcLjxibYPzB8WkRfUz64tnRIjoM877Xi";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
