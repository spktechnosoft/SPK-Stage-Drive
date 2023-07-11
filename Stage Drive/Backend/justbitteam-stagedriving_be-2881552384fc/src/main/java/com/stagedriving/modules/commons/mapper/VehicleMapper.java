package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.VehicleDTO;
import com.stagedriving.modules.commons.ds.entities.AccountVehicle;
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
public interface VehicleMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "features", ignore = true)
    })
    VehicleDTO accountVehicleToVehicleDto(AccountVehicle accountVehicle);

    List<VehicleDTO> accountVehiclesToVehicleDtos(List<AccountVehicle> accountVehicles);

}