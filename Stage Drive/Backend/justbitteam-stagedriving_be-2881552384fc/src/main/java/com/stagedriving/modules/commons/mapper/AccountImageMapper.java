package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.AccountImageDTO;
import com.stagedriving.modules.commons.ds.entities.AccountImage;
import com.stagedriving.modules.commons.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Project stg-drv
 * Author: man
 * Date: 16/10/18
 * Time: 18:36
 */
@Mapper
public interface AccountImageMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(source = "image", target = "normalUri"),
            @Mapping(source = "image", target = "uri"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    AccountImageDTO accountImageToAccountImageDto(AccountImage accountImage);

    List<AccountImageDTO> accountImagesToAccountImageDtos(List<AccountImage> accountImages);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    AccountImage accountImageDtoToAccountImage(AccountImageDTO accountImageDTO);

    List<AccountImage> accountImageDtosToAccountImages(List<AccountImageDTO> accountImageDTOs);
}