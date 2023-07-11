package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.RideDTO;
import com.stagedriving.modules.commons.ds.entities.Ride;
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
public interface RideMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(source = "eventid", target = "eventId"),
            @Mapping(source = "fromEventId", target = "fromEventId"),
            @Mapping(source = "toEventId", target = "toEventId"),
            @Mapping(source = "accountid", target = "accountId"),
            @Mapping(target = "passengers", ignore = true),
            @Mapping(target = "actions", ignore = true),
            @Mapping(target = "price", ignore = true),
            @Mapping(target = "withTickets"),
            @Mapping(target = "withBookings"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "goingDepartureDate", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "goingArrivalDate", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "returnDepartureDate", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "returnArrivalDate", dateFormat = DateUtils.DATE_PATTERN)
    })
    RideDTO rideToRideDto(Ride ride);

    List<RideDTO> ridesToRideDtos(List<Ride> rides);

    @Mappings({
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "rideHasActions", ignore = true),
            @Mapping(target = "ridePassengers", ignore = true),
            @Mapping(target = "price", ignore = true),
            @Mapping(target = "withTickets", ignore = true),
            @Mapping(target = "withBookings", ignore = true),
            @Mapping(target = "goingDepartureDate", ignore = true),
            @Mapping(target = "goingArrivalDate", ignore = true),
            @Mapping(target = "returnDepartureDate", ignore = true),
            @Mapping(target = "returnArrivalDate", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true),
            @Mapping(source = "toEventId", target = "toEventId"),
            @Mapping(source = "fromEventId", target = "fromEventId")
    })
    Ride rideDtoToRide(RideDTO rideDTO);

    List<Ride> rideDtosToRides(List<RideDTO> rideDTOs);
}