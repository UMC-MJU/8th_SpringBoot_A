package umc.study.service.missionService;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {

    Optional<Mission> findMission(Long id);

    List<Mission> findMemberMissionsByStatus(Long memberId, MissionStatus status);
}
