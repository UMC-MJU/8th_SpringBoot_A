package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public class MissionConverter {
  public static MissionResponseDTO.JoinResultDTO toJoinResultDTO(Mission mission){
    return MissionResponseDTO.JoinResultDTO.builder()
            .missionId(mission.getId())
            .deadline(mission.getDeadline())
            .build();
  }

  public static Mission toMission(MissionRequestDTO.JoinDto request, Store store){
    return Mission.builder()
            .reward(request.getReward())
            .deadline(request.getDeadline())
            .missionSpec(request.getMissionSpec())
            .store(store)
            .build();
  }
}
