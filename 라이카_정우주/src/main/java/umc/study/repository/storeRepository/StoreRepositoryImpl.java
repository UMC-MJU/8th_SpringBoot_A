package umc.study.repository.storeRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import umc.study.domain.Mission;
import umc.study.domain.QStore;
import umc.study.domain.Store;

import java.util.List;

import static umc.study.domain.QMission.mission;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryImpl implements StoreRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QStore store = QStore.store;

    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (name != null) {
            predicate.and(store.name.eq(name));
        }

        if (score != null) {
            predicate.and(store.score.goe(4.0f));
        }

        return jpaQueryFactory
                .selectFrom(store)
                .where(predicate)
                .fetch();
    }

    @Override
    public Page<Mission> findMissionsByStoreId(Long storeId, PageRequest pageRequest) {
        List<Mission> missions = jpaQueryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .where(mission.store.id.eq(storeId))
                .orderBy(mission.createdAt.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(mission.count())
                .from(mission)
                .where(mission.store.id.eq(storeId))
                .fetchOne();

        return new PageImpl<>(missions, pageRequest, total != null ? total : 0L);
    }
}
