package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.ConnectionDTO;
import com.stagedriving.modules.commons.ds.entities.AccountConnection;
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
public interface AccountConnectionMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "expires", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ConnectionDTO connectionToConnectionDto(AccountConnection connection);

    List<ConnectionDTO> connectionsToConnectionDtos(List<AccountConnection> connections);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "expires", ignore = true),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    AccountConnection connectionDtoToConnection(ConnectionDTO connectionDTO);

    List<AccountConnection> connectionDtosToConnections(List<ConnectionDTO> connectionDTOs);
}