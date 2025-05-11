package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
  private final JPAQueryFactory jpaQueryFactory;
  private final QMission mission = QMission.mission;
  private final QMemberMission memberMission = QMemberMission.memberMission;

  @Override
  public List<Mission> dynamicQueryWithBooleanBuilder(Long userId, MissionStatus status) {
    BooleanBuilder predicate = new BooleanBuilder();

    if(userId != null){
      predicate.and(memberMission.member.id.eq(userId));
    }

    if (status != null ) {
        predicate.and(memberMission.status.eq(status));
    }

    return jpaQueryFactory
            .select(memberMission.mission)
            .from(memberMission)
            .join(memberMission.mission, mission)
            .where(predicate)
            .orderBy(memberMission.updatedAt.desc(), memberMission.id.desc())
            .limit(10)
            .fetch();
  }
}
