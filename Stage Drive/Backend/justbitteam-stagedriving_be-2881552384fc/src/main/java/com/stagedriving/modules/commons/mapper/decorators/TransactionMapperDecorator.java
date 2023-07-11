package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.TransactionDTO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.EventDAO;
import com.stagedriving.modules.commons.ds.daos.RideDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.Ride;
import com.stagedriving.modules.commons.ds.entities.Transaction;
import com.stagedriving.modules.commons.mapper.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionMapperDecorator extends TransactionMapperImpl {

    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private EventDAO eventDAO;
    @Inject
    private RideMapperImpl rideMapper;
    @Inject
    private EventMapperImpl eventMapper;

    public TransactionDTO transactionToTransactionDto(Transaction transaction) {
        TransactionDTO transactionDTO = super.transactionToTransactionDto(transaction);

        if (transaction.getAccountIdFrom() != null) {
            Account account = accountDAO.findById(transaction.getAccountIdFrom());
            transactionDTO.setAccountFrom(accountMapper.accountToAccountDto(account));
        }
        if (transaction.getAccountIdTo() != null) {
            Account account = accountDAO.findById(transaction.getAccountIdTo());
            transactionDTO.setAccountTo(accountMapper.accountToAccountDto(account));
        }
        if (transaction.getRideId() != null) {
            Ride ride = rideDAO.findById(transaction.getRideId());
            transactionDTO.setRide(rideMapper.rideToRideDto(ride));
        }
        if (transaction.getEventId() != null) {
            Event event = eventDAO.findById(transaction.getEventId());
            transactionDTO.setEvent(eventMapper.eventToEventDto(event));
        }
        if (transactionDTO.getTotalAmount() == null) {
            Double amount = transaction.getAmount();
            Double fee = transaction.getFee();
            if (amount == null) {
                amount = 0.0;
            }
            if (fee == null) {
                fee = 0.0;
            }
            transactionDTO.setTotalAmount(amount + fee);
        }

        return transactionDTO;
    }

    public List<TransactionDTO> transactionsToTransactionDtos(List<Transaction> transactions) {
        if ( transactions == null ) {
            return null;
        }

        List<TransactionDTO> list = new ArrayList<TransactionDTO>( transactions.size() );
        for ( Transaction transaction : transactions ) {
            list.add( transactionToTransactionDto( transaction ) );
        }

        return list;
    }

}
