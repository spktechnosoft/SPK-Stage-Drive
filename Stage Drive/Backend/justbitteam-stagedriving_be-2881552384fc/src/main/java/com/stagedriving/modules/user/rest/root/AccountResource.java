/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stagedriving.modules.user.rest.root;

import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.*;
import com.stagedriving.modules.auth.controller.UserAuthController;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.PagedResults;
import com.stagedriving.modules.commons.ds.daos.*;
import com.stagedriving.modules.commons.ds.entities.*;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.AccountConnectionMapperImpl;
import com.stagedriving.modules.commons.mapper.AccountGroupMapperImpl;
import com.stagedriving.modules.commons.mapper.AccountImageMapperImpl;
import com.stagedriving.modules.commons.mapper.AccountMetaMapperImpl;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import com.stagedriving.modules.commons.mapper.decorators.VehicleMapperDecorator;
import com.stagedriving.modules.commons.utils.account.AccountUtils;
import com.stagedriving.modules.commons.utils.media.ImageUtils;
import com.stagedriving.modules.commons.utils.meta.MetaUtils;
import com.stagedriving.modules.event.rest.root.ActionLikeResource;
import com.stagedriving.modules.event.rest.root.BookingResource;
import com.stagedriving.modules.user.controller.AccountController;
import com.stagedriving.modules.user.controller.FriendshipController;
import com.stagedriving.modules.user.rest.l1.BillingAccountResourse;
import com.stagedriving.modules.user.rest.l1.ConnectionAccountResource;
import com.stagedriving.modules.user.rest.l1.ReviewAccountResource;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/accounts")
@Api(value = "accounts", description = "Accounts resource")
public class AccountResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AccountResource.class);

    @Inject
    AccountDAO accountDAO;
    @Inject
    AccountGroupMapperImpl groupMapper;
    @Inject
    VehicleMapperDecorator vehicleMapper;
    @Inject
    AccountConnectionDAO accountConnectionDAO;
    @Inject
    AccountVehicleDAO accountVehicleDAO;
    @Inject
    AccountHasGroupDAO accountHasGroupDAO;
    @Inject
    AccountGroupDAO groupDAO;
    @Inject
    AccountDeviceResource accountDeviceResource;
    @Inject
    AccountMapperDecorator accountMapper;
    @Inject
    AccountImageMapperImpl accountImageMapper;
    @Inject
    AccountImageDAO accountImageDAO;
    @Inject
    AccountMetaDAO accountMetaDAO;
    @Inject
    ConnectionAccountResource connectionAccountResourse;
    @Inject
    BillingAccountResourse billingAccountResourse;
    @Inject
    ActionLikeResource actionLikeResource;
    @Inject
    BookingResource bookingResource;
    @Inject
    ReviewAccountResource reviewAccountResourse;
    @Inject
    ReviewResource reviewResourse;
    @Inject
    UserAuthController userAuthController;
    @Inject
    AccountConnectionMapperImpl connectionMapper;
    @Inject
    AccountConnectionDAO connectionDAO;
    @Inject
    AccountUtils accountUtils;
    @Inject
    MetaUtils metaUtils;
    @Inject
    ImageUtils imageUtils;
    @Inject
    AccountMetaMapperImpl accountMetaMapper;
    @Inject
    FriendshipController friendshipController;
    @Inject
    AccountController accountController;

    private AppConfiguration configuration;

    @Inject
    public AccountResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "New account resource",
            notes = "New account resource. Mandatory field are (1) email (2) password",
            response = StgdrvResponseDTO.class)
    public Response createAccount(
            @Restricted(required = false) Account reqAccount,
            @ApiParam AccountDTO accountDto) throws Exception {

        boolean isAdmin = accountController.isAdmin(reqAccount);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.ACCOUNT_CREATE_ERROR);

        Preconds.checkConditions(accountDto.getEmail() == null, StgdrvMessage.MessageError.ACCOUNT_EMAIL_NULL);
        Preconds.checkConditions(!EmailValidator.getInstance().isValid(accountDto.getEmail()), StgdrvMessage.MessageError.ACCOUNT_EMAIL_NOT_VALID);

        Account account = null;
        boolean isNew = true;
        if (accountDto.getId() != null) {
            account = accountDAO.findByUid(accountDto.getId());
            Preconds.checkConditions(account == null, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST);
            isNew = false;
        } else {
            account = accountDAO.findByEmail(accountDto.getEmail());
            Preconds.checkConditions(account != null, StgdrvMessage.MessageError.ACCOUNT_EMAIL_ALREADY_EXIST);
            isNew = true;
        }

        if (!isAdmin) {
            Preconds.checkArgument(account == null || !(reqAccount == null || !reqAccount.getId().equals(account.getId())), StgdrvMessage.MessageError.ACCOUNT_EMAIL_ALREADY_EXIST);
        }

        if (isNew) {
            Preconds.checkConditions(accountDto.getPassword() == null, StgdrvMessage.MessageError.ACCOUNT_PASSWORD_NULL);
            Preconds.checkConditions(accountDto.getConnections().size() <= 0, StgdrvMessage.MessageError.CONNECTION_DTO_NULL);
            Preconds.checkConditions(accountDto.getConnections().get(0) == null, StgdrvMessage.MessageError.CONNECTION_DTO_NULL);
            Preconds.checkConditions(accountDto.getConnections().get(0).getProvider() == null, StgdrvMessage.MessageError.CONNECTION_PROVIDER_NULL);

            account = new Account();
            account.setUid(TokenUtils.generateUid());
            account.setCreated(new Date());
            account.setModified(new Date());
            account.setStatus(StgdrvData.AccountStatus.ACTIVE);
            account.setToken(TokenUtils.generateToken());
            account.setExpires(new Date());
            account.setVisible(true);
        }

        Account newAccount = accountMapper.accountDtoToAccount(accountDto);
        accountUtils.merge(account, newAccount);

        accountDAO.edit(account);

        if (accountDto.getImages() != null && !accountDto.getImages().isEmpty() && accountDto.getImages().get(0) != null) {
            Preconds.checkConditions(accountDto.getImages().get(0).getNormalUri() == null &&
                            accountDto.getImages().get(0).getUri() == null,
                    StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.IMAGE_NORMAL_URI_NULL);

            List<AccountImage> imagesOld = accountImageDAO.findByAccount(account);
            for (AccountImage accountImage : imagesOld) {
                accountImageDAO.delete(accountImage);
                account.getAccountImages().remove(accountImage);
                accountDAO.edit(account);
            }

            AccountImage accountImage = accountImageMapper.accountImageDtoToAccountImage(accountDto.getImages().get(0));
            accountImage.setCreated(new Date());
            accountImage.setModified(new Date());
            accountImage.setUid(TokenUtils.generateUid());
            accountImage.setAccount(account);
            String img = accountDto.getImages().get(0).getNormalUri();
            if (accountDto.getImages().get(0).getUri() != null) {
                img = accountDto.getImages().get(0).getUri();
            }
            String uri = imageUtils.saveImageUri(img, accountImage.getUid(), "png", configuration.getStorage().getEventBucket());
            accountImage.setImage(uri);
            accountImage.setTaxonomy(StgdrvData.AccountImageCategory.AVATAR);
            accountImageDAO.create(accountImage);
        }

        if (isNew) {
            for (ConnectionDTO connectionDTO : accountDto.getConnections()) {

                Preconds.checkArgument(connectionDTO.getId() == null || (connectionDTO.getId() != null && connectionDAO.findById(connectionDTO.getId()) == null), StgdrvMessage.MessageError.CONNECTION_ALREADY_EXIST);

                AccountConnection connection = connectionMapper.connectionDtoToConnection(connectionDTO);
                connection.setAccount(account);
                connection.setUid(TokenUtils.generateUid());
                connection.setCreated(new Date());
                connection.setModified(new Date());
                connection.setExpires(DateTime.now().plusDays(30).toDate());
                if (connectionDTO.getProvider().equals(StgdrvData.Provider.EMAIL)) {
                    connection.setId(TokenUtils.generateUid());
                    connection.setToken(connectionDTO.getToken());
                } else {
                    connection.setId(connectionDTO.getId());
                    connection.setToken(connectionDTO.getToken());
                }

                connectionDAO.create(connection);
            }

            AccountGroup userGroup = groupDAO.findByName(StgdrvData.AccountGroups.USER);
            AccountHasGroupId accountHasGroupId = new AccountHasGroupId();
            accountHasGroupId.setAccountId(account.getId());
            accountHasGroupId.setGroupId(userGroup.getId());
            AccountHasGroup accountHasGroup = new AccountHasGroup();
            accountHasGroup.setId(accountHasGroupId);
            accountHasGroup.setAccount(account);

            accountHasGroup.setAccountGroup(userGroup);
            accountHasGroup.setVisible(true);
            accountHasGroup.setCreated(new Date());
            accountHasGroup.setModified(new Date());
            accountHasGroupDAO.create(accountHasGroup);
        }

        if (accountDto.getVehicles() != null && !accountDto.getVehicles().isEmpty()) {

            List<AccountVehicle> vehiclesToDelete = new ArrayList<>();
            for (AccountVehicle accountVehicle : account.getAccountVehicles()) {
                vehiclesToDelete.add(accountVehicle);
            }
            for (AccountVehicle accountVehicle : vehiclesToDelete) {
                accountVehicleDAO.delete(accountVehicle);
            }
            account.setAccountVehicles(new ArrayList<>());
            accountDAO.edit(account);

            for (VehicleDTO vehicleDTO : accountDto.getVehicles()) {
                AccountVehicle newVehicle = new AccountVehicle();
                newVehicle.setUid(TokenUtils.generateUid());
                newVehicle.setCreated(new Date());
                newVehicle.setModified(new Date());
                newVehicle.setStatus("enabled");
                newVehicle.setAccount(account);

                newVehicle.setName(vehicleDTO.getName());
                newVehicle.setDescription(vehicleDTO.getDescription());
                newVehicle.setFeatures(StringUtils.join(vehicleDTO.getFeatures(), ","));
                newVehicle.setManufacturer(vehicleDTO.getManufacturer());

                accountVehicleDAO.edit(newVehicle);
                account.getAccountVehicles().add(newVehicle);
            }
        }

//        if (isNew) {
//            userAuthController.buildAndSave(account.getUid(),
//                    account.getToken(),
//                    account.getExpires().toString());
//        }

        accountController.checkForRole(account);

        responseDto.setId(account.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACCOUNT_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{accountId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing account",
            notes = "Modify existing account",
            response = StgdrvResponseDTO.class)
    public Response modifyAccount(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) AccountDTO accountDto,
                                  @PathParam("accountId") String accountId) throws Exception {

        accountDto.setId(accountId);
        return createAccount(me, accountDto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieves accounts",
            notes = "Retrieves accounts",
            response = AccountDTO.class,
            responseContainer = "List")
    public Response getAccounts(@Restricted(required = false) Account me,
                                @ApiParam(value = "Role") @QueryParam("role") String role,
                                @ApiParam(value = "Query") @QueryParam("q") String query,
                                @ApiParam(value = "Order") @QueryParam("order") @DefaultValue("DESC") String order,
                                @ApiParam(value = "Sort") @QueryParam("sort") @DefaultValue("created") String sort,
                                @ApiParam(value = "Firstname like") @QueryParam("firstname_like") String firstnameLike,
                                @ApiParam(value = "Lastname like") @QueryParam("lastname_like") String lastnameLike,
                                @ApiParam(value = "Id like") @QueryParam("id_like") String idLike,
                                @ApiParam(value = "Email like") @QueryParam("email_like") String emailLike,
                                @ApiParam(value = "Gender like") @QueryParam("gender_like") String genderLike,
                                @ApiParam(value = "Groups like") @QueryParam("groups_like") String groupsLike,
                                @ApiParam(value = "FavStyle like") @QueryParam("favStyle_like") String favStyleLike,
                                @ApiParam(value = "FavCategory like") @QueryParam("favCategory_like") String favCategoryLike,
                                @ApiParam(value = "Size") @QueryParam("size") @DefaultValue("false") Boolean size,
                                @ApiParam(value = "Response page size") @QueryParam("limit") Integer limit,
                                @ApiParam(value = "Response page index") @QueryParam("page") Integer page) {

        boolean isAdmin = accountController.isAdmin(me);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.ACCOUNTS_GET_ERROR);

        PagedResults<Account> results = null;
        if (size) {
            results = new PagedResults<>();
        }

        List<Account> accounts = new ArrayList<>();
        List<AccountDTO> accountDtos = new ArrayList<>();
        accounts = accountDAO.findAllFiltered(query, role, idLike, emailLike, firstnameLike, lastnameLike, genderLike, groupsLike, favCategoryLike, favStyleLike, sort, order, page, limit, results);

        for (Account account : accounts) {
            AccountDTO accountDto = accountMapper.accountToAccountDto(account);
            if (!account.getAccountImages().isEmpty()) {
                accountDto.setImages(accountImageMapper.accountImagesToAccountImageDtos(account.getAccountImages()));
            }
            if (account.getAccountImages().isEmpty()) {
                AccountImageDTO placeholderImg = new AccountImageDTO();
                placeholderImg.setNormalUri("https://3dkwnd1tjao69aw8x2c13k3p-wpengine.netdna-ssl.com/wp-content/uploads/Person-placeholder.jpg");
                placeholderImg.setUri("https://3dkwnd1tjao69aw8x2c13k3p-wpengine.netdna-ssl.com/wp-content/uploads/Person-placeholder.jpg");
                List<AccountImageDTO> imageDTOS = new ArrayList<>();
                imageDTOS.add(placeholderImg);
                accountDto.setImages(imageDTOS);
            }
            if (me != null && !isAdmin && !me.equals(account)) {
                boolean isFriend = friendshipController.isFriends(me, account);
                accountDto.setIsFriend(isFriend);
            }
            accountDtos.add(accountDto);
        }

        if (results != null) {
            results.setData(accountDtos);
            return Response.ok(results).build();
        }

        return Response.ok(accountDtos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{accountId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve account",
            notes = "Retrieve account",
            response = AccountDTO.class)
    public Response getAccount(@Restricted(required = false) Account me,
                               @PathParam("accountId") String accountId) {

        boolean isAdmin = accountController.isAdmin(me);

        Account account = null;
        if (accountId != null && accountId.equalsIgnoreCase(StgdrvData.Account.ME) && me != null &&
                me.getToken() != null) {
            account = accountDAO.findByToken(me.getToken());
        } else if (accountId != null) {
            account = accountDAO.findByUid(accountId);
        }
    Preconds.checkConditions(account == null || account.getStatus() == null || account.getStatus().equals(StgdrvData.AccountStatus.INACTIVE),
                StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST);

        AccountDTO meDto = accountMapper.accountToAccountDto(account);
        List<VehicleDTO> vehicleDTOS = vehicleMapper.accountVehiclesToVehicleDtos(account.getAccountVehicles());
        meDto.setVehicles(vehicleDTOS);
        List<AccountGroup> accountGroups = new ArrayList<>();
        for (AccountHasGroup accountHasGroup : account.getAccountHasGroups()) {
            accountGroups.add(accountHasGroup.getAccountGroup());
        }
        meDto.setGroups(groupMapper.groupsToGroupDtos(accountGroups));
        List<AccountImageDTO> imageDtos = accountImageMapper.accountImagesToAccountImageDtos(account.getAccountImages());
        if (imageDtos.isEmpty()) {
            AccountImageDTO placeholderImg = new AccountImageDTO();
            placeholderImg.setNormalUri("https://3dkwnd1tjao69aw8x2c13k3p-wpengine.netdna-ssl.com/wp-content/uploads/Person-placeholder.jpg");
            placeholderImg.setUri("https://3dkwnd1tjao69aw8x2c13k3p-wpengine.netdna-ssl.com/wp-content/uploads/Person-placeholder.jpg");
            imageDtos.add(placeholderImg);
        }
        meDto.setImages(imageDtos);

        if (me != null && /*!isAdmin &&*/ !me.equals(account)) {
            boolean isFriend = friendshipController.isFriends(me, account);
            meDto.setIsFriend(isFriend);
        }

        return Response.ok(meDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{accountId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing account",
            notes = "Delete existing account",
            response = StgdrvResponseDTO.class)
    public Response deleteAccount(
            @Restricted Account me,
            @ApiParam(required = true) @PathParam("accountId") String accountId) {

        boolean isAdmin = accountController.isAdmin(me);
        Preconds.checkConditions(!accountId.equals("me") && !isAdmin,
                StgdrvResponseDTO.Codes.INVALID_OPERATION, StgdrvMessage.MessageError.INVALID_PERMISSION);

        if (accountId.equals("me")) {
            accountId = me.getUid();
        }
        Account account = accountDAO.findByUid(accountId);
        Preconds.checkNotNull(account,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST));

        account.setVisible(false);
        account.setStatus(StgdrvData.AccountStatus.INACTIVE);
        account.setToken(TokenUtils.generateToken());
        account.setEmail(TokenUtils.generateToken()+"-deleted@stagedriving.com");
        List<AccountConnection> accountConnections = new ArrayList<>(account.getAccountConnections());
        if (accountConnections != null) {
            for (AccountConnection accountConnection : accountConnections) {
                accountConnectionDAO.delete(accountConnection);
                account.getAccountConnections().remove(accountConnection);
            }
        }
        accountDAO.edit(account);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(accountId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.ACCOUNT_DELETE);

        return Response.ok(responseDto).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{accountId}/connections")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve account connections",
            notes = "Retrieve account connections")
    public Response getAccountConnections(@Restricted(required = true) Account me,
                                          @PathParam("accountId") String accountId) {

        if (accountId.equalsIgnoreCase(StgdrvData.Account.ME))
            accountId = me.getUid();

        return connectionAccountResourse.getAccountConnections(accountId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{accountId}/billings")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve account billings methods",
            notes = "Retrieve account billings methods")
    public Response getAccountBillings(@Restricted(required = true) Account me,
                                       @PathParam("accountId") String accountId) {

        if (accountId.equalsIgnoreCase(StgdrvData.Account.ME))
            accountId = me.getUid();

        return billingAccountResourse.getAccountBillings(accountId);
    }

    @Path("/{accountId}/reviews")
    public ReviewResource reviewResource(@ApiParam(required = true) @PathParam("accountId") String accountId) {
        reviewResourse.setPathAccountId(accountId);
        return reviewResourse;
    }

    @Path("/{accountId}/likes")
    public ActionLikeResource likeResource(@ApiParam(required = true) @PathParam("accountId") String accountId) {
        actionLikeResource.setPathAccountId(accountId);
        return actionLikeResource;
    }

    @Path("/{accountId}/bookings")
    public BookingResource bookingResource(@ApiParam(required = true) @PathParam("accountId") String accountId) {
        bookingResource.setPathAccountId(accountId);
        return bookingResource;
    }

    @Path("/{accountId}/devices")
    public AccountDeviceResource deviceResource(@ApiParam(required = true) @PathParam("accountId") String accountId) {
        accountDeviceResource.setPathAccountId(accountId);
        return accountDeviceResource;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{accountId}/metas")
    @UnitOfWork(readOnly = false)
    //@Metered
    @ApiOperation(value = "Create user metas",
            notes = "Create user metas")
    public Response createAccountMetas(@Restricted(required = true) Account account,
                                       @PathParam("accountId") String accountId,
                                       @ApiParam(required = true) AccountMetaDTO metaDto) throws InterruptedException, NoSuchMethodException, IOException {

        Preconds.checkConditions(!account.getUid().equals(accountId), StgdrvMessage.MessageError.INVALID_PERMISSION);

        AccountMeta meta = null;

        List<AccountMeta> oldMeta = accountMetaDAO.findByMwrenchByAccount(metaDto.getMwrench(), accountId);
        if (!oldMeta.isEmpty()) {
            meta = oldMeta.get(0);
        }

        AccountMetaId accountMetaId = new AccountMetaId();
        accountMetaId.setAccountId(account.getId());

        if (meta == null) {
            meta = new AccountMeta();
            meta.setId(accountMetaId);
            meta.setUid(TokenUtils.generateUid());
            meta.setCreated(new Date());
            meta.setAccount(account);

            account.getAccountMetas().add(meta);
        }

        meta.setModified(new Date());
        meta.setMwrench(metaDto.getMwrench());
        meta.setMvalue(metaDto.getMvalue());
        accountMetaDAO.create(meta);
        accountDAO.edit(account);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(accountId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.META_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{accountId}/metas")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Edit user metas",
            notes = "Edit user metas")
    public Response editAccountMetas(@Restricted(required = true) Account me,
                                     @PathParam("accountId") String accountId,
                                     @ApiParam(required = true) AccountMetaDTO metaDto) throws InterruptedException, NoSuchMethodException, IOException {

        List<AccountMeta> metas = accountMetaDAO.findByMwrench(metaDto.getMwrench());

        Preconds.checkNotNull(metas,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.META_DOES_NOT_EXIST));

        Preconds.checkState(!metas.isEmpty(),
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.META_DOES_NOT_EXIST));

        metaUtils.merge(metas.get(0), accountMetaMapper.accountMetaDtoToAccountMeta(metaDto));
        metas.get(0).setModified(new Date());

        accountMetaDAO.edit(metas.get(0));
        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(accountId);
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.META_MODIFIED);

        return Response.ok(responseDto).build();
    }
}

