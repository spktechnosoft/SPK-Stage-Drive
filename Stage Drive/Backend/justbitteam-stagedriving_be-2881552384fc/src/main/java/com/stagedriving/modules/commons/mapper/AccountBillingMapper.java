package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.BillingDTO;
import com.stagedriving.modules.commons.ds.entities.AccountBilling;
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
public interface AccountBillingMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    BillingDTO billingToBillingDto(AccountBilling billing);

    List<BillingDTO> billingsToBillingDtos(List<AccountBilling> billings);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    AccountBilling billingDtoToBilling(BillingDTO billingDTO);

    List<AccountBilling> billingDtosToBillings(List<BillingDTO> billingDTOs);
}