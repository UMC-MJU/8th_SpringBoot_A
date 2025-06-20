package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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


  public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
    return ReviewResponseDTO.ReviewPreViewDTO.builder()
            .ownerNickname(review.getMember().getName())
            .score(review.getScore())
            .createdAt(review.getCreatedAt().toLocalDate())
            .body(review.getBody())
            .build();
  }

  public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {
    List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
            .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

    return ReviewResponseDTO.ReviewPreViewListDTO.builder()
            .isLast(reviewList.isLast())
            .isFirst(reviewList.isFirst())
            .totalPage(reviewList.getTotalPages())
            .totalElements(reviewList.getTotalElements())
            .listSize(reviewPreViewDTOList.size())
            .reviewList(reviewPreViewDTOList)
            .build();
  }


}
