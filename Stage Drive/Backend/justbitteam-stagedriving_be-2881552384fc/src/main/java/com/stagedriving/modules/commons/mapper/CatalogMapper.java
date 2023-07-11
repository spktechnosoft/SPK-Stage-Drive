package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.BrandDTO;
import com.stagedriving.commons.v1.resources.CatalogDTO;
import com.stagedriving.commons.v1.resources.ItemDTO;
import com.stagedriving.modules.commons.ds.entities.Brand;
import com.stagedriving.modules.commons.ds.entities.Catalog;
import com.stagedriving.modules.commons.ds.entities.Item;
import com.stagedriving.modules.commons.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Project stagedriving api
 * Author: man
 * Date: 16/10/18
 * Time: 18:36
 */
@Mapper
public interface CatalogMapper {
    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "events", ignore = true),
            @Mapping(target = "brands", ignore = true),
            @Mapping(target = "items", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    CatalogDTO catalogToCatalogDto(Catalog catalog);

    List<CatalogDTO> catalogsToCatalogDtos(List<Catalog> catalogs);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "catalogHasItems", ignore = true),
            @Mapping(target = "catalogHasBrands", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Catalog catalogDtoToCatalog(CatalogDTO catalogDTO);

    List<Catalog> catalogDtosToCatalogs(List<CatalogDTO> catalogDTOs);


    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "catalogs", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ItemDTO itemToItemDto(Item item);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "catalogHasItems", ignore = true)
    })
    Item itemDtoToItem(ItemDTO itemDTO);


    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BrandDTO brandToBrandDto(Brand brand);

    @Mappings({
            @Mapping(source = "id", target = "uid")
    })
    Brand brandDtoToBrand(BrandDTO brandDTO);
}