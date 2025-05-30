package umc8th.spring.web.dto.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    //회원 가입 완료 후, 보내줄것.
    public static class joinResultDTO {
        Long memberId; //생성된 멤버의 아이디
        LocalDateTime createdAt; //멤버가 생성된 시점
    }
}
