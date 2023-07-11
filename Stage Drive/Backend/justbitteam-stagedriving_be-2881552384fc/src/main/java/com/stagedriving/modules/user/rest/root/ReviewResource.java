package com.stagedriving.modules.user.rest.root;

import com.codahale.metrics.annotation.Metered;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.justbit.commons.TokenUtils;
import com.stagedriving.commons.StgdrvMessage;
import com.stagedriving.commons.v1.base.StgdrvResponseDTO;
import com.stagedriving.commons.v1.resources.AccountDTO;
import com.stagedriving.commons.v1.resources.ReviewDTO;
import com.stagedriving.modules.commons.conf.AppConfiguration;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.daos.AccountReviewDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountReview;
import com.stagedriving.modules.commons.exception.Preconds;
import com.stagedriving.modules.commons.exception.StgdrvInvalidRequestException;
import com.stagedriving.modules.commons.interceptor.restricted.Restricted;
import com.stagedriving.modules.commons.mapper.decorators.AccountMapperDecorator;
import com.stagedriving.modules.commons.mapper.decorators.AccountReviewMapperDecorator;
import com.stagedriving.modules.commons.utils.review.ReviewUtils;
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
@Path("/v1/reviews")
@Api(value = "reviews", description = "Reviews resource")
public class ReviewResource {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ReviewResource.class);

    private String pathAccountId;

    public String getPathAccountId() {
        return pathAccountId;
    }

    public void setPathAccountId(String pathAccountId) {
        this.pathAccountId = pathAccountId;
    }

    @Inject
    private ReviewUtils reviewUtils;
    @Inject
    private AccountReviewMapperDecorator reviewMapper;
    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private AccountReviewDAO reviewDAO;
    @Inject
    private AccountDAO accountDAO;

    private AppConfiguration configuration;

    @Inject
    public ReviewResource(AppConfiguration configuration) {
        this.configuration = configuration;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "New review",
            notes = "New review",
            response = StgdrvResponseDTO.class)
    public Response createReview(@Restricted(required = true) Account me,
                                 @ApiParam(required = true) @Valid ReviewDTO reviewDto) throws Exception {

        if (pathAccountId != null) {
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setId(pathAccountId);
            reviewDto.setAccount(accountDTO);
        }

//        Preconditions.checkArgument(reviewDto.getAccount().getId() != null, new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_ID_NULL));
        Account account = accountDAO.findByUid(reviewDto.getAccount().getId());
        Preconditions.checkArgument(account != null, new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_ID_NULL));

        AccountReview review = null;
        if (reviewDto.getId() != null) {
            review = reviewDAO.findByUid(reviewDto.getId());
            Preconds.checkArgument(review != null, new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_ID_NULL));
        } else {
            review = new AccountReview();
            review.setAccountByAccountId(account);
            review.setAccountByAuthorAccountId(me);
            review.setUid(TokenUtils.generateUid());
            review.setCreated(new Date());
            review.setModified(new Date());

            account.getAccountReviewsForAccountId().add(review);
            accountDAO.edit(account);

            me.getAccountReviewsForAuthorAccountId().add(review);
            accountDAO.edit(me);
        }

        review.setModified(new Date());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStar(reviewDto.getStar());
        reviewDAO.create(review);

        // TODO Move to async block
        List<AccountReview> reviews = reviewDAO.findByAccountByAccountId(account);
        Double newRating = 0.0;
        for (AccountReview rev : reviews) {
            newRating += rev.getStar();
        }
        newRating /= reviews.size();
        account.setRating(newRating);

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setId(review.getUid());
        responseDto.setCode(StgdrvResponseDTO.Codes.IS_OK);
        responseDto.setMessage(StgdrvMessage.OperationSuccess.REVIEW_CREATE);

        return Response.ok(responseDto).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{reviewId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Modify existing review",
            notes = "Modify existing review",
            response = StgdrvResponseDTO.class)
    public Response modifyReview(@Restricted(required = true) Account me,
                                  @ApiParam(required = true) ReviewDTO reviewDto,
                                  @PathParam("reviewId") String reviewId) throws Exception {

        reviewDto.setId(reviewId);
        return createReview(me, reviewDto);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieves reviews",
            notes = "Retrieves reviews",
            response = ReviewDTO.class,
            responseContainer = "List")
    public Response getReviews(@ApiParam(value = "Response page size") @QueryParam("limit") String limit,
                               @ApiParam(value = "Response page index") @QueryParam("page") String page,
                               @QueryParam("accountId") String accountId) {

        if (pathAccountId != null) {
            accountId = pathAccountId;
        }

        List<AccountReview> reviews = null;

        if (accountId != null) {
            Account account = accountDAO.findByUid(accountId);
            Preconditions.checkNotNull(account,
                    new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.ACCOUNT_DOES_NOT_EXIST));

            reviews = reviewDAO.findByAccountByAccountId(account);
        } else {
            reviews = reviewDAO.findAll();
        }

        return Response.ok(reviewMapper.reviewsToReviewDtos(reviews)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{reviewId}")
    @UnitOfWork(readOnly = true)
    //@Metered
    @ApiOperation(value = "Retrieve review",
            notes = "Retrieve review",
            response = ReviewDTO.class)
    public Response getReview(@PathParam("reviewId") String reviewId) {

        AccountReview review = reviewDAO.findByUid(reviewId);
        Preconditions.checkNotNull(review,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.REVIEW_DOES_NOT_EXIST));

        ReviewDTO reviewDto = reviewMapper.reviewToReviewDto(review);
        Account account = review.getAccountByAccountId();
        if (account != null) {
            AccountDTO accountDto = accountMapper.accountToAccountDto(account);
            reviewDto.setAccount(accountDto);
        }
        Account authorAccount = review.getAccountByAuthorAccountId();
        if (authorAccount != null) {
            AccountDTO accountDto = accountMapper.accountToAccountDto(authorAccount);
            reviewDto.setAuthor(accountDto);
        }

        return Response.ok(reviewDto).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{reviewId}")
    @UnitOfWork
    //@Metered
    @ApiOperation(value = "Delete existing review",
            notes = "Delete existing review",
            response = StgdrvResponseDTO.class)
    public Response deleteReview(@Restricted(required = true) Account me,
                                 @ApiParam(required = true) @PathParam("reviewId") String reviewId) {

        StgdrvResponseDTO responseDto = new StgdrvResponseDTO();
        responseDto.setCode(StgdrvResponseDTO.Codes.INTERNAL_ERROR);
        responseDto.setMessage(StgdrvMessage.OperationError.REVIEW_DELETE_ERROR);

        AccountReview review = reviewDAO.findByUid(reviewId);
        Preconditions.checkNotNull(review,
                new StgdrvInvalidRequestException(Response.Status.BAD_REQUEST, StgdrvResponseDTO.Codes.INVALID_REQUEST, StgdrvMessage.MessageError.REVIEW_DOES_NOT_EXIST));

        reviewDAO.delete(review);

        return Response.ok(responseDto).build();
    }
}
