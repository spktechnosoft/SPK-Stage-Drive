/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.catalog.rest.root;

import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.BrandDTO;
import com.stagedriving.commons.v1.resources.CatalogDTO;
import com.stagedriving.commons.v1.resources.ItemDTO;
import com.stagedriving.modules.catalog.rest.l1.BrandCatalogResource;
import com.stagedriving.modules.catalog.rest.l1.ItemCatalogResource;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.BrandMapperImpl;
import com.stagedriving.modules.commons.mapper.CatalogMapperImpl;
import com.stagedriving.modules.commons.mapper.ItemMapperImpl;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/catalogs")
@Api(value = "catalogs", description = "Catalog resource")
public class CatalogResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CatalogResource.class);

    @Inject
    CatalogMapperImpl catalogMapper;
    @Inject
    ItemMapperImpl itemMapper;
    @Inject
    CatalogDAO catalogDAO;
    @Inject
    BrandDAO brandDAO;
    @Inject
    CatalogHasItemDAO catalogHasItemDAO;
    @Inject
    ItemDAO itemDAO;
    @Inject
    ItemCatalogResource itemCatalogResource;
    @Inject
    BrandCatalogResource brandCatalogResource;
    @Inject
    CatalogHasBrandDAO catalogHasBrandDAO;
    @Inject
    BrandMapperImpl brandMapper;

    private AppConfiguration configuration;

    @Inject
    public CatalogResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new catalog",
            notes = "Add new catalog. Mandatory fields are: (1) name",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createCatalog(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) CatalogDTO catalogDTO) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CATALOG_CREATE);

        Preconds.checkNotNull(catalogDTO,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DTO_NULL));

        Preconds.checkNotNull(catalogDTO.getName(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_NAME_NULL));

        Catalog catalog = catalogMapper.catalogDtoToCatalog(catalogDTO);
        catalog.setUid(TokenUtils.generateUid());
        catalog.setCreated(new Date());
        catalog.setModified(new Date());
        catalog.setVisible(true);

        catalogDAO.create(catalog);

        responseDto.setId(catalog.getUid());

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing catalog",
            notes = "Modify existing catalog",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyCatalog(@PathParam("catalogId") String catalogId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(catalogId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve catalog",
            notes = "Retrieves catalog",
            response = CatalogDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getCatalogs(@QueryParam("limit") String limit,
                                @QueryParam("page") String page,
                                @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size) {

        PagedResults<CatalogDTO> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        List<CatalogDTO> catalogs = catalogMapper.catalogsToCatalogDtos(catalogDAO.findAll());

        if (results != null) {
            results.setSize(catalogs.size());
            results.setData(catalogs);
            return Response.ok(results).build();
        }

        return Response.ok(catalogs).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve catalog",
            notes = "Retrieve catalog",
            response = CatalogDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getCatalog(@PathParam("catalogId") String catalogId, @QueryParam("full") @DefaultValue("false") boolean full) {

        CatalogDTO catalogDto = new CatalogDTO();
        ArrayList<BrandDTO> brandDtos = new ArrayList<>();
        ArrayList<ItemDTO> itemDtos = new ArrayList<>();

        Catalog catalog = catalogDAO.findByUid(catalogId);
        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        catalogDto = catalogMapper.catalogToCatalogDto(catalog);

        if (full) {
            if (catalog.getCatalogHasBrands() != null && !catalog.getCatalogHasBrands().isEmpty()) {
                catalog.getCatalogHasBrands().stream().forEach((catalogHasBrand) -> {
                    Brand brand = catalogHasBrand.getBrand();
                    if (brand != null) {
                        brandDtos.add(brandMapper.brandToBrandDto(brand));
                    }
                });
                catalogDto.setBrands(brandDtos);
            }
            if (catalog.getCatalogHasItems() != null && !catalog.getCatalogHasItems().isEmpty()) {
                catalog.getCatalogHasItems().stream().forEach((catalogHasItem) -> {
                    Item item = catalogHasItem.getItem();
                    if (item != null) {
                        itemDtos.add(itemMapper.itemToItemDto(item));
                    }
                });
                catalogDto.setItems(itemDtos);
            }
        }

        return Response.ok(catalogDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing catalog",
            notes = "Delete existing catalog",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteCatalog(@PathParam("catalogId") String catalogId) {

        Catalog catalog = catalogDAO.findByUid(catalogId);
        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        catalog.setVisible(false);
        catalogDAO.edit(catalog);

        catalog.getCatalogHasItems().stream().forEach((catalogHasItem) -> {
            Item item = catalogHasItem.getItem();
            if (item != null) {
                item.setVisible(false);
                itemDAO.edit(item);
            }
            catalogHasItem.setVisible(false);
            catalogHasItemDAO.edit(catalogHasItem);
        });

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(catalogId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.CATALOG_DELETE);

        return Response.ok(responseDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}/items")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new item to the catalog",
            notes = "Add new item to the catalog",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response addItemToCalaog(@Restricted(required = true) Account me,
                                    @PathParam("catalogId") String catalogId,
                                    @ApiParam(required = true) ItemDTO itemDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ITEM_CREATE);

        Preconds.checkNotNull(itemDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Preconds.checkNotNull(catalogId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DTO_NULL));

        Catalog catalog = catalogDAO.findByUid(catalogId);

        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        return itemCatalogResource.createItem(catalogId, itemDto);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}/items/{itemId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new item to the catalog",
            notes = "Add new item to the catalog",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyItemOfCalaog(@Restricted(required = true) Account me,
                                       @PathParam("catalogId") String catalogId,
                                       @PathParam("itemId") String itemId,
                                       @ApiParam(required = true) ItemDTO itemDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ITEM_MODIFIED);

        Preconds.checkNotNull(catalogId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DTO_NULL));

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Preconds.checkNotNull(itemDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Catalog catalog = catalogDAO.findByUid(catalogId);
        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        Item item = itemDAO.findByUid(itemId);
        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        CatalogHasItemId catalogHasItemId = new CatalogHasItemId();
        catalogHasItemId.setCatalogId(catalog.getId());
        catalogHasItemId.setItemId(item.getId());

        CatalogHasItem catalogHasItem = catalogHasItemDAO.findById(catalogHasItemId);
        Preconds.checkNotNull(catalogHasItem,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_BELONG));

        return itemCatalogResource.modifyItem(me, itemDto, itemId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}/items")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve catalog",
            notes = "Retrieve catalog",
            response = CatalogDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getItemsOfCatalog(@Restricted(required = false) Account me,
                                      @PathParam("catalogId") String catalogId,
                                      @QueryParam("limit") String limit,
                                      @QueryParam("page") String page,
                                      @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size) {

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = 200;
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        Catalog catalog = catalogDAO.findByUid(catalogId);

        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        return itemCatalogResource.getItemsOfCatalog(catalogId, pageQuery, limitQuery, size);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}/items/{itemId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve catalog",
            notes = "Retrieve catalog",
            response = CatalogDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getItemOfCatalog(@Restricted(required = true) Account me,
                                     @PathParam("catalogId") String catalogId,
                                     @PathParam("itemId") String itemId) {

        Catalog catalog = catalogDAO.findByUid(catalogId);
        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        Item item = itemDAO.findByUid(itemId);
        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

//        Preconds.checkState(!item.getVisible(),
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        return itemCatalogResource.getItemOfCatalog(catalogId, itemId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}/brands")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new brand to the catalog",
            notes = "Add new brand to the catalog",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response addBrandToCalalog(@Restricted(required = true) Account me,
                                      @PathParam("catalogId") String catalogId,
                                      @ApiParam(required = true) BrandDTO brandDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BRAND_CREATE);

        Preconds.checkNotNull(brandDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DTO_NULL));

        Preconds.checkNotNull(brandDto.getId(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_ID_NULL));

        Preconds.checkNotNull(catalogId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_ID_NULL));

        Brand brand = brandDAO.findByUid(brandDto.getId());
        Catalog catalog = catalogDAO.findByUid(catalogId);

        Preconds.checkNotNull(brand,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DOES_NOT_EXIST));

        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        CatalogHasBrandId catalogHasBrandId = new CatalogHasBrandId();
        catalogHasBrandId.setCatalogId(catalog.getId());
        catalogHasBrandId.setBrandId(brand.getId());
        catalogHasBrandDAO.findById(catalogHasBrandId);

        Preconds.checkState(catalog != null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_CATALOG_RELATIONSHIP_ALREADY_EXISTS));

        CatalogHasBrand catalogHasBrand = new CatalogHasBrand();
        catalogHasBrand.setId(catalogHasBrandId);
        catalogHasBrand.setBrand(brand);
        catalogHasBrand.setCatalog(catalog);
        catalogHasBrand.setCreated(new Date());
        catalogHasBrand.setModified(new Date());
        catalogHasBrand.setVisible(true);

        catalogHasBrandDAO.create(catalogHasBrand);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{catalogId}/brands")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve catalog brands",
            notes = "Retrieve catalog brands",
            response = BrandDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getBrandsOfCatalog(@Restricted(required = false) Account me,
                                       @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size,
                                       @PathParam("catalogId") String catalogId) {

        return brandCatalogResource.getBrandOfCatalog(catalogId, size);
    }
}
