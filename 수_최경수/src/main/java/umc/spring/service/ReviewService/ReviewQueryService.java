package umc.spring.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
  Optional<Review> findReview(Long id);
  List<Review> findReviewsByScore(Float score);
  Page<Review> getReviewList(Long userId, Integer page, Integer size);
}
