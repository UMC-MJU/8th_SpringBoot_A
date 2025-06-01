package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

  private final MemberMissionRepository memberMissionRepository;
  private final MissionRepository missionRepository;
  private final StoreRepository storeRepository;

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

  @Override
  public Page<MemberMission> getMissionList(Long userId, Integer page, Integer size) {
    Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberIdAndStatus(userId, MissionStatus.CHALLENGING, PageRequest.of(page, size));
//    Page<Mission> MissionPage = missionRepository.findMissionsByMemberMissionList(memberMission, PageRequest.of(page, 5));
    return memberMissionPage;
  }

  @Override
  public Page<Mission> getMissionListByStore(Long storeID, Integer page, Integer size) {
    Page<Mission> storeMissionPage = storeRepository.findById(storeID, PageRequest.of(page, size));
    return storeMissionPage;
  }
}
