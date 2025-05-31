package umc.study.web.dto;

import lombok.Getter;
import umc.study.validation.annotation.ExistMission;

public class MemberMissionRequestDTO {

    @Getter
    public static class ChallengeMissionDto {
        private Long memberId;

        @ExistMission
        private Long missionId;
    }
}