package umc8th.spring.web.dto.memberMission;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MemberMissionRequestDto {

    @Getter
    //맴버의 아이디와 미션 아이디를 받아
    //멤버 미션을 생성해 리스트에 추가하기 위한 정보를 담는 dto
    public static class addMemberMission {
        @NotNull
        Long memberId;
        @NotNull
        Long missionId;
    }

    @Getter
    //멤버의 진행중인 미션을 진행 완료로 바꾸기 요청 dto
    public static class toComplete {
        @NotNull
        Long memberMissionId;

    }
}
