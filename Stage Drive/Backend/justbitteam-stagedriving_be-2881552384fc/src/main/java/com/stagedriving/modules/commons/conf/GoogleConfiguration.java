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
public class GoogleConfiguration {

    @Valid
    @NotNull
    private String appId = "167981231408-eq871ctktaadjr8qc02gkkhbnb71gkat.apps.googleusercontent.com";

    @Valid
    @NotNull
    private String appSecret = "k91pjefySVMTDNgL2hxbirS9";

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
