package umc.study.web.service.memberMission;

import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionRequestDTO;

public interface MemberMissionCommandService {
    MemberMission challengeMission(MemberMissionRequestDTO.ChallengeMissionDto request);
}