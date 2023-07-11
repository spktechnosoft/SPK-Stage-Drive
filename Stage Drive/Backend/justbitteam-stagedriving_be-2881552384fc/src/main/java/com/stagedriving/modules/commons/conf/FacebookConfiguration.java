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
public class FacebookConfiguration {

    @Valid
    @NotNull
    private String appId = "194185664065463";

    @Valid
    @NotNull
    private String appSecret = "1632c5aa832cf6fd6036f161f8ed0959";

    @Valid
    @NotNull
    private String namespace = "scxlocalhost";

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

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
