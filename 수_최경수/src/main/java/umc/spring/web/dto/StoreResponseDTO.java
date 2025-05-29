package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class StoreResponseDTO {
  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReviewPreViewListDTO{
    List<ReviewPreViewDTO> reviewList;
    Integer listSize;
    Integer totalPage;
    Long totalElements;
    Boolean isFirst;
    Boolean isLast;
  }

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReviewPreViewDTO{
    String ownerNickname; // 닉네임
    Float score;          // 리뷰의 점수
    String body;          // 리뷰 상세 내용
    LocalDate createdAt;  // 리뷰 작성 날짜
  }
}
