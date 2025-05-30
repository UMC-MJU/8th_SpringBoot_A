package umc8th.spring.repository;


import umc8th.spring.domain.Member;
import umc8th.spring.domain.MemberMission;
import umc8th.spring.domain.Region;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> getAbleMissionsInRegionByMember(Region region, Member member);
}


