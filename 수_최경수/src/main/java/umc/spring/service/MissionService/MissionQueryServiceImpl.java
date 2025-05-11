package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

  private final MissionRepository missionRepository;

  @Override
  public Optional<Mission> findMission(Long id) {
    return missionRepository.findById(id);
  }

  @Override
  public List<Mission> findMissionByIdAndStatus(Long userID, MissionStatus status) {
    List<Mission> filteredMission = missionRepository.dynamicQueryWithBooleanBuilder(userID, status);
    filteredMission.forEach(mission -> System.out.println("Mission: " + mission));
    return filteredMission;
  }
}
