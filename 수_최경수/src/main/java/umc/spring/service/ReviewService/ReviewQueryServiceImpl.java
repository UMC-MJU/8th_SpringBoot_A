package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Review;
import umc.spring.repository.ReviewRepository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{
  private final ReviewRepository reviewRepository;
  @Override
  public Optional<Review> findReview(Long id) {
    return reviewRepository.findById(id);
  }

  @Override
  public List<Review> findReviewsByScore(Float score) {
    List<Review> filteredReview = reviewRepository.dynamicQueryWithBooleanBuilder(score);
    filteredReview.forEach(review -> System.out.println("Review: " + review));
    return filteredReview;
  }
}
