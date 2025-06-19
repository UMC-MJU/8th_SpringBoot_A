package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{
  private final ReviewRepository reviewRepository;
  private final MemberRepository memberRepository;
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

  @Override
  public Page<Review> getReviewList(Long userId, Integer page, Integer size) {
    Member member = memberRepository.findById(userId).get();
    Page<Review> reviewPage = reviewRepository.findAllByMember(member, PageRequest.of(page, size));
    return reviewPage;
  }
}
