/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.commons.rest.root;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.bundle.controllers.BundleController;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.user.controller.AccountController;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/admin")
@Api(value = "admin")
public class AdminResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AdminResource.class);

    private AppConfiguration configuration;
    @Inject
    private BundleController bundleController;
    @Inject
    private AccountController accountController;

    @Inject
    public AdminResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBase() {

        return Response.ok(new StgdrvResponseDTO()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateBundle")
    @UnitOfWork
    @ApiOperation(value = "Update bundle",
            notes = "",
            response = StgdrvResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response updateBundle(@Restricted Account me) throws InterruptedException, IOException {

        boolean isAdmin = accountController.isAdmin(me);
        Preconds.checkConditions(!isAdmin, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.INVALID_PERMISSION);

        new Thread(new Runnable() {
            public void run(){
                try {
                    bundleController.updateBundleCache();
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    LOGGER.error("Oops", e);
                }
            }
        }).start();

        return Response.ok().build();
    }
}
