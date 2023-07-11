/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.catalog.rest.l1;

import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.BrandMapperImpl;
import com.stagedriving.modules.commons.mapper.ColorMapperImpl;
import com.stagedriving.modules.commons.mapper.ItemMapperImpl;
import com.stagedriving.modules.commons.utils.event.ItemUtils;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemCatalogResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemCatalogResource.class);

    @Inject
    ItemMapperImpl itemMapper;
    @Inject
    BrandMapperImpl brandMapper;
    @Inject
    ItemDAO itemDAO;
    @Inject
    CatalogDAO catalogDAO;
    @Inject
    CatalogHasItemDAO catalogHasItemDAO;
    @Inject
    ItemCategoryDAO itemCategoryDAO;
    @Inject
    ItemHasColorDAO itemHasColorDAO;
    @Inject
    CatalogHasBrandDAO catalogHasBrandDAO;
    @Inject
    ItemHasCategoryDAO itemHasCategoryDAO;
    @Inject
    BrandDAO brandDAO;
    @Inject
    ColorDAO colorDAO;
    @Inject
    ItemUtils itemUtils;
    @Inject
    ColorMapperImpl colorMapper;
    @Inject
    ItemHasBrandDAO itemHasBrandDAO;

    private AppConfiguration configuration;

    @Inject
    public ItemCatalogResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    public Response createItem(String catalogId, ItemDTO itemDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ITEM_CREATE);

        Catalog catalog = catalogDAO.findByUid(catalogId);

        Item item = itemMapper.itemDtoToItem(itemDto);
        item.setUid(TokenUtils.generateUid());
        item.setCreated(new Date());
        item.setModified(new Date());
        item.setVisible(true);
        item.setNcomment(0);
        item.setNlike(0);
        item.setStatus(StgdrvData.ItemStatus.APPROVED);

        //TODO refactor
        if (itemDto.getName() == null && itemDto.getTag() != null && itemDto.getTag().equalsIgnoreCase(StgdrvData.ItemTag.GHOST))
            item.setName(TokenUtils.generateUid());

        itemDAO.create(item);

        if (itemDto.getTag() == null || (itemDto.getTag() != null && !itemDto.getTag().equalsIgnoreCase(StgdrvData.ItemTag.GHOST))) {

            item.setTag(StgdrvData.ItemTag.STANDARD);
            itemDAO.edit(item);

            //Categories
            itemDto.getCategories().stream().forEach((itemCategoryDto) -> {

                ItemCategory itemCategory = itemCategoryDAO.findByUid(itemCategoryDto.getId());
                Preconds.checkNotNull(itemCategory,
                        new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_CATEGORY_DOES_NOT_EXIST));

                ItemHasCategory itemHasCategory = new ItemHasCategory();
                itemHasCategory.setUid(TokenUtils.generateUid());
                itemHasCategory.setCreated(new Date());
                itemHasCategory.setModified(new Date());
                itemHasCategory.setVisible(true);
                itemHasCategory.setItem(item);
                itemHasCategory.setItemCategory(itemCategory);

                itemHasCategoryDAO.create(itemHasCategory);
                item.getItemHasCategories().add(itemHasCategory);
                itemDAO.edit(item);
            });

            //Colors
            itemDto.getColors().stream().forEach((colorDto) -> {

                Color color = colorDAO.findByUid(colorDto.getId());
                Preconds.checkNotNull(color,
                        new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.COLOR_NAME_NULL));

                ItemHasColorId itemHasColorId = new ItemHasColorId();
                itemHasColorId.setColorId(color.getId());
                itemHasColorId.setItemId(item.getId());
                ItemHasColor itemHasColor = new ItemHasColor();
                itemHasColor.setId(itemHasColorId);
                itemHasColor.setCreated(new Date());
                itemHasColor.setModified(new Date());
                itemHasColor.setVisible(true);
                itemHasColor.setItem(item);
                itemHasColor.setColor(color);

                itemHasColorDAO.create(itemHasColor);
                item.getItemHasColors().add(itemHasColor);
                itemDAO.edit(item);
            });
        }

        //Brands
        itemDto.getBrands().stream().forEach((brandDto) -> {

            Brand brand = brandDAO.findByUid(brandDto.getId());
            Preconds.checkNotNull(brand,
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DOES_NOT_EXIST));

            ItemHasBrandId itemHasBrandId = new ItemHasBrandId();
            itemHasBrandId.setBrandId(brand.getId());
            itemHasBrandId.setItemId(item.getId());
            ItemHasBrand itemHasBrand = new ItemHasBrand();
            itemHasBrand.setId(itemHasBrandId);
            itemHasBrand.setCreated(new Date());
            itemHasBrand.setModified(new Date());
            itemHasBrand.setVisible(true);
            itemHasBrand.setBrand(brand);
            itemHasBrand.setItem(item);

            itemHasBrandDAO.create(itemHasBrand);

            item.getItemHasBrands().add(itemHasBrand);
            itemDAO.edit(item);

            brand.getItemHasBrands().add(itemHasBrand);
            brandDAO.edit(brand);
        });

        //Add item to the catalog
        CatalogHasItemId catalogHasItemId = new CatalogHasItemId();
        catalogHasItemId.setCatalogId(catalog.getId());
        catalogHasItemId.setItemId(item.getId());
        CatalogHasItem catalogHasItem = new CatalogHasItem();
        catalogHasItem.setId(catalogHasItemId);
        catalogHasItem.setVisible(true);
        catalogHasItem.setCreated(new Date());
        catalogHasItem.setModified(new Date());

        catalogHasItemDAO.create(catalogHasItem);

        item.getCatalogHasItems().add(catalogHasItem);
        itemDAO.edit(item);

        catalog.getCatalogHasItems().add(catalogHasItem);
        catalogDAO.edit(catalog);

        //Add brand to the catalog (if does not belong)
        itemDto.getBrands().stream().forEach((brandDto) -> {

            Brand brand = brandDAO.findByUid(brandDto.getId());
            CatalogHasBrandId catalogHasBrandId = new CatalogHasBrandId();
            catalogHasBrandId.setBrandId(brand.getId());
            catalogHasBrandId.setCatalogId(catalog.getId());

            if (catalogHasBrandDAO.findById(catalogHasBrandId) == null) {
                catalogHasBrandId = new CatalogHasBrandId();
                catalogHasBrandId.setBrandId(brand.getId());
                catalogHasBrandId.setCatalogId(catalog.getId());

                CatalogHasBrand catalogHasBrand = new CatalogHasBrand();
                catalogHasBrand.setId(catalogHasBrandId);
                catalogHasBrand.setBrand(brand);
                catalogHasBrand.setCatalog(catalog);
                catalogHasBrand.setCreated(new Date());
                catalogHasBrand.setModified(new Date());
                catalogHasBrand.setVisible(true);
                catalogHasBrandDAO.create(catalogHasBrand);

                catalog.getCatalogHasBrands().add(catalogHasBrand);
                catalogDAO.edit(catalog);

                brand.getCatalogHasBrands().add(catalogHasBrand);
                brandDAO.edit(brand);
            }
        });

        //Update the status of the ghost item (if exists)
        if (itemDto.getTag() == null || (itemDto.getTag() != null && !itemDto.getTag().equalsIgnoreCase(StgdrvData.ItemTag.GHOST))) {

            //Find ghost item
            Item ghost = null;
            for (CatalogHasItem catalogHasItem1 : catalog.getCatalogHasItems()) {
                if (catalogHasItem1.getItem() != null && catalogHasItem1.getItem().getTag() != null &&
                        catalogHasItem1.getItem().getTag().equalsIgnoreCase(StgdrvData.ItemTag.GHOST)) {

                    ghost = catalogHasItem1.getItem();
                    break;
                }
            }

            if (ghost != null) {
                for (BrandDTO brandDto : itemDto.getBrands()) {
                    Brand brand = brandDAO.findByUid(brandDto.getId());
                    Preconds.checkNotNull(brand,
                            new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DOES_NOT_EXIST));

                    //Add this brand to the ghost item (if the ghost does not have this brand)
                    ItemHasBrand itemHasBrand = itemHasBrandDAO.findById(new ItemHasBrandId(ghost.getId(), brand.getId()));
                    if (itemHasBrand == null) {
                        ItemHasBrandId itemHasBrandNewId = new ItemHasBrandId(ghost.getId(), brand.getId());
                        ItemHasBrand itemHasBrandNew = new ItemHasBrand();
                        itemHasBrandNew.setId(itemHasBrandNewId);
                        itemHasBrandNew.setCreated(new Date());
                        itemHasBrandNew.setModified(new Date());
                        itemHasBrandNew.setVisible(true);
                        itemHasBrandNew.setItem(ghost);
                        itemHasBrandNew.setBrand(brand);

                        itemHasBrandDAO.create(itemHasBrandNew);
                    }
                }
            }
        }

        responseDto.setId(item.getUid());

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{itemId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing item",
            notes = "Modify existing item",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyItem(@Restricted(required = true) Account me,
                               @ApiParam(required = true) ItemDTO itemDto,
                               @PathParam("itemId") String itemId) {

        Item itemOld = itemDAO.findByUid(itemId);
        Item itemNew = itemMapper.itemDtoToItem(itemDto);
        itemUtils.merge(itemOld, itemNew);

        itemDAO.edit(itemOld);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(itemOld.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ITEM_MODIFIED);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    public Response getItemOfCatalog(String catalogId, String itemId) {

        ArrayList<ItemCategoryDTO> categoryDtos = new ArrayList<>();

        Item item = itemDAO.findByUid(itemId);
        ItemDTO itemDto = itemMapper.itemToItemDto(item);

        List<ItemHasCategory> itemHasCategories = itemHasCategoryDAO.findByItem(item);

//        Preconds.checkState(itemHasCategories != null && itemHasCategories.size() > 0,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INTERNAL_ERROR, StgdrvMessage.MessageError.ITEM_CATEGORY_DOES_NOT_EXIST));

        //Fill categories
        if (itemHasCategories != null && itemHasCategories.size() > 0) {
            itemHasCategories.stream().forEach((itemHasCategory) -> {
                ItemCategory itemCategory = itemHasCategory.getItemCategory();
                if (itemCategory != null) {
                    ItemCategoryDTO itemCategoryDto = new ItemCategoryDTO();
                    itemCategoryDto.setId(itemCategory.getUid());
                    itemCategoryDto.setName(itemCategory.getName());
                    categoryDtos.add(itemCategoryDto);
                }
            });
        }

        //Fill brands
        ArrayList<BrandDTO> brandDtos = new ArrayList<BrandDTO>();
        if (item.getItemHasBrands() != null && item.getItemHasBrands().size() > 0) {
            item.getItemHasBrands().stream().forEach((itemHasBrand) -> {
                brandDtos.add(brandMapper.brandToBrandDto(itemHasBrand.getBrand()));
            });

            itemDto.setBrands(brandDtos);
        }

        //Fill colors
        ArrayList<ColorDTO> colorDtos = new ArrayList<ColorDTO>();
        if (item.getItemHasColors() != null && !item.getItemHasColors().isEmpty()) {
            item.getItemHasColors().stream().forEach((itemHasColor) -> {
                colorDtos.add(colorMapper.colorToColorDto(itemHasColor.getColor()));
            });

            itemDto.setColors(colorDtos);
        }

        return Response.ok(itemDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    public Response getItemsOfCatalog(String catalogId, int page, int limit,
                                      @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size) {

        PagedResults<ItemDTO> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        List<ItemDTO> itemDtos = new ArrayList<>();

        Catalog catalog = catalogDAO.findByUid(catalogId);
        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        List<CatalogHasItem> catalogHasItems = catalogHasItemDAO.findByCatalogByPageByLimit(catalog, page, limit, results);

        if (catalogHasItems != null && catalogHasItems.size() > 0) {
            catalogHasItems.stream().forEach((catalogHasItem) -> {
                Item item = catalogHasItem.getItem();
                if (item != null && item.getVisible()) {
                    ItemDTO itemDto = itemMapper.itemToItemDto(item);

                    //Fill categories
                    ArrayList<ItemCategoryDTO> categoryDtos = new ArrayList<ItemCategoryDTO>();

                    if (item.getItemHasCategories() != null && item.getItemHasCategories().size() > 0) {
                        item.getItemHasCategories().stream().forEach((itemHasCategory) -> {

                            ItemCategoryDTO itemCategory = new ItemCategoryDTO();
                            itemCategory.setId(itemHasCategory.getItemCategory().getUid());
                            itemCategory.setName(itemHasCategory.getItemCategory().getName());
                            itemCategory.setTag(itemHasCategory.getItemCategory().getTag());
                            itemCategory.setDescription(itemHasCategory.getItemCategory().getDescription());
                            itemCategory.setPosition(itemHasCategory.getItemCategory().getPosition());

                            categoryDtos.add(itemCategory);
                        });

                        itemDto.setCategories(categoryDtos);
                    }

                    //Fill brands
                    ArrayList<BrandDTO> brandDtos = new ArrayList<BrandDTO>();
                    if (item.getItemHasBrands() != null && item.getItemHasBrands().size() > 0) {
                        item.getItemHasBrands().stream().forEach((itemHasBrand) -> {
                            brandDtos.add(brandMapper.brandToBrandDto(itemHasBrand.getBrand()));
                        });

                        itemDto.setBrands(brandDtos);
                    }

                    //Fill colors
                    ArrayList<ColorDTO> colorDtos = new ArrayList<ColorDTO>();
                    if (item.getItemHasColors() != null && !item.getItemHasColors().isEmpty()) {
                        item.getItemHasColors().stream().forEach((itemHasColor) -> {
                            colorDtos.add(colorMapper.colorToColorDto(itemHasColor.getColor()));
                        });

                        itemDto.setColors(colorDtos);
                    }

                    //Add only if item is visible
                    if (item.getVisible()) itemDtos.add(itemDto);
                }
            });
        }

        if (results != null) {
            results.setData(itemDtos);
            return Response.ok(results).build();
        }

        return Response.ok(itemDtos).build();
    }
}
