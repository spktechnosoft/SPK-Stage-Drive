package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.commons.ds.entities.*;
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
public interface AccountMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(source = "accountImages", target = "images"),
            @Mapping(source = "accountDevices", target = "devices"),
            @Mapping(source = "accountMetas", target = "metas"),
            @Mapping(source = "accountConnections", target = "connections"),
            @Mapping(target = "token", ignore = true),
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "expires", ignore = true),
            @Mapping(target = "groups", ignore = true),
            @Mapping(target = "visible", ignore = true),
            @Mapping(target = "birthday", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountDTO accountToAccountDto(Account account);

    List<AccountDTO> accountsToAccountDtos(List<Account> accounts);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "accountImages", ignore = true),
            @Mapping(target = "accountDevices", ignore = true),
            @Mapping(target = "accountMetas", ignore = true),
            @Mapping(target = "accountConnections", ignore = true),
            @Mapping(target = "accountHasGroups", ignore = true),
            @Mapping(target = "expires", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    Account accountDtoToAccount(AccountDTO accountDTO);

    List<Account> accountDtosToAccounts(List<AccountDTO> accountDTOs);


    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountMetaDTO accountMetaToAccountMetaDto(AccountMeta accountMeta);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "account", ignore = true)
    })
    AccountMeta accountMetaDtoToAccountMeta(AccountMetaDTO accountMetaDTO);


    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(source = "image", target = "normalUri"),
            @Mapping(source = "image", target = "uri"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountImageDTO accountImageToAccountImageDto(AccountImage accountImage);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "account", ignore = true)
    })
    AccountImage accountImageDtoToAccountImage(AccountImageDTO accountImageDTO);


    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountDeviceDTO accountDeviceToAccountDeviceDto(AccountDevice accountDevice);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "account", ignore = true)
    })
    AccountDevice accountDeviceDtoToAccountDevice(AccountDeviceDTO accountDeviceDTO);

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "accounts", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountGroupDTO groupToGroupDto(AccountGroup group);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "accountHasGroups", ignore = true)
    })
    AccountGroup groupDtoToGroup(AccountGroupDTO groupDTO);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "expires", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ConnectionDTO connectionToConnectionDto(AccountConnection connection);

    @Mappings({
            @Mapping(source = "id", target = "uid"),
            @Mapping(target = "account", ignore = true)
    })
    AccountConnection connectionDtoToConnection(ConnectionDTO connectionDTO);
}