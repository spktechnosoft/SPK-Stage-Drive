/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.exception;

import javax.ws.rs.core.Response.Status;

/**
 *
 * @author simone
 */
public class StgdrvLoginRequestException extends RuntimeException {

    private Status statusLine;
    private int code;
    private String redirectUri;

    public StgdrvLoginRequestException(String string) {
        super(string);
    }

    public StgdrvLoginRequestException(Status statusLine, int code, String message, String redirectUri) {
        super(message);

        this.statusLine = statusLine;
        this.code = code;
        this.redirectUri = redirectUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public Status getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(Status statusLine) {
        this.statusLine = statusLine;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
