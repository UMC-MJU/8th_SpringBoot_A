package umc.study.repository.memberMission;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMemberMission memberMission = QMemberMission.memberMission;

    @Override
    public Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, PageRequest pageRequest) {
        List<MemberMission> memberMissions = jpaQueryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission).fetchJoin()
                .join(memberMission.mission.store).fetchJoin()
                .where(memberMission.member.eq(member)
                        .and(memberMission.status.eq(status)))
                .orderBy(memberMission.createdAt.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(memberMission.count())
                .from(memberMission)
                .where(memberMission.member.eq(member)
                        .and(memberMission.status.eq(status)))
                .fetchOne();

        return new PageImpl<>(memberMissions, pageRequest, total != null ? total : 0L);
    }
}