package umc.study.web.dto.mapping;

import lombok.Getter;
import umc.study.validation.annotation.ExistMission;

public class MemberMissionRequestDTO {

    @Getter
    public static class ChallengeMissionDto {
        private Long memberId;

        @ExistMission
        private Long missionId;
    }

    @Getter
    public static class CompleteMissionDto {
        private Long memberId;
    }
}