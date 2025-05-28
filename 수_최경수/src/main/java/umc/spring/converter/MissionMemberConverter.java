package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;

public class MissionMemberConverter {
  public static MissionResponseDTO.JoinMemberResultDTO toJoinResultDTO(Mission mission){
    return MissionResponseDTO.JoinMemberResultDTO.builder()
            .missionId(mission.getId())
            .build();
  }

  public static MemberMission toMemberMission(Mission mission, Member member){
    return MemberMission.builder()
            .status(MissionStatus.CHALLENGING)
            .mission(mission)
            .member(member)
            .build();
  }
}
