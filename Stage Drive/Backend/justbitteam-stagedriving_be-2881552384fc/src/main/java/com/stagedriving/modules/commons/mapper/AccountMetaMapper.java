package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.AccountMetaDTO;
import com.stagedriving.modules.commons.ds.entities.AccountMeta;
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
public interface AccountMetaMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountMetaDTO accountMetaToAccountMetaDto(AccountMeta accountMeta);

    List<AccountMetaDTO> accountMetasToAccountMetaDtos(List<AccountMeta> accountMetas);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    AccountMeta accountMetaDtoToAccountMeta(AccountMetaDTO accountMetaDTO);

    List<AccountMeta> accountMetaDtosToAccountMetas(List<AccountMetaDTO> accountMetaDTOs);
}