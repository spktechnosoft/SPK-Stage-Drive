package com.stagedriving.modules.user.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvData;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.AccountDeviceDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountDeviceDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountDevice;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.AccountDeviceMapperImpl;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
@Path("/v1/accounts/devices")
@Api(value = "devices", description = "Account devices resource")
public class AccountDeviceResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AccountDeviceResource.class);

    private String pathAccountId;

    public String getPathAccountId() {
        return pathAccountId;
    }

    public void setPathAccountId(String pathAccountId) {
        this.pathAccountId = pathAccountId;
    }

    @Inject
    private AccountDeviceMapperImpl deviceMapper;
    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private AccountDeviceDAO deviceDAO;
    @Inject
    private AccountDAO accountDAO;

    private AppConfiguration configuration;

    @Inject
    public AccountDeviceResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    ////@Metered
    @ApiOperation(value = "New device",
            notes = "New device",
            response = StgdrvResponseDTO.class)
    public Response createDevice(@Restricted(required = true) Account me,
                                 @ApiParam(required = true) @Valid AccountDeviceDTO deviceDto) throws Exception {

        Account account = me;

        if (pathAccountId != null) {
            account = accountDAO.findByUid(pathAccountId);
        }

        AccountDevice accountDevice = null;
        if (deviceDto.getId() != null) {
            accountDevice = deviceDAO.findByUid(deviceDto.getId());
            Preconds.checkConditions(accountDevice == null, StgdrvData.StgdrvError.INVALID_ID, StgdrvMessage.MessageError.INVALID_DEVICE);
        }

        if (accountDevice == null) {
            List<AccountDevice> oldDevs = deviceDAO.findByAccountAndDeviceId(account, null, deviceDto.getOs());
            if (oldDevs != null && !oldDevs.isEmpty()) {
                oldDevs.get(0).setDeviceid(deviceDto.getToken());
                oldDevs.get(0).setModified(new Date());
                deviceDAO.edit(oldDevs.get(0));

                StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
                responseDto.setId(oldDevs.get(0).getUid());
                responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
                responseDto.setMessage(StgdrvMessage.OperationSuccess.DEVICE_CREATE);
                return Response.ok(responseDto).build();
            }
        }

        if (accountDevice == null) {
            accountDevice = new AccountDevice();
            accountDevice.setUid(TokenUtils.generateUid());
            accountDevice.setCreated(new Date());
        }

        accountDevice.setOs(deviceDto.getOs());
        accountDevice.setDeviceid(deviceDto.getToken());
        accountDevice.setModified(new Date());
        accountDevice.setAccount(account);

        deviceDAO.edit(accountDevice);

        account.getAccountDevices().add(accountDevice);
        accountDAO.edit(account);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(accountDevice.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.DEVICE_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{deviceId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing device",
            notes = "Modify existing device",
            response = StgdrvResponseDTO.class)
    public Response modifyDevice(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) AccountDeviceDTO deviceDto,
                                  @PathParam("deviceId") String deviceId) throws Exception {

        if (pathAccountId != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(pathAccountId);
            deviceDto.setAccount(accountDTO);
        }

        deviceDto.setId(deviceId);

        return createDevice(me, deviceDto);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @UnitOfWork(readOnly = true)
//    @ApiOperation(value = "Retrieves devices",
//            notes = "Retrieves devices",
//            response = DeviceDTO.class,
//            responseContainer = "List")
//    public Response getDevices(@ApiParam(value = "Response page size") @QueryParam("limit") String limit,
//                               @ApiParam(value = "Response page index") @QueryParam("page") String page,
//                               @QueryParam("accountId") String accountId) {
//
//        if (pathAccountId != null) {
//            accountId = pathAccountId;
//        }
//
//        List<AccountDevice> devices = null;
//
//        if (accountId != null) {
//            Account account = accountDAO.findByUid(accountId);
//            Preconditions.checkNotNull(account,
//                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST));
//
//            devices = deviceDAO.findByAccountByAccountId(account);
//        } else {
//            devices = deviceDAO.findAll();
//        }
//
//        return Response.ok(deviceMapper.devicesToDeviceDtos(devices)).build();
//    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("/{deviceId}")
//    @UnitOfWork(readOnly = true)
//    @ApiOperation(value = "Retrieve device",
//            notes = "Retrieve device",
//            response = DeviceDTO.class)
//    public Response getDevice(@PathParam("deviceId") String deviceId) {
//
//        AccountDevice device = deviceDAO.findByUid(deviceId);
//        Preconditions.checkNotNull(device,
//                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.REVIEW_DOES_NOT_EXIST));
//
//        DeviceDTO deviceDto = deviceMapper.deviceToDeviceDto(device);
//        Account account = device.getAccountByAccountId();
//        if (account != null) {
//            AccountDTO accountDto = accountMapper.accountToAccountDto(account);
//            deviceDto.setAccount(accountDto);
//        }
//        Account authorAccount = device.getAccountByAuthorAccountId();
//        if (authorAccount != null) {
//            AccountDTO accountDto = accountMapper.accountToAccountDto(authorAccount);
//            deviceDto.setAuthor(accountDto);
//        }
//
//        return Response.ok(deviceDto).build();
//    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{deviceId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing device",
            notes = "Delete existing device",
            response = StgdrvResponseDTO.class)
    public Response deleteDevice(@Restricted(required = true) Account me,
                                 @ApiParam(required = true) @PathParam("deviceId") String deviceId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.DEVICE_DELETE);

        AccountDevice device = deviceDAO.findByUid(deviceId);
        Preconditions.checkNotNull(device,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.INVALID_DEVICE));

        Preconds.checkConditions(!me.getId().equals(device.getAccount().getId()), StgdrvResponseDTO.Codes.PERMISSION_DENIED, StgdrvMessage.MessageError.INVALID_PERMISSION);

        deviceDAO.delete(device);

        return Response.ok(responseDto).build();
    }
}
