package umc.study.repository.missionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Mission;

import umc.study.domain.QMission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.QMemberMission;

import java.util.List;

import static umc.study.domain.QStore.store;


@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public List<Mission> findMissionsByMemberId(Long memberId) {
        return List.of();
    }

    @Override
    public List<Mission> findMissionsByMemberId(Long memberId, MissionStatus status) {
        BooleanBuilder predicate = new BooleanBuilder();

        predicate.and(memberMission.member.id.eq(memberId));

        if (status != null) {
            predicate.and(memberMission.status.eq(status));
        }

        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.memberMissionList, memberMission)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<Mission> findMissionsByMemberIdAndStatus(Long memberId, MissionStatus status) {
        return List.of();
    }

    @Override
    public List<Mission> findMissionsByRegionId(Long regionId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (regionId != null) {
            predicate.and(store.region.id.eq(regionId));
        }

        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.store, store)
                .where(predicate)
                .fetch();
    }

    @Override
    public int countTotalMissionsByMemberId(Long memberId) {
        return jpaQueryFactory
                .selectFrom(memberMission)
                .where(memberMission.member.id.eq(memberId))
                .fetch()
                .size();
    }

    @Override
    public int countCompletedMissionsByMemberId(Long memberId) {
        return jpaQueryFactory
                .selectFrom(memberMission)
                .where(memberMission.member.id.eq(memberId)
                        .and(memberMission.status.eq(MissionStatus.COMPLETE)))
                .fetch()
                .size();
    }
}