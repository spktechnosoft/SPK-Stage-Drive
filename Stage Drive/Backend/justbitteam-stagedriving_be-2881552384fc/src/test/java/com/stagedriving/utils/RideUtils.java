package com.stagedriving.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.justbit.commons.DateUtils;
import com.stagedriving.AppTestSuite;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.CoordinateDTO;
import com.stagedriving.commons.v1.resources.RideDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by manuel on 15/09/14.
 */
public class RideUtils {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RideUtils.class);

    private static DropwizardAppRule<AppConfiguration> service = AppTestSuite.service;

    public RideDTO newRide(RideDTO ride, AccountDTO account) throws JsonProcessingException, InterruptedException {
        Client client = ClientBuilder.newClient();

        Invocation.Builder builder = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/rides")
                .request("application/json")
                .accept("application/json");
        builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + account.getToken());

        Response response = builder.post(Entity.json(ride));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());
        ride.setId(responseDTO.getId());

        return ride;
    }

    public RideDTO doNewRide(String id,
                             Boolean hasReturn,
                             CoordinateDTO from,
                             CoordinateDTO to,
                             String accountId,
                             String goingDept,
                             String goingArriv,
                             String returnDept,
                             String returnArriv,
                             Integer seats) throws JsonProcessingException, InterruptedException {

        RideDTO ride = new RideDTO();
        ride.setId(id != null ? id : null);
        ride.setHasReturn(hasReturn ? true : false);
        from.setLatitude(12.11);
        from.setLongitude(12.12);
        ride.setFromCoordinate(from != null ? from : null);
        to.setLatitude(12.13);
        to.setLongitude(12.14);
        ride.setToCoordinate(to != null ? to : null);
        ride.setAccountId(accountId != null ? accountId : null);
        ride.setGoingDepartureDate(goingDept != null ? goingDept : null);
        ride.setGoingArrivalDate(goingArriv != null ? goingArriv : null);
        ride.setReturnDepartureDate(returnDept != null ? returnDept : null);
        ride.setReturnArrivalDate(returnArriv != null ? returnArriv : null);
        ride.setSeats(seats != null ? seats : 5);

        return ride;
    }
}
