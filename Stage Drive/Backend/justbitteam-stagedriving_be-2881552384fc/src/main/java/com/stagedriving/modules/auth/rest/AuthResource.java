/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.auth.rest;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.auth.AuthDTO;
import com.stagedriving.commons.v1.auth.TokenDTO;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.utils.DateUtils;
import com.stagedriving.modules.notification.controllers.NotificationController;
import com.stagedriving.modules.user.views.AccountRecoverEmailNotificationView;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/auth")
@Api(value = "auth", description = "Authentication")
public class AuthResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AuthResource.class);

    @Inject
    AccountDAO accountDAO;
    @Inject
    private NotificationController notificationController;

    private AppConfiguration configuration;

    @Inject
    public AuthResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "User authentication",
            notes = "User authentication",
            response = TokenDTO.class)
    public Response auth(@ApiParam(required = true) AuthDTO authDto) {

        Preconds.checkConditions(authDto.getIdentifier() == null && authDto.getEmail() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_EMAIL_NULL);

        Preconds.checkConditions(authDto.getPassword() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_PASSWORD_NULL);

        Account account = null;
        if (authDto.getIdentifier() != null) {
            account = accountDAO.findByEmailByPassword(authDto.getIdentifier(), authDto.getPassword());
        } else if (authDto.getEmail() != null) {
            account = accountDAO.findByEmailByPassword(authDto.getEmail(), authDto.getPassword());
        }
        Preconds.checkConditions(account == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST);

        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setAccessToken(account.getToken());
        tokenDto.setAccessExpires(DateUtils.dateToString(account.getExpires()));

        return Response.ok(tokenDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/recover")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "User password recover",
            notes = "User password recover",
            response = StgdrvResponseDTO.class)
    public Response recover(@ApiParam(required = true) AuthDTO authDto) throws Exception {

        Preconds.checkConditions(authDto.getIdentifier() == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_EMAIL_NULL);

        Account account = accountDAO.findByEmail(authDto.getIdentifier());
        Preconds.checkConditions(account == null,
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST);


        account.setPassword(TokenUtils.generateUid());
        accountDAO.edit(account);

        AccountRecoverEmailNotificationView emailNotificationView = new AccountRecoverEmailNotificationView();
        emailNotificationView.setSubject("La tua nuova password");
        emailNotificationView.setPassword(account.getPassword());
        emailNotificationView.setUser(account);
        notificationController.sendEmailNotification("me", ImmutableList.of(account.getEmail()), emailNotificationView);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }
}
