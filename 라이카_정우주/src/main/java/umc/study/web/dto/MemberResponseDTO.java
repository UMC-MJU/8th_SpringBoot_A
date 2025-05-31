package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberResponseDTO {

    //DTO에도 빌더패턴을 쓴다.
    //Request는 프론트에서 객체를 받는것이기에 빌더 패턴을 사용하지 않아도 되지만, Response는 우리가 불러오는 것이기에 빌더 패턴을 사용한다.
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JoinResultDTO{
        Long memberId;
        LocalDateTime createdAt;
    }
}