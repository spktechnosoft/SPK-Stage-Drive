package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.BrandDTO;
import com.stagedriving.commons.v1.resources.ItemDTO;
import com.stagedriving.modules.commons.ds.entities.Brand;
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
public interface ItemMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "catalogs", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "brands", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ItemDTO itemToItemDto(Item item);

    List<ItemDTO> itemsToItemDtos(List<Item> items);

    @Mappings({
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "itemHasBrands", ignore = true),
            @Mapping(target = "catalogHasItems", ignore = true),
            @Mapping(target = "itemHasCategories", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Item itemDtoToItem(ItemDTO itemDTO);

    List<Item> itemDtosToItems(List<ItemDTO> itemDTOs);

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "items", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BrandDTO brandToBrandDto(Brand brand);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "itemHasBrands", ignore = true),
            @Mapping(target = "catalogHasBrands", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Brand brandDtoToBrand(BrandDTO brandDTO);
}