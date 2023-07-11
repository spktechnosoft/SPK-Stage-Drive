package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.BrandDTO;
import com.stagedriving.modules.commons.ds.entities.Brand;
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
public interface BrandMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "items", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BrandDTO brandToBrandDto(Brand brand);

    List<BrandDTO> brandsToBrandDtos(List<Brand> brands);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "itemHasBrands", ignore = true),
            @Mapping(target = "catalogHasBrands", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Brand brandDtoToBrand(BrandDTO brandDTO);

    List<Brand> brandDtosToBrands(List<BrandDTO> brandDTOs);
}