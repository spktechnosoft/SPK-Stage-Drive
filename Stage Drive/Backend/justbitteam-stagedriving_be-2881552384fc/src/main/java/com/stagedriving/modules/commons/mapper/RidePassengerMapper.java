package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.RidePassengerDTO;
import com.stagedriving.modules.commons.ds.entities.RidePassenger;
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
public interface RidePassengerMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "accountId", ignore = true),
            @Mapping(target = "ride", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    RidePassengerDTO ridePassengerToRidePassengerDto(RidePassenger ridePassenger);

    List<RidePassengerDTO> ridePassengersToRidePassengerDtos(List<RidePassenger> ridePassengers);
}