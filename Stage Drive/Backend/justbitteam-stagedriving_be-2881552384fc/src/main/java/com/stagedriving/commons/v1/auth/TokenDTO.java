package com.stagedriving.commons.v1.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * Created by manuel on 10/02/16.
 */
@Data
@ToString(includeFieldNames=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {

    private String accessToken;
    private String accessExpires;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessExpires() {
        return accessExpires;
    }

    public void setAccessExpires(String accessExpires) {
        this.accessExpires = accessExpires;
    }
}
