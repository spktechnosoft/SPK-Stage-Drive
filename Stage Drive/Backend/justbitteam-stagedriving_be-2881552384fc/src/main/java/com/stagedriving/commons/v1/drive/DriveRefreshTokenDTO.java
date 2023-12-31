/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.commons.v1.drive;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author manuel
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriveRefreshTokenDTO {
    private String client_id;
    private String client_secret;
    private String refresh_token;
    private String grant_type;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
