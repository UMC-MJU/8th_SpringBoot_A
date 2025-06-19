package umc.study.service.reviewService;

import umc.study.domain.Review;

import java.util.Optional;

public interface ReviewQueryService {
    Review createReview(String body, Float score, Long memberId, Long storeId);

    Optional<Review> findReview(Long id);
}
