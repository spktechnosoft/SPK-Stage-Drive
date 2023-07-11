/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.bundle.rest.root;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.justbit.aws.S3Utils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.catalog.rest.root.CatalogResource;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Catalog;
import com.stagedriving.modules.commons.ds.entities.CatalogHasItem;
import com.stagedriving.modules.commons.ds.entities.Item;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.BrandMapperImpl;
import com.stagedriving.modules.commons.mapper.CatalogMapperImpl;
import com.stagedriving.modules.commons.mapper.ColorMapperImpl;
import com.stagedriving.modules.commons.mapper.ItemMapperImpl;
import com.stagedriving.modules.commons.utils.event.ItemUtils;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/bundle")
@Api(value = "bundle", description = "Bundle resource")
public class BundleResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(BundleResource.class);

    private AppConfiguration configuration;

    @Inject
    CatalogDAO catalogDAO;
    @Inject
    CatalogMapperImpl catalogMapper;
    @Inject
    CatalogResource catalogResource;
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
    @Inject
    CatalogHasItemDAO catalogHasItemDAO;
    @Inject
    ItemMapperImpl itemMapper;
    @Inject
    BrandMapperImpl brandMapper;
    @Inject
    S3Utils s3Utils;
    @Inject
    ObjectMapper objectMapper;

    @Inject
    public BundleResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    @ApiOperation(value = "Retrieve bundle",
            notes = "Retrieves bundle",
            response = BundleDTO.class)
    //@Metered
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization", defaultValue = "Bearer ", required = true, dataType = "string", paramType = "header")
    })
    @ApiResponses(value = {
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_REQUEST, message = "Invalid request"),
            @ApiResponse(code = StgdrvResponseDTO.Codes.INVALID_TOKEN, message = "Invalid token")
    })
    public Response getBundle(@Restricted(required = false) Account me,
                              @QueryParam("limit") @DefaultValue("1000") String limit,
                              @QueryParam("page") @DefaultValue("0") String page) throws JsonProcessingException {

        BundleDTO bundleDto = new BundleDTO();

        Map<String, CatalogDTO> data = new HashMap<>();
        List<CatalogDTO> catalogs = catalogMapper.catalogsToCatalogDtos(catalogDAO.findAll());

        for (CatalogDTO catalogDTO : catalogs) {

            Catalog catalog = catalogDAO.findByUid(catalogDTO.getId());

            List<ItemDTO> itemDtos = new ArrayList<>();

            Preconds.checkNotNull(catalog,
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.CATALOG_DOES_NOT_EXIST));

            List<CatalogHasItem> catalogHasItems = catalogHasItemDAO.findByCatalogByPageByLimit(catalog, 0, 2000, null);

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

            catalogDTO.setItems(itemDtos);

            data.put(catalogDTO.getName(), catalogDTO);
        }

        bundleDto.setCatalogs(catalogs);
        bundleDto.setData(data);

        return Response.ok(bundleDto).build();
    }
}
