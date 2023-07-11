package com.stagedriving.modules.commons.mapper.decorators;

import com.stagedriving.commons.v1.resources.VehicleDTO;
import com.stagedriving.modules.commons.ds.entities.AccountVehicle;
import com.stagedriving.modules.commons.mapper.VehicleMapperImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleMapperDecorator extends VehicleMapperImpl {

    @Override
    public VehicleDTO accountVehicleToVehicleDto(AccountVehicle accountVehicle) {
        VehicleDTO vehicleDTO = super.accountVehicleToVehicleDto(accountVehicle);

        if (accountVehicle.getFeatures() != null && !accountVehicle.getFeatures().isEmpty()) {
            List<String> features = Arrays.asList(accountVehicle.getFeatures().split(","));
            vehicleDTO.setFeatures(features);
        }

        return vehicleDTO;
    }

    @Override
    public List<VehicleDTO> accountVehiclesToVehicleDtos(List<AccountVehicle> accountVehicles) {
        if ( accountVehicles == null ) {
            return null;
        }

        List<VehicleDTO> list = new ArrayList<VehicleDTO>( accountVehicles.size() );
        for ( AccountVehicle accountVehicle : accountVehicles ) {
            list.add( accountVehicleToVehicleDto( accountVehicle ) );
        }

        return list;
    }
}
