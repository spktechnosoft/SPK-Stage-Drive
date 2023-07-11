/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.catalog.rest.root;

import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.BrandDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.BrandDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.ItemDAO;
import com.stagedriving.modules.commons.ds.daos.ItemHasBrandDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Brand;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.BrandMapperImpl;
import com.stagedriving.modules.commons.utils.brand.BrandUtils;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/brands")
@Api(value = "brands", description = "Brand resource")
public class BrandResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BrandResource.class);

    @Inject
    BrandMapperImpl brandMapper;
    @Inject
    BrandDAO brandDAO;
    @Inject
    BrandUtils brandUtils;
    @Inject
    ItemDAO itemDAO;
    @Inject
    EventDAO eventDAO;
    @Inject
    ItemHasBrandDAO itemHasBrandDAO;

    private AppConfiguration configuration;

    @Inject
    public BrandResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new brand",
            notes = "Add new brand. Mandatory fields are: (1) name. " +
                    "Brand is subject to approval process (status in DTO)",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createBrand(@Restricted(required = true) Account me,
                                @ApiParam(required = true) BrandDTO brandDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BRAND_CREATE);

        Preconds.checkNotNull(brandDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DTO_NULL));

        Brand brand = null;
        if (brandDto.getId() != null) {
            brand = brandDAO.findByUid(brandDto.getId());
            Preconds.checkNotNull(brand,
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DOES_NOT_EXIST));
        } else {

            Preconds.checkNotNull(brandDto.getName(),
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_NAME_NULL));

            brand = brandMapper.brandDtoToBrand(brandDto);
            brand.setUid(TokenUtils.generateUid());
            brand.setCreated(new Date());
            brand.setModified(new Date());
            brand.setStatus(StgdrvData.BrandStatus.PENDING);
            brand.setVisible(true);
            brand.setBase(false);

            brandDAO.create(brand);
        }

        responseDto.setId(brand.getUid());

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{brandId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing brand",
            notes = "Modify existing brand",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyBrand(@Restricted(required = true) Account me,
                                @ApiParam(required = true) BrandDTO brandDto,
                                @PathParam("brandId") String brandId) {

        Preconds.checkNotNull(brandDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DTO_NULL));

        Brand brandOld = brandDAO.findByUid(brandId);
        Brand brandNew = brandMapper.brandDtoToBrand(brandDto);
        brandUtils.merge(brandOld, brandNew);

        if (brandOld.getStatus().equalsIgnoreCase(StgdrvData.BrandStatus.PENDING) && brandNew.getStatus().equalsIgnoreCase(StgdrvData.BrandStatus.APPROVED)) {
            brandOld.setBase(true);
        } else if (brandOld.getStatus().equalsIgnoreCase(StgdrvData.BrandStatus.APPROVED) && brandNew.getStatus().equalsIgnoreCase(StgdrvData.BrandStatus.REJECTED)) {
            brandOld.setBase(false);
        }

        brandDAO.edit(brandOld);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(brandOld.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BRAND_MODIFIED);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve brand",
            notes = "Retrieves brand",
            response = BrandDTO.class,
            responseContainer = "List")
    //@Metered
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getBrands(@QueryParam("name") String name,
                              @QueryParam("base") Boolean base,
                              @QueryParam("limit") String limit,
                              @QueryParam("page") String page,
                              @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size) {

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = 100;
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        PagedResults<BrandDTO> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        List<Brand> brands;
        if (name != null) {
            brands = brandDAO.findBrandByName(name, pageQuery, limitQuery);
        } else if (base != null && base) {
            brands = brandDAO.findBrandByBase(true);
        } else {
            brands = brandDAO.findBrandByVisible(true, pageQuery, limitQuery, results);
        }

        List<BrandDTO> brandDtos = brandMapper.brandsToBrandDtos(brands);

        Collections.sort(brandDtos, new Comparator<BrandDTO>() {
            public int compare(BrandDTO result1, BrandDTO result2) {
                if (result1.getName() != null && result2.getName() != null) {
                    return result1.getName().compareToIgnoreCase(result2.getName());
                } else if (result1.getName() == null && result2.getName() != null) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        if (results != null) {
            results.setData(brandDtos);
            return Response.ok(results).build();
        }

        return Response.ok(brandDtos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{brandId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve brand",
            notes = "Retrieve brand",
            response = BrandDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getBrand(@PathParam("brandId") String brandId) {

        return Response.ok(brandMapper.brandToBrandDto(brandDAO.findByUid(brandId))).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{brandId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing brand",
            notes = "Delete existing brand",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteBrand(@PathParam("brandId") String brandId) {

        Brand brand = brandDAO.findByUid(brandId);
        Preconds.checkNotNull(brand,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DOES_NOT_EXIST));

        brand.setVisible(false);
        brandDAO.edit(brand);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(brandId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BRAND_DELETE);

        return Response.ok(responseDto).build();
    }
}
