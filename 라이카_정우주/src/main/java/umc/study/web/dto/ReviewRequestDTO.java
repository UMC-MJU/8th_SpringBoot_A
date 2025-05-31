package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class AddReviewDto {
        private String body;
        private Float score;
        private Long memberId;

        @ExistStore
        private Long storeId;
    }
}