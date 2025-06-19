package umc.study.repository.reviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    public Review createReview(String body, Float score, Long memberId, Long storeId) {
        Member member = entityManager.getReference(Member.class, memberId);
        Store store = entityManager.getReference(Store.class, storeId);

        Review review = Review.builder()
                .body(body)
                .score(score)
                .member(member)
                .store(store)
                .build();

        entityManager.persist(review);

        return review;
    }
}