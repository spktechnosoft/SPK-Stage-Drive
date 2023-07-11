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
public class DriveResponseRefreshTokenDTO {

    private String access_token;
    private String expires_in;
    private String token_type;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
