/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.catalog.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.inject.Inject;
import com.stagedriving.modules.commons.utils.DateUtils;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.catalog.rest.l1.ItemCatalogResource;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.*;
import com.stagedriving.modules.commons.utils.event.ItemUtils;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/items")
@Api(value = "items", description = "Items resources")
public class ItemResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemResource.class);

    @Inject
    ItemMapper itemMapper;
    @Inject
    ItemDAO itemDAO;
    @Inject
    BrandDAO brandDAO;
    @Inject
    ItemUtils itemUtils;
    @Inject
    CatalogDAO catalogDAO;
    @Inject
    ItemHasCategoryDAO itemHasCategoryDAO;
    @Inject
    ItemCategoryDAO itemCategoryDAO;
    @Inject
    BrandMapperImpl brandMapper;
    @Inject
    ColorMapperImpl colorMapper;
    @Inject
    ItemHasBrandDAO itemHasBrandDAO;
    @Inject
    ColorDAO colorDAO;
    @Inject
    ItemHasColorDAO itemHasColorDAO;
    @Inject
    ItemHasActionDAO itemHasActionDAO;
    @Inject
    EventMapper eventMapper;
    @Inject
    AccountMapper accountMapper;
    @Inject
    AccountDAO accountDAO;
    @Inject
    EventDAO eventDAO;
    @Inject
    ItemCatalogResource itemCatalogResource;

    private AppConfiguration configuration;

    @Inject
    public ItemResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new item",
            notes = "Add new item to the catalog. Mandatory fiels are: (1) CatalogDTO in ItemDTO, (2) name, (3) category",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response createItem(@Restricted(required = true) Account me,
                               @ApiParam(required = true) ItemDTO itemDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ITEM_CREATE);

        Preconds.checkNotNull(itemDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Preconds.checkNotNull(itemDto.getCatalogs().get(0).getId(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DTO_NULL));

        if (itemDto.getTag() == null || (itemDto.getTag() != null && !itemDto.getTag().equalsIgnoreCase(StgdrvData.ItemTag.GHOST))) {
            Preconds.checkState(itemDto.getCategories() != null && itemDto.getCategories().size() > 0,
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_CATEGORIES_NULL));

            Preconds.checkNotNull(itemDto.getColors() == null,
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_COLORS_DTO_NULL));

            Preconds.checkNotNull(itemDto.getColors().isEmpty(),
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_COLORS_DTO_NULL));

            Preconds.checkNotNull(itemDto.getPicture(),
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_PICTURE_NULL));
        }

        Preconds.checkNotNull(itemDto.getBrands() == null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_BRANDS_DTO_NULL));

        Preconds.checkNotNull(itemDto.getBrands().isEmpty(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_BRANDS_DTO_NULL));

        if (itemDto.getOutfit() != null && itemDto.getOutfit() && itemDto.getBrands().size() < 2)
            new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_OUTFIT_BRANDS);

        if (itemDto.getOutfit() != null && itemDto.getOutfit() && itemDto.getColors().size() < 2)
            new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_OUTFIT_COLORS);

        Catalog catalog = catalogDAO.findByUid(itemDto.getCatalogs().get(0).getId());

        Preconds.checkNotNull(catalog,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

        return itemCatalogResource.createItem(catalog.getUid(), itemDto);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}")
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

        Preconds.checkNotNull(itemDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Preconds.checkNotNull(itemDto.getCategories(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_CATEGORIES_NULL));

        Preconds.checkState(itemDto.getCategories().size() > 0,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_CATEGORIES_NULL));

        Preconds.checkNotNull(itemDto.getColors() == null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_COLORS_DTO_NULL));

        Preconds.checkNotNull(itemDto.getColors().isEmpty(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_COLORS_DTO_NULL));

        Preconds.checkNotNull(itemDto.getBrands() == null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_BRANDS_DTO_NULL));

        Preconds.checkNotNull(itemDto.getBrands().isEmpty(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_BRANDS_DTO_NULL));

        if (itemDto.getOutfit() != null && itemDto.getOutfit() && itemDto.getBrands().size() < 2)
            new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_OUTFIT_BRANDS);

        if (itemDto.getOutfit() != null && itemDto.getOutfit() && itemDto.getColors().size() < 2)
            new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_OUTFIT_COLORS);

        Item itemOld = itemDAO.findByUid(itemId);
        Item itemNew = itemMapper.itemDtoToItem(itemDto);
        itemUtils.merge(itemOld, itemNew);

        itemDAO.edit(itemOld);

        //Add categories to the item
        if (itemDto.getCategories() != null && itemDto.getCategories().size() > 0) {
            itemDto.getCategories().stream().forEach((itemCategoryDto) -> {

                ItemCategory itemCategory = itemCategoryDAO.findByUid(itemCategoryDto.getId());

                ItemHasCategory itemHasCategory = itemHasCategoryDAO.findByItemCategoryByItem(itemCategory, itemOld);
                if (itemHasCategory == null) {
                    ItemHasCategory itemHasCategoryNew = new ItemHasCategory();
                    itemHasCategoryNew.setUid(TokenUtils.generateUid());
                    itemHasCategoryNew.setCreated(new Date());
                    itemHasCategoryNew.setModified(new Date());
                    itemHasCategoryNew.setVisible(true);
                    itemHasCategoryNew.setItem(itemOld);
                    itemHasCategoryNew.setItemCategory(itemCategory);

                    itemHasCategoryDAO.create(itemHasCategoryNew);
                }
            });
        }
        //Remove categories from the item
        List<ItemHasCategory> itemHasCategories = itemHasCategoryDAO.findByItem(itemOld);
        if (itemHasCategories != null && itemHasCategories.size() > 0) {
            itemHasCategories.stream().forEach((itemHasCategory) -> {
                Boolean isPresentInDto = false;
                for (ItemCategoryDTO categoryDto : itemDto.getCategories()) {
                    if (categoryDto.getId().equalsIgnoreCase(itemHasCategory.getItemCategory().getUid())) {
                        isPresentInDto = true;
                        break;
                    }
                }
                if (!isPresentInDto) itemHasCategoryDAO.delete(itemHasCategory);
            });
        }

        //Add brands to the item
        if (itemDto.getBrands() != null && itemDto.getBrands().size() > 0) {
            itemDto.getBrands().stream().forEach((itemBrandDto) -> {

                Brand brand = brandDAO.findByUid(itemBrandDto.getId());

                ItemHasBrand itemHasBrand = itemHasBrandDAO.findById(new ItemHasBrandId(itemOld.getId(), brand.getId()));
                if (itemHasBrand == null) {
                    ItemHasBrandId itemHasBrandNewId = new ItemHasBrandId(itemOld.getId(), brand.getId());
                    ItemHasBrand itemHasBrandNew = new ItemHasBrand();
                    itemHasBrandNew.setId(itemHasBrandNewId);
                    itemHasBrandNew.setCreated(new Date());
                    itemHasBrandNew.setModified(new Date());
                    itemHasBrandNew.setVisible(true);
                    itemHasBrandNew.setItem(itemOld);
                    itemHasBrandNew.setBrand(brand);

                    itemHasBrandDAO.create(itemHasBrandNew);
                }
            });
        }
        //Remove brands from the item
        List<ItemHasBrand> itemHasBrands = itemHasBrandDAO.findByItem(itemOld);
        if (itemHasBrands != null && itemHasBrands.size() > 0) {
            itemHasBrands.stream().forEach((itemHasBrand) -> {
                Boolean isPresentInDto = false;
                for (BrandDTO colorDto : itemDto.getBrands()) {
                    if (colorDto.getId().equalsIgnoreCase(itemHasBrand.getBrand().getUid())) {
                        isPresentInDto = true;
                        break;
                    }
                }
                if (!isPresentInDto) itemHasBrandDAO.delete(itemHasBrand);
            });
        }

        //Add colors to the item
        if (itemDto.getColors() != null && itemDto.getColors().size() > 0) {
            itemDto.getColors().stream().forEach((itemColorDto) -> {

                Color color = colorDAO.findByUid(itemColorDto.getId());

                ItemHasColor itemHasColor = itemHasColorDAO.findById(new ItemHasColorId(itemOld.getId(), color.getId()));
                if (itemHasColor == null) {
                    ItemHasColorId itemHasColorNewId = new ItemHasColorId(itemOld.getId(), color.getId());
                    ItemHasColor itemHasColorNew = new ItemHasColor();
                    itemHasColorNew.setId(itemHasColorNewId);
                    itemHasColorNew.setCreated(new Date());
                    itemHasColorNew.setModified(new Date());
                    itemHasColorNew.setVisible(true);
                    itemHasColorNew.setItem(itemOld);
                    itemHasColorNew.setColor(color);

                    itemHasColorDAO.create(itemHasColorNew);
                }
            });
        }
        //Remove colors from the item
        List<ItemHasColor> itemHasColors = itemHasColorDAO.findByItem(itemOld);
        if (itemHasColors != null && itemHasColors.size() > 0) {
            itemHasColors.stream().forEach((itemHasColor) -> {
                Boolean isPresentInDto = false;
                for (ColorDTO colorDto : itemDto.getColors()) {
                    if (colorDto.getId().equalsIgnoreCase(itemHasColor.getColor().getUid())) {
                        isPresentInDto = true;
                        break;
                    }
                }
                if (!isPresentInDto) itemHasColorDAO.delete(itemHasColor);
            });
        }

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(itemOld.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ITEM_MODIFIED);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve item",
            notes = "Retrieves item",
            response = ItemDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getItems(@Restricted(required = true) Account me,
                             @ApiParam(name = "search", value = "Search text", required = false) @QueryParam("search") String search,
                             @ApiParam(name = "categories", value = "Categories specified in string as {cat1,cat2,..,catn} without graph symbols", required = false) @QueryParam("categories") String categories,
                             @ApiParam(name = "brands", value = "Brands specified in string as {brand1,brand2,..,brandn} without graph symbols", required = false) @QueryParam("brands") String brands,
                             @ApiParam(name = "colors", value = "Colors", required = false) @QueryParam("colors") String colors,
                             @ApiParam(name = "male", value = "Male", required = false) @QueryParam("male") Boolean male,
                             @ApiParam(name = "female", value = "Female", required = false) @QueryParam("female") Boolean female,
                             @ApiParam(name = "children", value = "Children", required = false) @QueryParam("children") Boolean children,
                             @ApiParam(name = "distance", value = "Distance in km (or miles) to get proximity events", required = false) @QueryParam("distance") String distanceParam,
                             @ApiParam(name = "distanceType", value = "Return distance type in m=miles or km=kilometers", allowableValues = "m,km", required = false) @QueryParam("distanceType") String distanceTypeParam,
                             @ApiParam(name = "coordinates", value = "Coordinates specified in string as {lat,lon} without graph symbols", required = false) @QueryParam("coordinates") String coordinatesParam,
                             @QueryParam("limit") String limit,
                             @QueryParam("page") String page) {

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = 1000;
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        List<Item> items = new ArrayList<>();
        HashMap<String, Item> itemsHash = new HashMap<>();

        List<ItemCategory> itemCategoriesQuery = new ArrayList<>();
        if (categories != null) {
            String[] categoriesSplitted = categories.split(",");
            if (categoriesSplitted.length == 0) {
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.WRONG_CATEGORIES_FORMAT);
            }
            for (String s : categoriesSplitted) {
                ItemCategory itemCategory = itemCategoryDAO.findByUid(s);
                Preconds.checkNotNull(itemCategory,
                        new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_CATEGORY_DOES_NOT_EXIST));

                itemCategoriesQuery.add(itemCategory);
            }
        }

        List<Brand> brandsQuery = new ArrayList<>();
        if (brands != null) {
            String[] brandsSplitted = brands.split(",");
            if (brandsSplitted.length == 0) {
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.WRONG_BRANDS_FORMAT);
            }
            for (String s : brandsSplitted) {
                Brand brandQuery = brandDAO.findByUid(s);
                Preconds.checkNotNull(brandQuery,
                        new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.WRONG_BRANDS_FORMAT));

                brandsQuery.add(brandQuery);
            }
        }

        List<Color> colorsQuery = new ArrayList<>();
        if (colors != null) {
            String[] colorsSplitted = colors.split(",");
            if (colorsSplitted.length == 0) {
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.WRONG_BRANDS_FORMAT);
            }
            for (String s : colorsSplitted) {
                Color colorQuery = colorDAO.findByUid(s);
                Preconds.checkNotNull(colorQuery,
                        new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.WRONG_BRANDS_FORMAT));

                colorsQuery.add(colorQuery);
            }
        }

        if (search != null || !itemCategoriesQuery.isEmpty() || !brandsQuery.isEmpty() || !colorsQuery.isEmpty() ||
                male != null || female != null || children != null) {

            List<Item> itemsBase = itemDAO.findByFilters(pageQuery, limitQuery, search, male, female, children);

            if (search != null && itemCategoriesQuery.isEmpty() && brandsQuery.isEmpty() && colorsQuery.isEmpty()) {
                itemCategoriesQuery = itemCategoryDAO.findLikeByName(search);
                brandsQuery = brandDAO.findBrandByName(search, 0, 1000);
                colorsQuery = colorDAO.findColorByName(search, 0, 1000);
            }
            if (itemsBase!=null && !itemsBase.isEmpty()) {
                for (Item item : itemsBase) {

                    boolean foundCategory = true;
                    if (itemCategoriesQuery != null && !itemCategoriesQuery.isEmpty()) {
                        foundCategory = false;
                        for (ItemCategory itemCategory : itemCategoriesQuery) {
                            if (itemUtils.itemHasThisCategory(itemCategory, item)) {
                                foundCategory = true;
                                break;
                            }
                        }
                    }

                    boolean foundColor = true;
                    if (colorsQuery != null && !colorsQuery.isEmpty()) {
                        foundColor = false;
                        for (Color color1 : colorsQuery) {
                            if (itemUtils.itemHasThisColor(color1, item)) {
                                foundColor = true;
                                break;
                            }
                        }
                    }

                    boolean foundBrand = true;
                    if (brandsQuery != null && !brandsQuery.isEmpty()) {
                        foundBrand = false;
                        for (Brand brand1 : brandsQuery) {
                            if (itemUtils.itemHasThisBrand(brand1, item)) {
                                foundBrand = true;
                                break;
                            }
                        }
                    }

                    if (foundCategory && foundColor &&foundBrand) {
                        if(!item.getTag().equalsIgnoreCase("ghost")) itemsHash.put(item.getUid(), item);
                    }
                }
            }


            items = new ArrayList<Item>(itemsHash.values());

        } else {
            items = itemDAO.findAll();
        }

        List<ItemDTO> itemDtos = itemMapper.itemsToItemDtos(items);

        return Response.ok(itemDtos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{itemId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve item",
            notes = "Retrieve item",
            response = ItemDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getItem(@Restricted(required = true) Account me,
                            @PathParam("itemId") String itemId) {

        ArrayList<BrandDTO> brandDtos = new ArrayList<>();
        ArrayList<ColorDTO> colorDtos = new ArrayList<>();
        ArrayList<ItemCategoryDTO> categorieDtos = new ArrayList<>();

        Item item = itemDAO.findByUid(itemId);
        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        final ItemDTO itemDto = itemMapper.itemToItemDto(item);

        //Categories
        if (item.getItemHasCategories() != null && !item.getItemHasCategories().isEmpty()) {
            item.getItemHasCategories().stream().forEach((itemHasCategories) -> {
                ItemCategory category = itemHasCategories.getItemCategory();
                if (category != null) {
                    ItemCategoryDTO categoryDto = new ItemCategoryDTO();
                    categoryDto.setId(category.getUid());
                    categoryDto.setName(category.getName());
                    categoryDto.setDescription(category.getDescription());
                    categoryDto.setTag(category.getTag());

                    categorieDtos.add(categoryDto);
                }
                itemDto.setCategories(categorieDtos);
            });
        }

        //Brands
        if (item.getItemHasBrands() != null && !item.getItemHasBrands().isEmpty()) {
            item.getItemHasBrands().stream().forEach((itemHasBrand) -> {
                Brand brand = itemHasBrand.getBrand();
                if (brand != null) {
                    brandDtos.add(brandMapper.brandToBrandDto(brand));
                }
                itemDto.setBrands(brandDtos);
            });
        }

        //Colors
        if (item.getItemHasColors() != null && !item.getItemHasColors().isEmpty()) {
            item.getItemHasColors().stream().forEach((itemHasColor) -> {
                Color color = itemHasColor.getColor();
                if (color != null) {
                    colorDtos.add(colorMapper.colorToColorDto(color));
                }
                itemDto.setColors(colorDtos);
            });
        }

        //Like
        itemDto.setLike(false);
        ItemHasAction itemHasLike = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.LIKEUP);
        if (itemHasLike != null)
            itemDto.setLike(true);

        //Bookmark
        itemDto.setBookmark(false);
        ItemHasAction itemHasBookmark = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.BOOKMARKUP);
        if (itemHasBookmark != null)
            itemDto.setBookmark(true);

        //Event
        //TODO
        EventDTO eventDto = new EventDTO();
//        EventDTO eventDto = eventMapper.eventToEventDto(item.getCatalogHasItems().get(0).getCatalog().getEventHasCatalogs().get(0).getEvent());
//        Account account = accountDAO.findById(item.getCatalogHasItems().get(0).getCatalog().getEventHasCatalogs().get(0).getEvent().getAccountid());
//        eventDto.setAccountid(account.getUid());
        itemDto.setEvent(eventDto);

        return Response.ok(itemDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{itemId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing item",
            notes = "Delete existing item",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteItem(@Restricted(required = true) Account me,
                               @PathParam("itemId") String itemId) {

        Preconds.checkState(me.getRole().equalsIgnoreCase(StgdrvData.AccountRoles.USER),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        item.setVisible(false);
        itemDAO.edit(item);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(itemId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ITEM_DELETE);

        return Response.ok(responseDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/likes")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new like to the item",
            notes = "Add new like to the item. AccountDto (me) cannot be null",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response addLikeToItem(@Restricted(required = true) Account me,
                                  @PathParam("itemId") String itemId,
                                  @ApiParam(required = true) AccountDTO accountDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACTION_CREATE);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ItemHasAction itemHasLike = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.LIKEUP);
        Preconds.checkState(itemHasLike == null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_NOT_ALLOWED));

        itemHasLike = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.LIKEDOWN);

        if (itemHasLike == null) itemHasLike = new ItemHasAction();
        itemHasLike.setUid(TokenUtils.generateUid());
        itemHasLike.setCreated(new Date());
        itemHasLike.setModified(new Date());
        itemHasLike.setVisible(true);
        itemHasLike.setItem(item);
        itemHasLike.setTaxonomy(StgdrvData.IteractionTypes.LIKEUP);
        itemHasLike.setAccountid(me.getId());
        itemHasActionDAO.create(itemHasLike);

        item.setNlike(item.getNlike() + 1);
        itemDAO.edit(item);

        return Response.ok(responseDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/likes")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Delete like from the item",
            notes = "Delete like from the item",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteLikeFromItem(@Restricted(required = true) Account me,
                                       @PathParam("itemId") String itemId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACTION_DELETE);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ItemHasAction itemHasLike = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.LIKEUP);
        Preconds.checkState(itemHasLike != null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_NOT_ALLOWED));

        itemHasLike.setModified(new Date());
        itemHasLike.setTaxonomy(StgdrvData.IteractionTypes.LIKEDOWN);
        itemHasActionDAO.create(itemHasLike);

        item.setNlike(item.getNlike() - 1);
        itemDAO.edit(item);

        return Response.ok(responseDto).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/bookmarks")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Add new bookmark to the item",
            notes = "Add new bookmark to the item. AccountDto (me) cannot be null",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response addBookmarkToItem(@Restricted(required = true) Account me,
                                      @PathParam("itemId") String itemId,
                                      @ApiParam(required = true) AccountDTO accountDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACTION_CREATE);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ItemHasAction itemHasBookmark = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.BOOKMARKUP);
        Preconds.checkState(itemHasBookmark == null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_NOT_ALLOWED));

        itemHasBookmark = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.BOOKMARKDOWN);

        if (itemHasBookmark == null) itemHasBookmark = new ItemHasAction();
        itemHasBookmark.setUid(TokenUtils.generateUid());
        itemHasBookmark.setCreated(new Date());
        itemHasBookmark.setModified(new Date());
        itemHasBookmark.setVisible(true);
        itemHasBookmark.setItem(item);
        itemHasBookmark.setTaxonomy(StgdrvData.IteractionTypes.BOOKMARKUP);
        itemHasBookmark.setAccountid(me.getId());
        itemHasActionDAO.create(itemHasBookmark);

        return Response.ok(responseDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/bookmarks")
    //@Metered
    @UnitOfWork
    @ApiOperation(value = "Delete bookmark from the item",
            notes = "Delete bookmark from the item",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteBookmarkFromItem(@Restricted(required = true) Account me,
                                           @PathParam("itemId") String itemId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACTION_DELETE);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ItemHasAction itemHasBookmark = itemHasActionDAO.findByItemByAccountIdByTaxonomy(item, me.getId(), StgdrvData.IteractionTypes.BOOKMARKUP);
        Preconds.checkState(itemHasBookmark != null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACTION_NOT_ALLOWED));

        itemHasBookmark.setModified(new Date());
        itemHasBookmark.setTaxonomy(StgdrvData.IteractionTypes.BOOKMARKDOWN);
        itemHasActionDAO.create(itemHasBookmark);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/likes")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve like items",
            notes = "Retrieve like items",
            response = ItemDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getLikeItems(@Restricted(required = true) Account me,
                                 @QueryParam("limit") String limit,
                                 @QueryParam("page") String page) {

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = 1000;
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        List<Item> items = new ArrayList<>();
        List<ItemHasAction> itemHasActions = itemHasActionDAO.findByTaxonomyByAccountId(pageQuery, limitQuery, StgdrvData.IteractionTypes.LIKEUP, me.getId());
        if (itemHasActions != null && !itemHasActions.isEmpty()) {
            itemHasActions.stream().forEach((itemHasAction) -> {
                items.add(itemHasAction.getItem());
            });
        }

        //Event in item
        List<ItemDTO> itemDtos = itemMapper.itemsToItemDtos(items);
        for (ItemDTO itemDto : itemDtos) {
            Item item = itemDAO.findByUid(itemDto.getId());
//            itemDto.setEvent(eventMapper.eventToEventDto(item.getCatalogHasItems().get(0).getCatalog().getEventHasCatalogs().get(0).getEvent()));
//
            //Brands in item
            List<BrandDTO> brandDtos = new ArrayList<>();
            if (item.getItemHasBrands() != null && !item.getItemHasBrands().isEmpty()) {
                item.getItemHasBrands().stream().forEach((itemHasBrand) -> {
                    Brand brand = itemHasBrand.getBrand();
                    if (brand != null) {
                        brandDtos.add(brandMapper.brandToBrandDto(brand));
                    }
                    itemDto.setBrands(brandDtos);
                });
            }
        }

        return Response.ok(itemDtos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/bookmarks")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve bookmark items",
            notes = "Retrieve bookmark items",
            response = ItemDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getBookmarkItems(@Restricted(required = true) Account me,
                                     @QueryParam("limit") String limit,
                                     @QueryParam("page") String page) {

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = 1000;
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        List<Item> items = new ArrayList<>();
        List<ItemHasAction> itemHasActions = itemHasActionDAO.findByTaxonomyByAccountId(pageQuery, limitQuery, StgdrvData.IteractionTypes.BOOKMARKUP, me.getId());
        if (itemHasActions != null && !itemHasActions.isEmpty()) {
            itemHasActions.stream().forEach((itemHasAction) -> {
                items.add(itemHasAction.getItem());
            });
        }

        //Event in item
        List<ItemDTO> itemDtos = itemMapper.itemsToItemDtos(items);
        for (ItemDTO itemDto : itemDtos) {
            Item item = itemDAO.findByUid(itemDto.getId());
//            itemDto.setEvent(eventMapper.eventToEventDto(item.getCatalogHasItems().get(0).getCatalog().getEventHasCatalogs().get(0).getEvent()));
//
            //Brands in item
            List<BrandDTO> brandDtos = new ArrayList<>();
            if (item.getItemHasBrands() != null && !item.getItemHasBrands().isEmpty()) {
                item.getItemHasBrands().stream().forEach((itemHasBrand) -> {
                    Brand brand = itemHasBrand.getBrand();
                    if (brand != null) {
                        brandDtos.add(brandMapper.brandToBrandDto(brand));
                    }
                    itemDto.setBrands(brandDtos);
                });
            }
        }

        return Response.ok(itemDtos).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/comments")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Add new comment to the item",
            notes = "Add new comment to the item. AccountDto (me) cannot be null",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response addCommentToItem(@Restricted(required = true) Account me,
                                     @PathParam("itemId") String itemId,
                                     @ApiParam(required = true) CommentDTO commentDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.COMMENT_CREATE);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Preconds.checkNotNull(commentDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.COMMENT_DTO_NULL));

        Preconds.checkNotNull(commentDto.getContent(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.COMMENT_CONTENT_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ItemHasAction itemHasComment = new ItemHasAction();
        itemHasComment.setUid(TokenUtils.generateUid());
        itemHasComment.setCreated(new Date());
        itemHasComment.setModified(new Date());
        itemHasComment.setVisible(true);
        itemHasComment.setItem(item);
        itemHasComment.setContent(commentDto.getContent());
        itemHasComment.setTaxonomy(StgdrvData.IteractionTypes.COMMENTUP);
        itemHasComment.setAccountid(me.getId());
        itemHasActionDAO.create(itemHasComment);

        item.setNcomment(item.getNcomment() + 1);
        itemDAO.edit(item);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/comments/{commentId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify comment to the item",
            notes = "Modify comment to the item. AccountDto (me) cannot be null",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response modifyCommentToItem(@Restricted(required = true) Account me,
                                        @PathParam("itemId") String itemId,
                                        @PathParam("commentId") String commentId,
                                        @ApiParam(required = true) CommentDTO commentDto) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.COMMENT_MODIFIED);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Preconds.checkNotNull(commentDto,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.COMMENT_DTO_NULL));

        Preconds.checkNotNull(commentDto.getContent(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.COMMENT_CONTENT_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ItemHasAction itemHasComment = itemHasActionDAO.findByUid(commentId);
        Preconds.checkState(itemHasComment != null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.COMMENT_DOES_NOT_EXIST));

        itemHasComment.setModified(new Date());
        itemHasComment.setContent(commentDto.getContent());
        itemHasActionDAO.edit(itemHasComment);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/comments")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve like items",
            notes = "Retrieve like items",
            response = ItemDTO.class,
            responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getItemComments(@Restricted(required = true) Account me,
                                    @PathParam("itemId") String itemId,
                                    @QueryParam("limit") String limit,
                                    @QueryParam("page") String page) {

        int pageQuery = 0;
        if (page != null) {
            pageQuery = Integer.valueOf(page);
        }

        int limitQuery = 1000;
        if (limit != null) {
            limitQuery = Integer.valueOf(limit);
        }

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ArrayList<CommentDTO> commentDtos = new ArrayList<>();
        List<ItemHasAction> itemHasActions = itemHasActionDAO.findByTaxonomyByItemOrdered(pageQuery, limitQuery, StgdrvData.IteractionTypes.COMMENTUP, item, StgdrvData.Ordered.ASCENDING);
        if (itemHasActions != null && !itemHasActions.isEmpty()) {
            for (ItemHasAction itemHasAction : itemHasActions) {
                CommentDTO commentDto = new CommentDTO();
                AccountDTO accountDto = accountMapper.accountToAccountDto(accountDAO.findById(itemHasAction.getAccountid()));
                commentDto.setAccount(accountDto);
                commentDto.setId(itemHasAction.getUid());

                Account account = accountDAO.findById(itemHasAction.getAccountid());
                if (account.getRole().equalsIgnoreCase(StgdrvData.AccountRoles.USER)) {
                    List<Event> events = eventDAO.findByAccountid(itemHasAction.getAccountid());
                    EventDTO eventDto = eventMapper.eventToEventDto(events.get(0));
                    accountDto.setEvent(eventDto);
                    commentDto.setAccount(accountDto);
                }

                commentDto.setContent(itemHasAction.getContent());
                commentDto.setCreated(DateUtils.dateToString(itemHasAction.getCreated()));
                commentDto.setModified(DateUtils.dateToString(itemHasAction.getModified()));
                commentDtos.add(commentDto);
            }
        }

        return Response.ok(commentDtos).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/comments/{commentId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify comment to the item",
            notes = "Modify comment to the item. AccountDto (me) cannot be null",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteCommentToItem(@Restricted(required = true) Account me,
                                        @PathParam("itemId") String itemId,
                                        @PathParam("commentId") String commentId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.COMMENT_DELETE);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DTO_NULL));

        Item item = itemDAO.findByUid(itemId);

        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        ItemHasAction itemHasComment = itemHasActionDAO.findByUid(commentId);
        Preconds.checkState(itemHasComment != null,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.COMMENT_DOES_NOT_EXIST));

        itemHasComment.setModified(new Date());
        itemHasComment.setTaxonomy(StgdrvData.IteractionTypes.COMMENTDOWN);
        itemHasActionDAO.edit(itemHasComment);

        return Response.ok(responseDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{itemId}/brands/{brandId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify comment to the item",
            notes = "Modify comment to the item. AccountDto (me) cannot be null",
            response = StgdrvResponseDTO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response deleteBrandToItem(@Restricted(required = true) Account me,
                                      @PathParam("itemId") String itemId,
                                      @PathParam("brandId") String brandId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.BRAND_DELETE);

        Preconds.checkNotNull(itemId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_ID_NULL));
        Item item = itemDAO.findByUid(itemId);
        Preconds.checkNotNull(item,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ITEM_DOES_NOT_EXIST));

        Preconds.checkNotNull(brandId,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_ID_NULL));
        Brand brand = brandDAO.findByUid(brandId);
        Preconds.checkNotNull(brand,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.BRAND_DOES_NOT_EXIST));

        //Remove this brand (if exists)
        ItemHasBrand itemHasBrand = itemHasBrandDAO.findById(new ItemHasBrandId(item.getId(), brand.getId()));
        if (itemHasBrand != null) {
            itemHasBrandDAO.delete(itemHasBrand);
            brand.getItemHasBrands().remove(itemHasBrand);
            brandDAO.edit(brand);
            item.getItemHasBrands().remove(itemHasBrand);
            itemDAO.edit(item);
        }

        return Response.ok(responseDto).build();
    }
}
