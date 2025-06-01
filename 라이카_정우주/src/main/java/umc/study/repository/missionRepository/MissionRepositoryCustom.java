package umc.study.repository.missionRepository;

import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {

    List<Mission> findMissionsByMemberId(Long memberId);
    List<Mission> findMissionsByMemberId(Long memberId, MissionStatus status);
    List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status);
    int countTotalMissionsByMemberId(Long memberId);
    int countCompletedMissionsByMemberId(Long memberId);
    List<Mission> findMissionsByRegionId(Long regionId);
}