package umc8th.spring.web.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class addReviewResult {
        Long reviewId; //생성된 리뷰 아이디
        LocalDateTime createdAt; //생성된 시간
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    //내가 작성한 리뷰 목록 조회 결과를 담는 dto
    public static class GetReviewsResult{
        String storeName;//리뷰를 작성한 가게 이름
        String name; //작성자 이름 -> 똑같은 사람이니까 중복될 필요 x
        List<GetReviewListDetail> reviews; //리뷰 목록
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    //리뷰 목록의 필요한 정보를 담는 detailDto
    public static class GetReviewListDetail {
        LocalDateTime date; // 리뷰 작성 날짜
        Float score; // 리뷰 점수
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReviewPreViewDTO {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }
}
