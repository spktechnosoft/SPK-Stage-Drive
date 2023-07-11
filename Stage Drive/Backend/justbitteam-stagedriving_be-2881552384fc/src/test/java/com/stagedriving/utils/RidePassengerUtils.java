package com.stagedriving.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.stagedriving.AppTestSuite;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.RideDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.assertj.core.api.Assertions;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Created by manuel on 15/09/14.
 */
public class RidePassengerUtils {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RidePassengerUtils.class);

    private static DropwizardAppRule<AppConfiguration> service = AppTestSuite.service;

    public AccountDTO addRidePassenger(AccountDTO accountRider, AccountDTO accountPassenger, RideDTO ride) throws JsonProcessingException, InterruptedException {
        Client client = ClientBuilder.newClient();

        Invocation.Builder builder = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/rides/" + ride.getId() + "/passengers")
                .request("application/json")
                .accept("application/json");
        builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + accountRider.getToken());

        Response response = builder.post(Entity.json(accountPassenger));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());
        Assertions.assertThat(responseDTO.getId()).isNotEmpty();
        accountPassenger.setId(responseDTO.getId());

        return accountPassenger;
    }
}
