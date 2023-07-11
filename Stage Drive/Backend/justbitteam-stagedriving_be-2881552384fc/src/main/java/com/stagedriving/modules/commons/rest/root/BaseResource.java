/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.rest.root;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/")
public class BaseResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BaseResource.class);

    private AppConfiguration configuration;

    @Inject
    public BaseResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBase() {

        return Response.ok(new StgdrvResponseDTO()).build();
    }
}
