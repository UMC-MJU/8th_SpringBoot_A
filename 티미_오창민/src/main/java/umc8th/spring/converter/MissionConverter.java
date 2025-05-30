package umc8th.spring.converter;


import umc8th.spring.domain.Member;
import umc8th.spring.domain.Mission;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.web.dto.mission.MissionRequestDto;
import umc8th.spring.web.dto.mission.MissionResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDto.addMissionResult toAddMissionResult(Mission mission) {
        return MissionResponseDto.addMissionResult.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MissionResponseDto.getMissionListByStore toGetMissionListByStoreResult(Long storeId, List<Mission> missions) {
        return MissionResponseDto.getMissionListByStore.builder()
                .storeId(storeId)
                .missionList(toMissionListByStoreDetailDto(missions)).build();
    }


    public static List<MissionResponseDto.GetMissionListByStoreDetail> toMissionListByStoreDetailDto(List<Mission> missions) {
        return missions.stream()
                .map(mission -> MissionResponseDto.GetMissionListByStoreDetail.builder()
                        .date(mission.getCreatedAt())
                        .reward(mission.getReward())
                        .deadline(mission.getDeadline()).build()).collect(Collectors.toList());
    }

    public static MissionResponseDto.getChallengingMissionList toChallengingMissionResult(Member member, List<Mission> missions) {
        return MissionResponseDto.getChallengingMissionList
                .builder()
                .memberId(member.getId())
                .memberMissions(toMemberMissionDetail(missions)).build();
    }

    public static List<MissionResponseDto.ChallengingMissionListDetail> toMemberMissionDetail(List<Mission> missions) {
        return missions.stream()
                .map(mission -> MissionResponseDto.ChallengingMissionListDetail.builder()
                        .date(mission.getCreatedAt())
                        .reward(mission.getReward())
                        .deadline(mission.getDeadline())
                        .status(MissionStatus.CHALLENGING).build()).collect(Collectors.toList());
    }

    //요청을 받아 객체로 반환
    public static Mission toMission(MissionRequestDto.addMission request) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .build();
    }



}
