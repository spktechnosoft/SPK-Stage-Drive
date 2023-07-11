package com.stagedriving.account;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConnectionResourceRestTest {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionResourceRestTest.class);
//
//    private static DropwizardAppRule<BuyuConfiguration> service = AppTestSuite.service;
//
//    private static List<ConnectionDTO> connections;
//    private static ArrayList<AccountDTO> accounts = new ArrayList<>();
//
//    private Random rand = new Random();
//
//    private Client client = new Client();
//    private ClientResponse response = null;
//
//    @Before
//    public void testSetup() {
//
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void AAretrieveAccounts() throws JsonProcessingException, InterruptedException {
//        /*
//            RETRIEVE ALL ACCOUNTS
//         */
//        accounts = new ArrayList<AccountDTO>();
//
//        response = client
//                .resource("http://localhost:" + service.getLocalPort() + "/v1/accounts")
//                .accept("application/json")
//                .type("application/json")
//                .get(ClientResponse.class);
//        assertThat(response.getStatus()).isEqualTo(200);
//        accounts = response.getEntity(new GenericType<ArrayList<AccountDTO>>() {
//        });
//    }
//
//    @Test
//    public void ABauthNewAccounts() throws JsonProcessingException, InterruptedException {
//        /*
//            AUTH EVERY ACCOUNT
//         */
//        for (int i = 0; i < accounts.size(); i++) {
//            AccountDTO accountDto = accounts.get(i);
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/auth")
//                    .accept("application/json")
//                    .type("application/json")
//                    .post(ClientResponse.class, accountDto);
//            assertThat(response.getStatus()).isEqualTo(200);
//
//            TokenDTO tokenDTO = response.getEntity(new GenericType<TokenDTO>() {
//            });
//
//            assertThat(tokenDTO.getAccessToken()).isNotEmpty();
//            assertThat(tokenDTO.getAccessExpires()).isNotEmpty();
//
//            accounts.get(i).setToken(tokenDTO.getAccessToken());
//            accounts.get(i).setExpires(tokenDTO.getAccessExpires());
//        }
//    }
//
//    @Test
//    public void AcreateNewConnections() throws JsonProcessingException, InterruptedException {
//        /*
//            POST CONNECTION
//         */
//        for (int i = 0; i < accounts.size(); i++) {
//            AccountDTO accountDto = accounts.get(i);
//            ArrayList<ConnectionDTO> connectionDTOs = new ArrayList<>();
//
//            ConnectionDTO connectionFacebookDTO = doNewConnection(TokenUtils.generateToken().toString(), "facebook", TokenUtils.generateToken(), new Date().toString(), new Date().toString(), "code_" + i);
//            connectionDTOs.add(connectionFacebookDTO);
//
//            ConnectionDTO connectionGoogleDTO = doNewConnection(TokenUtils.generateToken().toString(), "google", TokenUtils.generateToken(), new Date().toString(), new Date().toString(), "code_" + i);
//            connectionDTOs.add(connectionGoogleDTO);
//
//            accountDto.setConnections(connectionDTOs);
//
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/v1/connections")
//                    .accept("application/json")
//                    .header("Authorization", "Bearer " + accountDto.getToken())
//                    .type("application/json")
//                    .post(ClientResponse.class, connectionFacebookDTO);
//            assertThat(response.getStatus()).isEqualTo(200);
//            BuyuResponseDTO responseDTO = response.getEntity(new GenericType<BuyuResponseDTO>() {
//            });
//            assertThat(responseDTO.getId()).isNotEmpty();
//            accountDto.getConnections().get(0).setId(responseDTO.getId());
//
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/v1/connections")
//                    .accept("application/json")
//                    .header("Authorization", "Bearer " + accountDto.getToken())
//                    .type("application/json")
//                    .post(ClientResponse.class, connectionGoogleDTO);
//            assertThat(response.getStatus()).isEqualTo(200);
//            responseDTO = response.getEntity(new GenericType<BuyuResponseDTO>() {
//            });
//            assertThat(responseDTO.getId()).isNotEmpty();
//            accountDto.getConnections().get(1).setId(responseDTO.getId());
//        }
//    }
//
//    @Test
//    public void BgetEveryNewConnection() throws JsonProcessingException, InterruptedException {
//        /*
//            GET EVERY SINGLE ACCOUNT CONNECTION
//         */
//        for (AccountDTO accountDto : accounts) {
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/v1/connections/" + accountDto.getConnections().get(0).getId())
//                    .accept("application/json")
//                    .header("Authorization", "Bearer " + accountDto.getToken())
//                    .type("application/json")
//                    .get(ClientResponse.class);
//            assertThat(response.getStatus()).isEqualTo(200);
//            ConnectionDTO connectionDTO = response.getEntity(new GenericType<ConnectionDTO>() {
//            });
//            assertThat(connectionDTO.getId()).isNotEmpty();
//
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/v1/connections/" + accountDto.getConnections().get(1).getId())
//                    .accept("application/json")
//                    .header("Authorization", "Bearer " + accountDto.getToken())
//                    .type("application/json")
//                    .get(ClientResponse.class);
//            assertThat(response.getStatus()).isEqualTo(200);
//            connectionDTO = response.getEntity(new GenericType<ConnectionDTO>() {
//            });
//            assertThat(connectionDTO.getId()).isNotEmpty();
//        }
//    }
//
//    @Test
//    public void CputEveryNewConnection() throws JsonProcessingException, InterruptedException {
//        /*
//            PUT ON EVERY ACCOUNT CONNECTION
//         */
//        for (AccountDTO accountDto : accounts) {
//            ConnectionDTO connectionDTO = accountDto.getConnections().get(0);
//            connectionDTO.setProvider("linkedin");
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/v1/connections/" + accountDto.getConnections().get(0).getId())
//                    .accept("application/json")
//                    .header("Authorization", "Bearer " + accountDto.getToken())
//                    .type("application/json")
//                    .put(ClientResponse.class, connectionDTO);
//            assertThat(response.getStatus()).isEqualTo(200);
//            BuyuResponseDTO responseDTO = response.getEntity(new GenericType<BuyuResponseDTO>() {
//            });
//            assertThat(responseDTO.getId()).isNotEmpty();
//
//            connectionDTO = accountDto.getConnections().get(1);
//            connectionDTO.setProvider("twitter");
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/v1/connections/" + accountDto.getConnections().get(1).getId())
//                    .accept("application/json")
//                    .header("Authorization", "Bearer " + accountDto.getToken())
//                    .type("application/json")
//                    .put(ClientResponse.class, connectionDTO);
//            assertThat(response.getStatus()).isEqualTo(200);
//            responseDTO = response.getEntity(new GenericType<BuyuResponseDTO>() {
//            });
//            assertThat(responseDTO.getId()).isNotEmpty();
//        }
//    }
//
//    @Test
//    public void DgetEveryNewConnectionByAccount() throws JsonProcessingException, InterruptedException {
//        /*
//            GET EVERY SINGLE ACCOUNT CONNECTION WITH ACCOUNT CONNECTIONS SUBRESOURCE
//         */
//        for (AccountDTO accountDto : accounts) {
//            response = client
//                    .resource("http://localhost:" + service.getLocalPort() + "/v1/accounts/" + accountDto.getId() + "/connections")
//                    .accept("application/json")
//                    .header("Authorization", "Bearer " + accountDto.getToken())
//                    .type("application/json")
//                    .get(ClientResponse.class);
//            assertThat(response.getStatus()).isEqualTo(200);
//            List<ConnectionDTO> connectionDtos = response.getEntity(new GenericType<List<ConnectionDTO>>() {
//            });
//            assertThat(connectionDtos.size() == 2);
//        }
//    }
//
//    private ConnectionDTO doNewConnection(String id,
//                                          String provider,
//                                          String token,
//                                          String expires,
//                                          String refresh,
//                                          String code) throws JsonProcessingException, InterruptedException {
//
//        ConnectionDTO connection = new ConnectionDTO();
//        connection.setId(id != null ? id : null);
//        connection.setProvider(provider != null ? provider : null);
//        connection.setToken(token != null ? token : null);
//        connection.setExpires(expires != null ? expires : null);
//        connection.setRefresh(refresh != null ? refresh : null);
//        connection.setCode(code != null ? code : null);
//
//        return connection;
//    }
}
