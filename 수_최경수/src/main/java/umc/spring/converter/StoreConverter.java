package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

// Converter(컨버터)의 경우 클라이언트 요청 데이터를 JPA에서 처리하기 위한 Entity로 만드는, DTO to Entity
public class StoreConverter {
  public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
    return StoreResponseDTO.ReviewPreViewDTO.builder()
            .ownerNickname(review.getMember().getName())
            .score(review.getScore())
            .createdAt(review.getCreatedAt().toLocalDate())
            .body(review.getBody())
            .build();
  }
  public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

    List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
            .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

    return StoreResponseDTO.ReviewPreViewListDTO.builder()
            .isLast(reviewList.isLast())
            .isFirst(reviewList.isFirst())
            .totalPage(reviewList.getTotalPages())
            .totalElements(reviewList.getTotalElements())
            .listSize(reviewPreViewDTOList.size())
            .reviewList(reviewPreViewDTOList)
            .build();
  }
}
