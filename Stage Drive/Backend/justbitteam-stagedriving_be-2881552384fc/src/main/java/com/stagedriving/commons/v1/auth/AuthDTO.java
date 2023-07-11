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
public class AuthDTO {

    private String identifier;
    private String email;
    private String password;
}
