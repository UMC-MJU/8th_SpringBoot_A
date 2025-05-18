package umc.spring.service.ReviewService;

import umc.spring.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
  Optional<Review> findReview(Long id);
  List<Review> findReviewsByScore(Float score);
}
