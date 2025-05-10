package umc.study.repository.quest;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import umc.study.domain.QMission;
import umc.study.domain.QQuest;
import umc.study.domain.QStore;
import umc.study.domain.Quest;
import umc.study.web.dto.MissionResponse;

import java.util.List;

@RequiredArgsConstructor
public class QuestRepositoryImpl implements QuestRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<MissionResponse> findInProgressQuests(Long customerId, Pageable pageable) {
        QQuest quest = QQuest.quest;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        List<MissionResponse> results = queryFactory
                .select(Projections.constructor(MissionResponse.class,
                        mission.id,
                        store.name,
                        mission.amount,
                        quest.isComplete,
                        mission.createdAt
                ))
                .from(quest)
                .join(quest.mission, mission)
                .join(quest.store, store)
                .where(
                        quest.customer.id.eq(customerId),
                        quest.isComplete.isFalse()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(quest.count())
                .from(quest)
                .where(
                        quest.customer.id.eq(customerId),
                        quest.isComplete.isFalse()
                )
                .fetchOne();

        return new PageImpl<>(results, pageable, total);
    }


    @Override
    public Page<MissionResponse> findCompletedQuests(Long customerId, Pageable pageable) {
        QQuest quest = QQuest.quest;
        QMission mission = QMission.mission;
        QStore store = QStore.store;

        List<MissionResponse> content = queryFactory
                .select(Projections.constructor(MissionResponse.class,
                        mission.id,
                        mission.amount,
                        store.name,
                        store.description,
                        quest.isComplete))
                .from(quest)
                .join(quest.mission, mission)
                .join(quest.store, store)
                .where(
                        quest.customer.id.eq(customerId),
                        quest.isComplete.eq(true)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(quest.count())
                .from(quest)
                .where(
                        quest.customer.id.eq(customerId),
                        quest.isComplete.eq(true)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }
}
