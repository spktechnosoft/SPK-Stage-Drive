package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.AccountDeviceDTO;
import com.stagedriving.modules.commons.ds.entities.AccountDevice;
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
public interface AccountDeviceMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "provider", ignore = true),
            @Mapping(target = "token", source = "deviceid"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountDeviceDTO accountDeviceToAccountDeviceDto(AccountDevice accountDevice);

    List<AccountDeviceDTO> accountDevicesToAccountDeviceDtos(List<AccountDevice> accountDevices);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    AccountDevice accountDeviceDtoToAccountDevice(AccountDeviceDTO accountDeviceDTO);

    List<AccountDevice> accountDeviceDtosToAccountDevices(List<AccountDeviceDTO> accountDeviceDTOs);
}