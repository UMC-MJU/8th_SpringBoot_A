package umc.study.convertor;

import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.mapping.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.ChallengeMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberName(memberMission.getMember().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MemberMissionResponseDTO.CompleteMissionResultDTO toCompleteMissionResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.CompleteMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberName(memberMission.getMember().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
