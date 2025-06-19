package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

// 컨버터의 역할 : Entity to DTO
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

  public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(MemberMission memberMission){
    Mission mission = memberMission.getMission();
    return MissionResponseDTO.MissionPreViewDTO.builder()
            .missionSpec(mission.getMissionSpec())
            .reward(mission.getReward())
            .deadline(mission.getDeadline())
            .createdAt(LocalDate.from(mission.getCreatedAt()))
            .build();
  }

  public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<MemberMission> memberMissionPage){
    List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = memberMissionPage.stream()
            .map(MissionConverter::missionPreViewDTO)
            .collect(Collectors.toList());

    return MissionResponseDTO.MissionPreViewListDTO.builder()
            .isFirst(memberMissionPage.isLast())
            .isLast(memberMissionPage.isLast())
            .totalPage(memberMissionPage.getTotalPages())
            .totalElements(memberMissionPage.getTotalElements())
            .listSize(missionPreViewDTOList.size())
            .missionList(missionPreViewDTOList)
            .build();
  }
}
