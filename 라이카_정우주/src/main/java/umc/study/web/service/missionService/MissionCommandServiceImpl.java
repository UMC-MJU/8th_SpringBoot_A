package umc.study.web.service.missionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.convertor.MissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.memberMission.MemberMissionRepository;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.missionRepository.MissionRepository;
import umc.study.repository.storeRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDTO;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public MemberMission addMission(MissionRequestDTO.AddMissionDto request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission newMission = MissionConverter.toMission(request, store);
        Mission savedMission = missionRepository.save(newMission);

        MemberMission memberMission = MissionConverter.toMemberMission(savedMission, member);
        return memberMissionRepository.save(memberMission);
    }

    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }

    @Override
    public List<Mission> findMemberMissionsByStatus(Long memberId, MissionStatus status) {
        return missionRepository.findMissionsByMemberId(memberId, status);
    }
}