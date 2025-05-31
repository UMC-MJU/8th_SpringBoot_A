package umc.study.web.service.reviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.ReviewHandler;
import umc.study.domain.Review;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.reviewRepository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review createReview(String body, Float score, Long memberId, Long storeId) {
        if (!memberRepository.existsById(memberId)) {
            throw new ReviewHandler(ErrorStatus.MEMBER_NOT_FOUND);
        }
        Review review = reviewRepository.createReview(body, score, memberId, storeId);
        return review;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> findReview(Long id) {
        return reviewRepository.findById(id);
    }
}