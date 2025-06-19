package umc.spring.service.MissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {
  Optional<Mission> findMission(Long id);
  List<Mission> findMissionByIdAndStatus(Long userID, MissionStatus status);
  Page<MemberMission> getMissionList(Long userID, Integer page, Integer size);
  Page<Mission> getMissionListByStore(Long storeID, Integer page, Integer size);
}
