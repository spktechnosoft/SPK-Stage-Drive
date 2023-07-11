package com.stagedriving.modules.user.rest.l1;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountReviewDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountReview;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.mapper.decorators.AccountReviewMapperDecorator;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class ReviewAccountResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ReviewAccountResource.class);

    @Inject
    AccountReviewMapperDecorator reviewMapper;
    @Inject
    AccountReviewDAO reviewDAO;
    @Inject
    AccountDAO accountDAO;

    private AppConfiguration configuration;

    @Inject
    public ReviewAccountResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve user reviews",
            notes = "Retrieve user reviews")
    public Response getAccountReviews(String accountId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.CONNECTIONS_GET_ERROR);

        Account account = accountDAO.findByUid(accountId);
        Preconditions.checkNotNull(account,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST));

        List<AccountReview> reviews = reviewDAO.findByAccountByAccountId(account);

        return Response.ok(reviewMapper.reviewsToReviewDtos(reviews)).build();
    }
}
