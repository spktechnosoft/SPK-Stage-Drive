package com.stagedriving.modules.commons.views;

import com.stagedriving.modules.commons.exception.StgdrvLoginRequestException;
import io.dropwizard.views.View;

import javax.ws.rs.core.Response;

/**
 * Created with IntelliJ IDEA.
 * User: simone
 * Date: 13/07/13
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
public class ScxErrorView extends View {

    private final StgdrvLoginRequestException error;

    public ScxErrorView(StgdrvLoginRequestException error) {
        super("ErrorView.ftl");
        this.error = error;
    }

    public ScxErrorView(int code, Response.Status status, String message) {
        super("ErrorView.ftl");
        this.error = new StgdrvLoginRequestException(status, code, message, null);
    }

    public StgdrvLoginRequestException getError() {
        return error;
    }
}
