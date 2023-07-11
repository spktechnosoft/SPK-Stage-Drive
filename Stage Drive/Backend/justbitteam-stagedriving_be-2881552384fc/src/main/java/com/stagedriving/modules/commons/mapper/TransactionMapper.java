package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.TransactionDTO;
import com.stagedriving.modules.commons.ds.entities.Transaction;
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
public interface TransactionMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "accountIdFrom", ignore = true),
            @Mapping(target = "accountIdTo", ignore = true),
            @Mapping(target = "rideId", ignore = true),
            @Mapping(target = "eventId", ignore = true)
    })
    TransactionDTO transactionToTransactionDto(Transaction transaction);

    List<TransactionDTO> transactionsToTransactionDtos(List<Transaction> transactions);

    @Mappings({
            @Mapping(target = "uid", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    Transaction transactionDtoToTransaction(TransactionDTO transactionDTO);

    List<Transaction> transactionDtosToTransactions(List<TransactionDTO> transactionDTOs);

}