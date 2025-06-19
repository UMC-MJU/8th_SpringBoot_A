package umc.study.repository.missionRepository;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findMissionsByMemberId(Long memberId, MissionStatus status);

    //홈화면 관련 메서드(지역 별 조회)
    List<Mission> findMissionsByRegionId(Long regionId);
    //미션 진행도
    int countTotalMissionsByMemberId(Long memberId);
    int countCompletedMissionsByMemberId(Long memberId);
}