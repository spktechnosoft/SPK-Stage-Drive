package com.stagedriving.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.AppTestSuite;
import com.stagedriving.commons.v1.auth.AuthDTO;
import com.stagedriving.commons.v1.auth.TokenDTO;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.BillingDTO;
import com.stagedriving.commons.v1.resources.ConnectionDTO;
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
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by simone on 15/09/14.
 */
public class AccountUtils {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AccountUtils.class);

    private static DropwizardAppRule<AppConfiguration> service = AppTestSuite.service;

    @Inject
    public AccountUtils() {
    }

    public AccountDTO newUser(String email, String password) throws JsonProcessingException, InterruptedException {
        Client client = ClientBuilder.newClient();

        AccountDTO user = new AccountDTO();
        user.setEmail(TokenUtils.generateUid().replaceAll("-", "") + "@stagedriving.com");
        user.setFirstname("TestUserFirstName");
        user.setLastname("TestUserLastName");
        user.setPassword(TokenUtils.generateUid());
        user.setGender("male");
        user.setTelephone(new Random().nextLong() + "");
        user.setMobile("3283153322");
        user.setCountry("Italy");
        user.setAddress("Via address");

        user.setConnections(ImmutableList.of(
                doNewConnection(TokenUtils.generateToken(), "email", TokenUtils.generateToken(), new Date().toString(), new Date().toString(), "code_")
        ));

        Invocation.Builder builder = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/accounts")
                .request("application/json")
                .accept("application/json");

        Response response = builder.post(Entity.json(user));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());
        user.setId(responseDTO.getId());

        return user;
    }

    public ConnectionDTO addConnection(ConnectionDTO connection, AccountDTO account) throws JsonProcessingException, InterruptedException {
        Client client = ClientBuilder.newClient();

        Invocation.Builder builder = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/connections")
                .request("application/json")
                .accept("application/json");
        builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + account.getToken());

        Response response = builder.post(Entity.json(connection));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());
        connection.setId(responseDTO.getId());

        return connection;
    }

    public BillingDTO addBilling(BillingDTO billing, AccountDTO account) throws JsonProcessingException, InterruptedException {
        Client client = ClientBuilder.newClient();

        Invocation.Builder builder = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/billings")
                .request("application/json")
                .accept("application/json");
        builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + account.getToken());

        Response response = builder.post(Entity.json(billing));

        StgdrvResponseDTO responseDTO = response.readEntity(new GenericType<StgdrvResponseDTO>() {
        });

        LOGGER.info("Response: " + responseDTO.getMessage());
        billing.setId(responseDTO.getId());

        return billing;
    }

    public TokenDTO authUser(String email, String password) throws JsonProcessingException, InterruptedException {

        Client client = ClientBuilder.newClient();

        AuthDTO authDTO = new AuthDTO();
        authDTO.setIdentifier(email);
        authDTO.setPassword(password);

        Invocation.Builder builder = client
                .target("http://localhost:" + service.getLocalPort() + "/auth")
                .request("application/json")
                .accept("application/json");

        Response response = builder.post(Entity.json(authDTO));

        assertThat(response.getStatus()).isEqualTo(200);

        TokenDTO tokenDTO = response.readEntity(new GenericType<TokenDTO>() {
        });

        assertThat(tokenDTO.getAccessToken()).isNotEmpty();

        return tokenDTO;
    }

    public AccountDTO getUser(String token, String userId) {
        Client client = ClientBuilder.newClient();

        Invocation.Builder builder = client
                .target("http://localhost:" + service.getLocalPort() + "/v1/users/" + userId)
                .request("application/json").header("Authorization", "Bearer " + token);

        Response response = builder.get(Response.class);

        assertThat(response.getStatus()).isEqualTo(200);

        AccountDTO userDTO = response.readEntity(new GenericType<AccountDTO>() {
        });

        return userDTO;
    }

    public ConnectionDTO doNewConnection(String id,
                                         String provider,
                                         String token,
                                         String expires,
                                         String refresh,
                                         String code) throws JsonProcessingException, InterruptedException {

        ConnectionDTO connection = new ConnectionDTO();
        connection.setId(id != null ? id : null);
        connection.setProvider(provider != null ? provider : null);
        connection.setToken(token != null ? token : null);
        connection.setExpires(expires != null ? expires : null);
        connection.setRefresh(refresh != null ? refresh : null);
        connection.setCode(code != null ? code : null);

        return connection;
    }

    public BillingDTO doNewBilling(String provider,
                                         String iban) throws JsonProcessingException, InterruptedException {

        BillingDTO billing = new BillingDTO();
        billing.setProvider(provider != null ? provider : null);
        billing.setIban(iban != null ? iban : null);

        return billing;
    }
}
