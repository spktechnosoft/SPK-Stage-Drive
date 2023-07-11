/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author simone
 */
public class StgdrvInvalidRequestException extends RuntimeException {

    private Response.Status statusLine;
    private int code;
    
    public StgdrvInvalidRequestException(String string) {
        super(string);
    }
    
    public StgdrvInvalidRequestException(Response.Status statusLine, int code, String message) {
        super(message);
        
        this.statusLine = statusLine;
        this.code = code;
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
