package com.stagedriving.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.justbit.commons.TokenUtils;
import com.stagedriving.AppTestSuite;
import com.stagedriving.commons.v1.auth.AuthDTO;
import com.stagedriving.commons.v1.auth.TokenDTO;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.ConnectionDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountResourceRestTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountResourceRestTest.class);

    private static DropwizardAppRule<AppConfiguration> service = AppTestSuite.service;

    private static ArrayList<AccountDTO> accounts = new ArrayList<>();
    private final int N_ACCOUNT = 12;
    private final Client client = ClientBuilder.newClient();

    @Test
    public void ACmakeNewAccounts() throws JsonProcessingException, InterruptedException {
        /*
            MAKE USER ACCOUNTS
         */
        accounts = new ArrayList<>();
        for (int i = 0; i < N_ACCOUNT; i++) {
            AccountDTO account;
            account = doNewAccount("firstname" + i,
                    "middlename" + i,
                    "lastname" + i,
                    "junitemail_" + i + "@gmail.com",
                    "3495499312" + i,
                    "address" + i,
                    "country" + i,
                    "town" + i,
                    "city" + i,
                    "00132" + i,
                    "status" + i,
                    "M",
                    null,
                    "123123123" + i,
                    "123122321" + i,
                    "pec" + i,
                    "note" + i,
                    "password" + i,
                    "user",
                    "username" + i);
            accounts.add(account);
        }

        /*
            POST ACCOUNT WITH CONNECTIONS (fb, google, email)
         */
        for (int i = 0; i < accounts.size(); i++) {

            ArrayList<ConnectionDTO> connectionDtos = new ArrayList<>();
            ConnectionDTO connectionFacebookDto = doNewConnection(TokenUtils.generateToken().toString(), "facebook", TokenUtils.generateToken(), new Date().toString(), new Date().toString(), "code_" + i);
            ConnectionDTO connectionGoogleDTO = doNewConnection(TokenUtils.generateToken().toString(), "google", TokenUtils.generateToken(), new Date().toString(), new Date().toString(), "code_" + i);
            ConnectionDTO connectionEmailDTO = doNewConnection(TokenUtils.generateToken().toString(), "email", TokenUtils.generateToken(), new Date().toString(), new Date().toString(), "code_" + i);

            connectionDtos.add(connectionFacebookDto);
            connectionDtos.add(connectionGoogleDTO);
            connectionDtos.add(connectionEmailDTO);
            accounts.get(i).setConnections(connectionDtos);

            Invocation.Builder builder = client
                    .target("http://localhost:" + service.getLocalPort() + "/v1/accounts")
                    .request("application/json")
                    .accept("application/json");

            Response response = builder.post(Entity.json(accounts.get(i)));

            StgdrvResponseDTO responseDto = response.readEntity(new GenericType<StgdrvResponseDTO>() {
            });

            LOGGER.info("Response: " + responseDto.getMessage());
            accounts.get(i).setId(responseDto.getId());
        }
    }

    @Test
    public void BauthNewAccounts() throws JsonProcessingException, InterruptedException {
        /*
            AUTH EVERY ACCOUNT
         */
        for (int i = 0; i < accounts.size(); i++) {
            AccountDTO accountDto = accounts.get(i);

            Client client = ClientBuilder.newClient();

            AuthDTO authDTO = new AuthDTO();
            authDTO.setIdentifier(accountDto.getEmail());
            authDTO.setPassword(accountDto.getPassword());

            Invocation.Builder builder = client
                    .target("http://localhost:" + service.getLocalPort() + "/auth")
                    .request("application/json")
                    .accept("application/json");

            Response response = builder.post(Entity.json(authDTO));
            Assertions.assertThat(response.getStatus()).isEqualTo(200);
            TokenDTO tokenDto = response.readEntity(new GenericType<TokenDTO>() {
            });
            Assertions.assertThat(tokenDto.getAccessToken()).isNotEmpty();

            accounts.get(i).setToken(tokenDto.getAccessToken());
        }
    }

    @Test
    public void CgetEveryNewAccounts() throws JsonProcessingException, InterruptedException {
        /*
            GET EVERY SINGLE ACCOUNT
         */
        for (AccountDTO account : accounts) {
            Invocation.Builder builder = client
                    .target("http://localhost:" + service.getLocalPort() + "/v1/accounts/" + account.getId())
                    .request("application/json")
                    .accept("application/json");
            builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + account.getToken());

            Response response = builder.get();
            Assertions.assertThat(response.getStatus()).isEqualTo(200);

            AccountDTO accountDto = response.readEntity(new GenericType<AccountDTO>() {
            });
            Assertions.assertThat(accountDto.getId()).isNotEmpty();
            Assertions.assertThat(accountDto.getEmail()).isNotEmpty();
        }
    }

    @Test
    public void DmodifyEveryNewAccounts() throws JsonProcessingException, InterruptedException {
        /*
            PUT ON EVERY ACCOUNT
         */
        for (AccountDTO account : accounts) {
            account.setFirstname(account.getFirstname() + "_mod");
            account.setFirstname(account.getMiddlename() + "_mod");
            account.setFirstname(account.getLastname() + "_mod");

            Invocation.Builder builder = client
                    .target("http://localhost:" + service.getLocalPort() + "/v1/accounts/" + account.getId())
                    .request("application/json")
                    .accept("application/json");
            builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + account.getToken());

            Response response = builder.put(Entity.json(account));
            Assertions.assertThat(response.getStatus()).isEqualTo(200);

            StgdrvResponseDTO responseDto = response.readEntity(new GenericType<StgdrvResponseDTO>() {
            });
            Assertions.assertThat(responseDto.getId()).isNotEmpty();
        }
    }

    private AccountDTO doNewAccount(String firstname,
                                    String middlename,
                                    String lastname,
                                    String email,
                                    String phone,
                                    String address,
                                    String country,
                                    String town,
                                    String city,
                                    String zipcode,
                                    String status,
                                    String gender,
                                    String birthday,
                                    String telephone,
                                    String mobile,
                                    String pec,
                                    String note,
                                    String password,
                                    String role,
                                    String username) throws JsonProcessingException, InterruptedException {

        AccountDTO account = new AccountDTO();
        account.setFirstname(firstname != null ? firstname : null);
        account.setMiddlename(middlename != null ? middlename : null);
        account.setLastname(lastname != null ? lastname : null);
        account.setEmail(email != null ? email : null);
        account.setTelephone(phone != null ? phone : null);
        account.setAddress(address != null ? address : null);
        account.setCountry(country != null ? country : null);
        account.setTown(town != null ? town : null);
        account.setCity(city != null ? city : null);
        account.setZipcode(zipcode != null ? zipcode : null);
        account.setStatus(status != null ? status : null);
        account.setGender(gender != null ? gender : null);
        account.setTelephone(telephone != null ? telephone : null);
        account.setMobile(mobile != null ? mobile : null);
        account.setPec(pec != null ? pec : null);
        account.setNote(note != null ? note : null);
        account.setPassword(password != null ? password : null);
        account.setRole(role != null ? role : null);
        account.setUsername(username != null ? username : null);

        return account;
    }

    private ConnectionDTO doNewConnection(String id,
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
}
