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
  public Mission joinPreferMission(MissionRequestDTO.JoinMemberDto request) {
    Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
    MemberMission memberMission = MissionMemberConverter.toMemberMission(mission, member);
    memberMission.setMissionAndMember(mission, member);

    return memberMissionRepository.save(memberMission).getMission();
  }

}
