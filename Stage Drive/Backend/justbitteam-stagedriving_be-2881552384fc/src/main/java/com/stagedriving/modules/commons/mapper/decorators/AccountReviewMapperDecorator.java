package com.stagedriving.modules.commons.mapper.decorators;

import com.google.inject.Inject;
import com.stagedriving.commons.v1.resources.ReviewDTO;
import com.stagedriving.modules.commons.ds.daos.AccountDAO;
import com.stagedriving.modules.commons.ds.entities.Account;
import com.stagedriving.modules.commons.ds.entities.AccountReview;
import com.stagedriving.modules.commons.mapper.AccountReviewMapperImpl;

import java.util.ArrayList;
import java.util.List;

public class AccountReviewMapperDecorator extends AccountReviewMapperImpl {

    @Inject
    private AccountMapperDecorator accountMapper;
    @Inject
    private AccountDAO accountDAO;

    public ReviewDTO reviewToReviewDto(AccountReview review) {
        ReviewDTO reviewDTO = super.reviewToReviewDto(review);

        Account account = review.getAccountByAccountId();
        reviewDTO.setAccount(accountMapper.accountToAccountDto(account));

        Account authorAccount = review.getAccountByAuthorAccountId();
        if (authorAccount == null) {
            authorAccount = account;
        }
        reviewDTO.setAuthor(accountMapper.accountToAccountDto(authorAccount));

        return reviewDTO;
    }

    public List<ReviewDTO> reviewsToReviewDtos(List<AccountReview> reviews) {
        if ( reviews == null ) {
            return null;
        }

        List<ReviewDTO> list = new ArrayList<ReviewDTO>( reviews.size() );
        for ( AccountReview accountReview : reviews ) {
            list.add( reviewToReviewDto( accountReview ) );
        }

        return list;
    }
}
