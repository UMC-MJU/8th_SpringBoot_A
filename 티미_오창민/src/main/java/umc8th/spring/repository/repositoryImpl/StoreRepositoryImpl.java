package umc8th.spring.repository.repositoryImpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc8th.spring.domain.QStore;
import umc8th.spring.domain.Store;
import umc8th.spring.repository.StoreRepositoryCustom;

import java.util.List;

@Repository
@RequiredArgsConstructor // 생성자 주입을 자동으로 해준다.
public class StoreRepositoryImpl implements StoreRepositoryCustom {
    //required... 에노테이션을 통해, 해당 설정을 자동으로
    //생성자 주입을 해준다.
    private final JPAQueryFactory jpaQueryFactory;
    private final QStore store = QStore.store; //Q엔티티


    //
    @Override
    public List<Store> dynamicQueryWithBooleanBuilder(String name, Float score) {
        BooleanBuilder predicate = new BooleanBuilder(); // 가져온 정보들을 담을 곳
        //정보를 불러와서 담아온다.
        //if 문을 통해서, 동적인 경우를 고려할 수 있다.
        if (name != null) { //입력받은 상점 이름이 null이 아닌 경우
            predicate.and(store.name.eq(name)); // store의 이름이 name과 같은 상점을 조회
        }
        if (score != null) {
            predicate.and(store.score.goe(4.0f)); //점수가 4.0 이상인 상점을 조회
        }
        return jpaQueryFactory
                .selectFrom(store)
                .where(predicate)
                .fetch();
    }
}
