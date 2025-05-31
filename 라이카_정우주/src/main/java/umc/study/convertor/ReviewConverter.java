package umc.study.convertor;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResponseDTO.AddReviewResultDTO toAddReviewResultDTO(Review review) {
        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .reviewId(review.getId())
                .body(review.getBody())
                .score(review.getScore())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .createdAt(LocalDateTime.now())
                .build();
    }
}