package umc.study.repository.memberMission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.study.domain.Member;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepositoryCustom {
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest);
}

