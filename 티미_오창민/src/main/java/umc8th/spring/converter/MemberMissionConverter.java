package umc8th.spring.converter;


import umc8th.spring.domain.MemberMission;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.web.dto.memberMission.MemberMissionRequestDto;
import umc8th.spring.web.dto.memberMission.MemberMissionResponseDto;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMissionResponseDto.addMissionResult toAddMemberMissionResult(MemberMission memberMission) {
        return MemberMissionResponseDto.addMissionResult
                .builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now()).build();
    }


    public static MemberMissionResponseDto.toCompleteResult toCompleteResult(MemberMission memberMission) {
        return MemberMissionResponseDto.toCompleteResult
                .builder()
                .memberMissionId(memberMission.getId())
                .editedStatus(memberMission.getStatus())
                .build();
    }


    public static MemberMission toMemberMission(MemberMissionRequestDto.addMemberMission request) {
        return MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
