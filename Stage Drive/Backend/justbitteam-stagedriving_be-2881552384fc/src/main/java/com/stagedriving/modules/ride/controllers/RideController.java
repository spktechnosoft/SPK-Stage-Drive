package com.stagedriving.modules.ride.controllers;

import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.RideDAO;
import com.stagedriving.modules.commons.ds.daos.RidePassengerDAO;
import com.stagedriving.modules.commons.ds.entities.Ride;
import com.stagedriving.modules.commons.ds.entities.RidePassenger;

import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class RideController {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RideController.class);

    @Inject
    private AppConfiguration configuration;
    @Inject
    private AccountDAO accountDAO;
    @Inject
    private RideDAO rideDAO;
    @Inject
    private RidePassengerDAO ridePassengerDAO;

    public Integer getRideBookedSeats(Ride ride) {
        Integer seats = 0;

        List<RidePassenger> ridePassengers = ridePassengerDAO.findByRide(ride);
        for (RidePassenger ridePassenger : ridePassengers) {
            if (ridePassenger.getStatus().equals(StgdrvData.RidePassengersStatusses.DELETED)) {
                continue;
            }
            Integer currentSeats = ridePassenger.getSeats();
            if (currentSeats == null || currentSeats < 0) {
                currentSeats = 1;
            }
            seats += currentSeats;
        }

        return seats;
    }
}
