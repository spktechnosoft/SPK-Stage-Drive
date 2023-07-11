package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.BundleDTO;
import com.stagedriving.modules.commons.ds.entities.Bundle;
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
public interface BundleMapper {
    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "catalogs", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BundleDTO bundleToBundleDto(Bundle bundle);

    List<BundleDTO> bundlesToBundleDtos(List<Bundle> bundles);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "bundleHasCatalogs", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Bundle bundleDtoToBundle(BundleDTO bundleDTO);

    List<Bundle> bundleDtosToBundles(List<BundleDTO> bundleDTOs);
}