package com.stagedriving.account;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GroupResourceRestTest {

//    private static final Logger LOGGER = LoggerFactory.getLogger(GroupResourceRestTest.class);
//
//    private static DropwizardAppRule<BuyuConfiguration> service = AppTestSuite.service;
//
//    private static ArrayList<AccountGroupDTO> groups = new ArrayList<>();
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
//    public void AcreateNewGroups() throws JsonProcessingException, InterruptedException {
//        /*
//            POST GROUPS
//         */
//        AccountGroupDTO groupAdmin = doNewGroup(BuyuData.AccountRoles.ADMIN, "Admin group", BuyuData.Status.ENABLED);
//        AccountGroupDTO groupUser = doNewGroup(BuyuData.AccountRoles.USER, "Account group", BuyuData.Status.ENABLED);
//        AccountGroupDTO groupDealer = doNewGroup(BuyuData.AccountRoles.DEALER, "Dealer group", BuyuData.Status.ENABLED);
//
//        response = client
//                .resource("http://localhost:" + service.getLocalPort() + "/v1/groups")
//                .accept("application/json")
//                .type("application/json")
//                .post(ClientResponse.class, groupAdmin);
//        assertThat(response.getStatus()).isEqualTo(200);
//        BuyuResponseDTO responseDTO = response.getEntity(new GenericType<BuyuResponseDTO>() {
//        });
//        assertThat(responseDTO.getId()).isNotEmpty();
//
//        response = client
//                .resource("http://localhost:" + service.getLocalPort() + "/v1/groups")
//                .accept("application/json")
//                .type("application/json")
//                .post(ClientResponse.class, groupUser);
//        assertThat(response.getStatus()).isEqualTo(200);
//        responseDTO = response.getEntity(new GenericType<BuyuResponseDTO>() {
//        });
//        assertThat(responseDTO.getId()).isNotEmpty();
//
//        response = client
//                .resource("http://localhost:" + service.getLocalPort() + "/v1/groups")
//                .accept("application/json")
//                .type("application/json")
//                .post(ClientResponse.class, groupDealer);
//        assertThat(response.getStatus()).isEqualTo(200);
//        responseDTO = response.getEntity(new GenericType<BuyuResponseDTO>() {
//        });
//        assertThat(responseDTO.getId()).isNotEmpty();
//    }
//
//    private AccountGroupDTO doNewGroup(String name,
//                                String description,
//                                String status) throws JsonProcessingException, InterruptedException {
//
//        AccountGroupDTO group = new AccountGroupDTO();
//        group.setName(name);
//        group.setDescription(description);
//        group.setStatus(status);
//
//        return group;
//    }
}
