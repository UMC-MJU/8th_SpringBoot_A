package umc.study.service.reviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Review;
import umc.study.repository.reviewRepository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review createReview(String body, Float score, Long memberId, Long storeId) {
        Review review = reviewRepository.createReview(body, score, memberId, storeId);
        return review;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> findReview(Long id) {
        return reviewRepository.findById(id);
    }
}