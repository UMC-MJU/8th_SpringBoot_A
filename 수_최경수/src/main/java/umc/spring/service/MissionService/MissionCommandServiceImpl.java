package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.MissionMemberConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
  private final MemberRepository memberRepository;
  private final MemberMissionRepository memberMissionRepository;
  private final MissionRepository missionRepository;
  private final StoreRepository storeRepository;

  @Override
  @Transactional
  public Mission joinMission(Long storeId, MissionRequestDTO.JoinDto request) {
    Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
    Mission mission = MissionConverter.toMission(request, store);
    return missionRepository.save(mission);
  }

  @Override
  @Transactional
  public Mission joinPreferMission(MissionRequestDTO.JoinMemberDto request) {

    Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
    List<MemberMission> memberMissions = memberMissionRepository.findByMemberAndMission(member, mission);

    MemberMission memberMission;

    if (memberMissions.isEmpty()) {
      memberMission = MissionMemberConverter.toMemberMission(mission, member);
    } else {
      // 필요한 경우 하나만 선택 (예: 가장 최근 것)
      memberMission = memberMissions.get(0); // 또는 비즈니스 로직에 맞게 필터링
    }

    if(request.getStatus() == 0)
      memberMission.setStatus(MissionStatus.CHALLENGING);
    else
      memberMission.setStatus(MissionStatus.COMPLETE);

    mission = memberMissionRepository.save(memberMission).getMission();
    return mission;
  }

}
