package com.stagedriving.ride;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.justbit.commons.DateUtils;
import com.justbit.commons.TokenUtils;
import com.stagedriving.AppTestSuite;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.v1.auth.TokenDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.utils.AccountUtils;
import com.stagedriving.utils.RidePassengerUtils;
import com.stagedriving.utils.RideUtils;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.Date;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RideResourceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RideResourceRestTest.class);

    private static DropwizardAppRule<AppConfiguration> service = AppTestSuite.service;

    private final Client client = ClientBuilder.newClient();
    private static AccountUtils userUtils = new AccountUtils();
    private static RideUtils rideUtils = new RideUtils();
    private static RidePassengerUtils ridePassengerUtils = new RidePassengerUtils();

    private static AccountDTO riderDto;
    private static TokenDTO tokenRiderDto;
    private static RideDTO rideDto;

    private ArrayList<AccountDTO> passengerDtos = new ArrayList<>();

    //Rider account create - Passenger accounts create
    @Test
    public void A() throws JsonProcessingException, InterruptedException {
        riderDto = userUtils.newUser(DateUtils.dateToString(new Date()) + "@gmail.com", "amxk887wuYNsbd");
        Assertions.assertThat(riderDto.getId()).isNotEmpty();

        passengerDtos.add(userUtils.newUser(DateUtils.dateToString(new Date()) + "@gmail.com", "amxk887wuYNsbd"));
        passengerDtos.add(userUtils.newUser(DateUtils.dateToString(new Date()) + "@gmail.com", "amxk887wuYNsbd"));
        passengerDtos.add(userUtils.newUser(DateUtils.dateToString(new Date()) + "@gmail.com", "amxk887wuYNsbd"));
        passengerDtos.add(userUtils.newUser(DateUtils.dateToString(new Date()) + "@gmail.com", "amxk887wuYNsbd"));
        passengerDtos.add(userUtils.newUser(DateUtils.dateToString(new Date()) + "@gmail.com", "amxk887wuYNsbd"));
        passengerDtos.add(userUtils.newUser(DateUtils.dateToString(new Date()) + "@gmail.com", "amxk887wuYNsbd"));

        passengerDtos.forEach(passenger -> Assertions.assertThat(passenger.getId()).isNotEmpty());
    }

    //Auth rider account  - Auth every passenger account
    @Test
    public void B() throws JsonProcessingException, InterruptedException {
        tokenRiderDto = userUtils.authUser(riderDto.getEmail(), riderDto.getPassword());
        Assertions.assertThat(tokenRiderDto.getAccessToken()).isNotEmpty();
        riderDto.setToken(tokenRiderDto.getAccessToken());

        passengerDtos.forEach(passenger -> {
            try {
                passenger.setToken(userUtils.authUser(riderDto.getEmail(), riderDto.getPassword()).getAccessToken());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    //Account phone validation (add a connection with type [PHONE] - At the end of a Firebase phone validation flow)
    @Test
    public void C() throws JsonProcessingException, InterruptedException {
        ConnectionDTO connectionPhone = userUtils.addConnection(userUtils.doNewConnection(TokenUtils.generateToken(), StgdrvData.Provider.PHONE,
                TokenUtils.generateToken(), new Date().toString(), new Date().toString(), "code_"), riderDto);
        Assertions.assertThat(connectionPhone.getId()).isNotEmpty();
    }

    //Account add billing details
    @Test
    public void D() throws JsonProcessingException, InterruptedException {
        BillingDTO billing = userUtils.addBilling(userUtils.doNewBilling("bnl", "IT17X0331713401000001234567"), riderDto);
        Assertions.assertThat(billing.getId()).isNotEmpty();
    }

    //Account create a ride (with 5 seats)
    @Test
    public void E() throws JsonProcessingException, InterruptedException {
        rideDto = rideUtils.newRide(rideUtils.doNewRide(null, true,
                new CoordinateDTO(), new CoordinateDTO(),
                riderDto.getId(),
                "2018-11-7T11:27:00Z", "2018-11-7T11:27:00Z",
                "2018-11-7T11:27:00Z", "2018-11-7T11:27:00Z",
                5), riderDto);
        Assertions.assertThat(rideDto.getId()).isNotEmpty();
    }

    //Check if the account is now a rider
    @Test
    public void F() throws JsonProcessingException, InterruptedException {
        AccountDTO accountDTO = userUtils.getUser(riderDto.getToken(), riderDto.getId());
        Assertions.assertThat(accountDTO.getId()).isNotEmpty();
        Assertions.assertThat(accountDTO.getGroups()).isNotEmpty();
        Assertions.assertThat((accountDTO.getGroups().get(0).getName().equalsIgnoreCase(StgdrvData.AccountGroups.DRIVER) &&
                accountDTO.getGroups().get(1).getName().equalsIgnoreCase(StgdrvData.AccountGroups.USER)) ||
                (accountDTO.getGroups().get(1).getName().equalsIgnoreCase(StgdrvData.AccountGroups.DRIVER) &&
                        accountDTO.getGroups().get(0).getName().equalsIgnoreCase(StgdrvData.AccountGroups.USER)));
    }

    //First passenger search for specific ride [geospatial]
    @Test
    public void G() throws JsonProcessingException, InterruptedException {

    }

    //First passenger search for specific ride [temporal]
    @Test
    public void H() throws JsonProcessingException, InterruptedException {

    }

    //First passenger search for specific ride [geospatial+temporal]
    @Test
    public void I() throws JsonProcessingException, InterruptedException {

    }

    //First passenger search for specific ride [geospatial+temporal+other_filters]
    @Test
    public void L() throws JsonProcessingException, InterruptedException {
    }

    //First passenger try to make a ride booking (from geospatial+temporal results, pick the firstone)
    @Test
    public void M() throws JsonProcessingException, InterruptedException {
        AccountDTO account = ridePassengerUtils.addRidePassenger(riderDto, passengerDtos.get(0), rideDto);
        Assertions.assertThat(account.getId()).isNotEmpty();
        Assertions.assertThat(account.getId().equalsIgnoreCase(passengerDtos.get(0).getId()));
    }

    //Second passenger try to make a ride booking
    @Test
    public void N() throws JsonProcessingException, InterruptedException {
        AccountDTO account = ridePassengerUtils.addRidePassenger(riderDto, passengerDtos.get(1), rideDto);
        Assertions.assertThat(account.getId()).isNotEmpty();
        Assertions.assertThat(account.getId().equalsIgnoreCase(passengerDtos.get(1).getId()));
    }

    //Third passenger try to make a ride booking
    @Test
    public void O() throws JsonProcessingException, InterruptedException {
        AccountDTO account = ridePassengerUtils.addRidePassenger(riderDto, passengerDtos.get(2), rideDto);
        Assertions.assertThat(account.getId()).isNotEmpty();
        Assertions.assertThat(account.getId().equalsIgnoreCase(passengerDtos.get(2).getId()));
    }

    //Forth passenger try to make a ride booking
    @Test
    public void P() throws JsonProcessingException, InterruptedException {
        AccountDTO account = ridePassengerUtils.addRidePassenger(riderDto, passengerDtos.get(3), rideDto);
        Assertions.assertThat(account.getId()).isNotEmpty();
        Assertions.assertThat(account.getId().equalsIgnoreCase(passengerDtos.get(3).getId()));
    }

    //Five passenger try to make a ride booking
    @Test
    public void Q() throws JsonProcessingException, InterruptedException {
        AccountDTO account = ridePassengerUtils.addRidePassenger(riderDto, passengerDtos.get(4), rideDto);
        Assertions.assertThat(account.getId()).isNotEmpty();
        Assertions.assertThat(account.getId().equalsIgnoreCase(passengerDtos.get(4).getId()));
    }

    //Six passenger try to make a ride booking -> Error, no more seats available
    @Test
    public void R() throws JsonProcessingException, InterruptedException {
        AccountDTO account = ridePassengerUtils.addRidePassenger(riderDto, passengerDtos.get(5), rideDto);
        Assertions.assertThat(account.getId()).isNotEmpty();
        Assertions.assertThat(account.getId().equalsIgnoreCase(passengerDtos.get(5).getId()));
    }
}
