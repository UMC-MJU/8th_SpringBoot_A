package umc.study.service.homeService;

import umc.study.domain.Mission;

import java.util.List;
import java.util.Map;

public interface HomeQueryService {
    Map<String, Object> getHomeInfo(Long memberId);
    List<Mission> getMissionsByRegionId(Long regionId);
    double calculateMissionState(Long memberId);
}
