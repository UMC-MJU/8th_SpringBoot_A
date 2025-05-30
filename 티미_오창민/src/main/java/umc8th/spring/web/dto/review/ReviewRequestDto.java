package umc8th.spring.web.dto.review;

import lombok.Getter;

public class ReviewRequestDto {

    //리뷰를 가게에 추가 하는 요청을 담기 위한 DTO
    @Getter
    public static class addReview{
        Long memberId; //리뷰를 작성한 회원의 아이디
        Long storeId; //리뷰 대상이 되는 상점의 아이디
        String title; //리뷰 내용
        Float score; //리뷰 점수
        //사진은 ... 잘 모르겠다.

    }


}
