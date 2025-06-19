package umc.study.repository.reviewRepository;

import umc.study.domain.Review;

public interface ReviewRepositoryCustom {
    Review createReview(String body, Float score, Long memberId, Long storeId);
}
