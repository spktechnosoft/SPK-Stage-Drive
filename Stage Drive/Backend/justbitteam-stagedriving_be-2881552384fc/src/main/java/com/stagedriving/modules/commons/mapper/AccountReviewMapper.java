package com.stagedriving.modules.commons.mapper;

import com.stagedriving.commons.v1.resources.ReviewDTO;
import com.stagedriving.modules.commons.ds.entities.AccountReview;
import com.stagedriving.modules.commons.utils.DateUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Project stagedriving api
 * Author: man
 * Date: 16/10/18
 * Time: 18:36
 */
@Mapper
public interface AccountReviewMapper {

    @Mappings({
            @Mapping(source = "uid", target = "id"),
            @Mapping(target = "account", ignore = true),
            @Mapping(target = "created", dateFormat = DateUtils.DATE_PATTERN),
            @Mapping(target = "modified", dateFormat = DateUtils.DATE_PATTERN)
    })
    ReviewDTO reviewToReviewDto(AccountReview review);

    List<ReviewDTO> reviewsToReviewDtos(List<AccountReview> reviews);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "modified", ignore = true)
    })
    AccountReview reviewDtoToReview(ReviewDTO reviewDTO);

    List<AccountReview> reviewDtosToReviews(List<ReviewDTO> reviewDTOs);
}