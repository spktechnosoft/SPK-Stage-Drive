package com.stagedriving.modules.event.rest.l1;

import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.CheckinDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.CheckinDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasCheckinDAO;
import com.stagedriving.modules.commons.ds.entities.Checkin;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasCheckin;
import com.stagedriving.modules.commons.ds.entities.EventHasCheckinId;
import com.stagedriving.modules.commons.mapper.CheckinMapperImpl;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Project buyu-api
 * Author: man
 * Date: 20/11/15
 * Time: 16:58
 */
public class CheckinEventResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CheckinEventResource.class);

    @Inject
    CheckinMapperImpl checkinMapper;
    @Inject
    CheckinDAO checkinDAO;
    @Inject
    EventDAO eventDAO;
    @Inject
    EventHasCheckinDAO eventHasCheckinDAO;

    private AppConfiguration configuration;

    @Inject
    public CheckinEventResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response createCheckin(String eventId, CheckinDTO checkinDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CHECKIN_CREATE);

        Event event = eventDAO.findByUid(eventId);

        Checkin checkin = checkinMapper.checkinDtoToCheckin(checkinDto);
        checkin.setUid(TokenUtils.generateUid());
        checkin.setCreated(new Date());
        checkin.setModified(new Date());
        checkin.setVisible(true);
        checkin.setVisible(true);

        checkinDAO.create(checkin);

        EventHasCheckinId eventHasCheckinId = new EventHasCheckinId();
        eventHasCheckinId.setEventId(event.getId());
        eventHasCheckinId.setCheckinId(checkin.getId());
        EventHasCheckin eventHasCheckin = new EventHasCheckin();
        eventHasCheckin.setId(eventHasCheckinId);
        eventHasCheckin.setVisible(true);
        eventHasCheckin.setCreated(new Date());
        eventHasCheckin.setModified(new Date());

        eventHasCheckinDAO.create(eventHasCheckin);

        checkin.getEventHasCheckins().add(eventHasCheckin);
        checkinDAO.edit(checkin);

        event.getEventHasCheckins().add(eventHasCheckin);
        event.setNcheckin(event.getNcheckin()+1);
        eventDAO.edit(event);

        return Response.ok(responseDto).build();
    }
}
