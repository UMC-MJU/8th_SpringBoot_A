package umc8th.spring.web.dto.memberMission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc8th.spring.domain.enums.MissionStatus;

import java.time.LocalDateTime;

public class MemberMissionResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class addMissionResult {
        Long memberMissionId; //만들어진 맴버 미션 아이디
        LocalDateTime createdAt; //만들어진 시기
    }


    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class toCompleteResult {
        Long memberMissionId; //수정된 멤버 미션의 아이디
        MissionStatus editedStatus; //수정 결과 확인용 미션 상태 -> 항상 COMPLETE 여야 겠지..?
    }

}
