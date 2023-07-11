/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.catalog.rest.l1;

import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.BrandDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.CatalogDAO;
import com.stagedriving.modules.commons.ds.daos.CatalogHasBrandDAO;
import com.stagedriving.modules.commons.ds.entities.Brand;
import com.stagedriving.modules.commons.ds.entities.Catalog;
import com.stagedriving.modules.commons.ds.entities.CatalogHasBrand;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.mapper.BrandMapperImpl;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class BrandCatalogResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BrandCatalogResource.class);

    @Inject
    BrandMapperImpl brandMapper;
    @Inject
    CatalogHasBrandDAO catalogHasBrandDAO;
    @Inject
    CatalogDAO catalogDAO;

    private AppConfiguration configuration;

    @Inject
    public BrandCatalogResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    public Response getBrandOfCatalog(String catalogId,
                                      @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size) {

        Catalog catalog = catalogDAO.findByUid(catalogId);
        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        List<CatalogHasBrand> catalogHasBrands = catalogHasBrandDAO.findByCatalog(catalog);

        ArrayList<BrandDTO> brandDtos = new ArrayList<>();

        if (catalogHasBrands != null && catalogHasBrands.size() > 0) {
            catalogHasBrands.stream().forEach((catalogHasBrand) -> {
                Brand brand = catalogHasBrand.getBrand();
                if (brand != null) {
                    brandDtos.add(brandMapper.brandToBrandDto(brand));
                }
            });
        }

        return Response.ok(brandDtos).build();
    }
}
