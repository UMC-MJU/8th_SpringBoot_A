package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;

public class ReviewConverter {
  public static ReviewResponseDTO.JoinResultDTO toJoinResultDTO(Review review){
    return ReviewResponseDTO.JoinResultDTO.builder()
            .reviewId(review.getId())
            .content(review.getBody())
            .createdAt(LocalDateTime.now())
            .build();
  }

  public static Review toReview(ReviewRequestDTO.JoinDto request, Store store){
    return Review.builder()
            .title(request.getTitle())
            .body(request.getBody())
            .score(request.getScore())
            .store(store)
            .build();
  }
}
