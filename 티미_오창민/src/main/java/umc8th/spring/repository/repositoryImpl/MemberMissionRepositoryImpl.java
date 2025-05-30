package umc8th.spring.repository.repositoryImpl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc8th.spring.domain.*;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.repository.MemberMissionRepositoryCustom;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;//쿼리 자동 생성
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMission mission = QMission.mission;
    private final QStore store= QStore.store;
    private final QRegion qRegion = QRegion.region;


    //현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)
    @Override
    public List<MemberMission> getAbleMissionsInRegionByMember(Region region, Member member) {
        List<MemberMission> findMemberMissions = jpaQueryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission)
                .join(mission.store, store)
                .join(store.region, qRegion)
                .where(qRegion.name.like(region.getName()), //현재 가능한 지역, like 비교 사용
                        memberMission.status.eq(MissionStatus.CHALLENGING)) //도전이 가능한 미션
                .offset(0) //시작 페이징
                .limit(5)//페이지당 최대 5건 조회 -> 와 이거 땜에 3개였구나..
                .fetch();//여러개의 응답을 받으니까 fetchOne이 아니라,fetch를 사용

        return findMemberMissions;
    }

}
