package umc.study.web.service.missionService;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MissionRequestDTO;

import java.util.List;
import java.util.Optional;

public interface MissionCommandService {

    MemberMission addMission(MissionRequestDTO.AddMissionDto request);

    Optional<Mission> findMission(Long id);

    List<Mission> findMemberMissionsByStatus(Long memberId, MissionStatus status);
}
