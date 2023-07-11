/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.event.rest.l1;

import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.CatalogDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.CatalogDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.EventHasCatalogDAO;
import com.stagedriving.modules.commons.ds.entities.Catalog;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.EventHasCatalog;
import com.stagedriving.modules.commons.mapper.CatalogMapperImpl;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
public class CatalogEventResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CatalogEventResource.class);

    @Inject
    CatalogMapperImpl catalogMapper;
    @Inject
    EventDAO eventDAO;
    @Inject
    CatalogDAO catalogDAO;
    @Inject
    EventHasCatalogDAO eventHasCatalogDAO;

    private AppConfiguration configuration;

    @Inject
    public CatalogEventResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response createCatalog(String eventId, CatalogDTO catalogDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CATALOG_CREATE);

        Event event = eventDAO.findByUid(eventId);

        Catalog catalog = catalogMapper.catalogDtoToCatalog(catalogDto);
        catalog.setUid(TokenUtils.generateUid());
        catalog.setCreated(new Date());
        catalog.setModified(new Date());
        catalog.setVisible(true);

        eventDAO.create(event);

        EventHasCatalog eventHasCatalog = new EventHasCatalog();
        eventHasCatalog.setCatalogid(catalog.getId());
        eventHasCatalog.setVisible(true);
        eventHasCatalog.setCreated(new Date());
        eventHasCatalog.setModified(new Date());

        eventHasCatalogDAO.create(eventHasCatalog);

        event.getEventHasCatalogs().add(eventHasCatalog);
        eventDAO.edit(event);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    public Response getCatalogOfEvent(String catalogId) {

        return Response.ok(catalogMapper.catalogToCatalogDto(catalogDAO.findByUid(catalogId))).build();
    }
}
