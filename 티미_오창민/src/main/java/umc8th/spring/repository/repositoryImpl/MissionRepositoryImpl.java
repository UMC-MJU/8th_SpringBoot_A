package umc8th.spring.repository.repositoryImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc8th.spring.domain.*;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.repository.MissionRepositoryCustom;

import java.util.List;

@Repository //리포지토리 명시
@RequiredArgsConstructor //생성자 주입 자동화 에노테이션
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;//쿼리 자동 생성
    private final QMission mission = QMission.mission;//미션 Q객체
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMember qMember = QMember.member;
    //구현 시작

    @Override
    public List<Mission> getMissionsByMemberAndStatus(MissionStatus status, Member member, Integer page) {
        List<Mission> missions = jpaQueryFactory
                .select(mission)
                .from(memberMission)
                .join(memberMission.mission, mission)
                .join(memberMission.member, qMember)
                .where(memberMission.status.eq(status),
                        memberMission.member.eq(member))
                .offset(page)
                .limit(10)
                .fetch();
        return missions;
    }
}
