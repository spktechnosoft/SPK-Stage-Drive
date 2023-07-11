package com.stagedriving.modules.commons.utils.review;

import com.google.inject.Inject;
import com.stagedriving.modules.commons.ds.entities.AccountReview;

/**
 * Project stage driving api
 * Author: man
 * Date: 17/10/2018
 */
public class ReviewUtils {

    @Inject
    public ReviewUtils() {
    }
    
    public AccountReview merge(AccountReview oldAccountReview, AccountReview newAccountReview) {

        oldAccountReview.setTitle(newAccountReview.getTitle() != null ? newAccountReview.getTitle() : oldAccountReview.getTitle());
        oldAccountReview.setContent(newAccountReview.getContent() != null ? newAccountReview.getContent() : oldAccountReview.getContent());
        oldAccountReview.setStar(newAccountReview.getStar() != null ? newAccountReview.getStar() : oldAccountReview.getStar());

        return oldAccountReview;
    }
}


