package umc8th.spring.service;


import umc8th.spring.domain.Member;
import umc8th.spring.domain.Mission;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.web.dto.mission.MissionRequestDto;

import java.util.List;

public interface MissionQueryService {
    List<Mission> getMissionsByMemberAndStatus(MissionStatus status, Member member, Integer page);
    List<Mission> getMissionByStore(Long storeId, Integer page);
    Mission addMission(MissionRequestDto.addMission request);
}
