package umc8th.spring.service;


import umc8th.spring.domain.MemberMission;
import umc8th.spring.web.dto.memberMission.MemberMissionRequestDto;

public interface MemberMissionService {
    public MemberMission addMemberMission(MemberMissionRequestDto.addMemberMission request);

    public MemberMission toComplete(MemberMissionRequestDto.toComplete request);
}
