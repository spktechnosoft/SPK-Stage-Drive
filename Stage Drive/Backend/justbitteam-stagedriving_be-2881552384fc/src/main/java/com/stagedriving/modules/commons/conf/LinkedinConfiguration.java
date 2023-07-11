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
public class LinkedinConfiguration {

    @Valid
    @NotNull
    private String appId = "77b5nnzk5lui15";

    @Valid
    @NotNull
    private String appSecret = "VmFTOZLFtvAy6ItI";

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
