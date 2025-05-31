package umc.study.service.homeService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.repository.missionRepository.MissionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeQueryServiceImpl implements HomeQueryService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    public Map<String, Object> getHomeInfo(Long memberId) {
        Map<String, Object> homeInfo = new HashMap<>();

        double completionRate = calculateMissionState(memberId);
        homeInfo.put("missionCompletionRate", completionRate);

        Integer points = memberRepository.findMemberPoint(memberId);
        homeInfo.put("points", points);

        List<Mission> inProgressMissions = missionRepository.findMissionsByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING);
        homeInfo.put("inProgressMissions", inProgressMissions);

        return homeInfo;
    }

    @Override
    public List<Mission> getMissionsByRegionId(Long regionId) {
        return missionRepository.findMissionsByRegionId(regionId);
    }

    @Override
    public double calculateMissionState(Long memberId) {
        int totalMissions = missionRepository.countTotalMissionsByMemberId(memberId);

        int completedMissions = missionRepository.countCompletedMissionsByMemberId(memberId);

        if (totalMissions == 0) {
            return 0.0;
        }
        return (double) completedMissions / totalMissions * 100.0;
    }
}