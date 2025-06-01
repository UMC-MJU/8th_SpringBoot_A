package umc8th.spring.service.serviceIml;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring.apiPayload.code.status.ErrorStatus;
import umc8th.spring.apiPayload.exception.handler.StoreHandler;
import umc8th.spring.converter.MissionConverter;
import umc8th.spring.domain.Member;
import umc8th.spring.domain.Mission;
import umc8th.spring.domain.Store;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.repository.MissionRepository;
import umc8th.spring.repository.StoreRepository;
import umc8th.spring.service.MissionQueryService;
import umc8th.spring.web.dto.mission.MissionRequestDto;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository; //jpa 기본 리포지토리와 커스텀한 메소드까지 가져오기
    private final StoreRepository storeRepository;
    @Override
    public List<Mission> getMissionsByMemberAndStatus(MissionStatus status, Member member, Integer page) {
        List<Mission> missions = missionRepository.getMissionsByMemberAndStatus(status, member, page);
        return missions;
    }

    @Override
    public List<Mission> getMissionByStore(Long storeId, Integer page) {
        //가게 조회
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        return missionRepository.findAllByStore(store, PageRequest.of(page, 10)).stream().toList();

    }

    @Override
    public Mission addMission(MissionRequestDto.addMission request) {
        //미션 생성 -> 아직 상점은 할당 안됨
        Mission mission = MissionConverter.toMission(request);
        //상점 조회
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        //연관관계 매핑
        mission.setStore(store);
        //저장 및 반환
        return missionRepository.save(mission);
    }


    public List<Mission> getAllMission() {
        return missionRepository.findAll();
    }

}
