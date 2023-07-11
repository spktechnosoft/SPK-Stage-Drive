/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.EventInterestDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasInterestDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasInterest;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.EventInterestMapperImpl;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import io.swagger.annotations.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/interests")
@Api(value = "interests", description = "Event interest resource")
public class InterestResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(InterestResource.class);

    private String pathEventId;

    @Inject
    private EventDAO eventDAO;
    @Inject
    private EventHasInterestDAO eventHasInterestDAO;
    @Inject
    private EventInterestMapperImpl interestMapper;
    @Inject
    private AccountDAO accountDAO;

    private AppConfiguration configuration;

    public String getPathEventId() {
        return pathEventId;
    }

    public void setPathEventId(String pathEventId) {
        this.pathEventId = pathEventId;
    }

    @Inject
    public InterestResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve event interests",
            notes = "Retrieves event interests",
            response = EventInterestDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getInterests(@Restricted(required = false) Account account,
                                @QueryParam("eventId") String eventUid,
                                @QueryParam("accountId") String accountUid,
                                @QueryParam("limit") @ApiParam(required = true) IntParam limit,
                                @QueryParam("page") @ApiParam(required = true) IntParam page) {



        Event event = null;
        if (eventUid != null) {
            pathEventId = eventUid;
        }

        if (pathEventId != null) {
            event = eventDAO.findByUid(pathEventId);
        }

        Integer accountId = null;
        if (accountUid != null && !accountUid.isEmpty()) {
            Account acc = accountDAO.findByUid(accountUid);
            Preconditions.checkArgument(acc != null, "Invalid account");
            accountId = acc.getId();
        } else if (account != null) {
            accountId = account.getId();
        }

        List<EventInterestDTO> eventInterestDTOS = new ArrayList<>();
        List<EventHasInterest> eventHasInterests = eventHasInterestDAO.findByFilters(page.get(), limit.get(), event, accountId);
        for (EventHasInterest eventHasInterest : eventHasInterests) {
            EventInterestDTO eventInterestDTO = interestMapper.eventInterestToEventInterestDto(eventHasInterest);

            eventInterestDTOS.add(eventInterestDTO);
        }

        return Response.ok(eventInterestDTOS).build();
    }
}
