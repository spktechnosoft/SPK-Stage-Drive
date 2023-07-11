package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.AccountGroupDTO;
import com.stagedriving.modules.commons.ds.entities.AccountGroup;
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
public interface AccountGroupMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "accounts", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountGroupDTO groupToGroupDto(AccountGroup group);

    List<AccountGroupDTO> groupsToGroupDtos(List<AccountGroup> groups);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "accountHasGroups", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    AccountGroup groupDtoToGroup(AccountGroupDTO groupDTO);

    List<AccountGroup> groupDtosToGroups(List<AccountGroupDTO> groupDTOs);
}