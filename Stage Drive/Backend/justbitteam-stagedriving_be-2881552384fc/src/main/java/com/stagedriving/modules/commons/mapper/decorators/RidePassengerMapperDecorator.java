package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.RidePassengerDTO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.Event;
import com.stagedriving.modules.commons.ds.entities.RidePassenger;
import com.stagedriving.modules.commons.mapper.RidePassengerMapperImpl;
import com.stagedriving.modules.event.controllers.EventController;

import java.util.ArrayList;
import java.util.List;

public class RidePassengerMapperDecorator extends RidePassengerMapperImpl {

    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private EventController eventController;

    @Override
    public RidePassengerDTO ridePassengerToRidePassengerDto(RidePassenger ridePassenger) {
        RidePassengerDTO ridePassengerDTO = super.ridePassengerToRidePassengerDto(ridePassenger);

        if (ridePassenger.getAccountId() != null) {
            Account account = accountDAO.findById(ridePassenger.getAccountId());
            if (account != null) {
                ridePassengerDTO.setAccount(accountMapper.accountToAccountDto(account));
            }
        }

        return ridePassengerDTO;
    }

    @Override
    public List<RidePassengerDTO> ridePassengersToRidePassengerDtos(List<RidePassenger> ridePassengers) {
        if ( ridePassengers == null ) {
            return null;
        }

        List<RidePassengerDTO> list = new ArrayList<RidePassengerDTO>( ridePassengers.size() );
        for ( RidePassenger ridePassenger : ridePassengers ) {
            list.add( ridePassengerToRidePassengerDto( ridePassenger ) );
        }

        return list;
    }

    public RidePassengerDTO ridePassengerToRidePassengerDto(RidePassenger ridePassenger, Event event) {
        RidePassengerDTO ridePassengerDTO = super.ridePassengerToRidePassengerDto(ridePassenger);

        if (ridePassenger.getAccountId() != null) {
            Account account = accountDAO.findById(ridePassenger.getAccountId());
            if (account != null) {
                ridePassengerDTO.setAccount(accountMapper.accountToAccountDto(account));
                ridePassengerDTO.getAccount().setHasTicket(eventController.accountHasTicket(account.getId(), event.getUid()));
            }
        }

        return ridePassengerDTO;
    }

    public List<RidePassengerDTO> ridePassengersToRidePassengerDtos(List<RidePassenger> ridePassengers, Event event) {
        if ( ridePassengers == null ) {
            return null;
        }

        List<RidePassengerDTO> list = new ArrayList<RidePassengerDTO>( ridePassengers.size() );
        for ( RidePassenger ridePassenger : ridePassengers ) {
            list.add( ridePassengerToRidePassengerDto( ridePassenger, event ) );
        }

        return list;
    }
}
