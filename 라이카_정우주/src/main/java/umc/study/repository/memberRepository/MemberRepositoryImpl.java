package umc.study.repository.memberRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.QMember;
import umc.study.domain.Review;

import java.util.List;
import java.util.Optional;

import static umc.study.domain.QReview.review;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;
    private final QMember member = QMember.member;

    @Override
    public Optional<Member> findMemberProfile(Long memberId) {
        Member foundMember = jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))
                .fetchOne();

        return Optional.ofNullable(foundMember);
    }

    @Override
    public Integer findMemberPoint(Long memberId) {
        Integer point = jpaQueryFactory
                .select(member.point)
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();

        return point != null ? point : 0;
    }

    @Override
    public boolean updateMemberName(Long memberId, String newName) {
        long updatedCount = jpaQueryFactory
                .update(member)
                .set(member.name, newName)
                .where(member.id.eq(memberId))
                .execute();

        entityManager.flush();
        entityManager.clear();

        return updatedCount > 0;
    }

    @Override
    public Page<Review> findReviewsByMemberId(Long memberId, PageRequest pageRequest) {
        List<Review> reviews = jpaQueryFactory
                .selectFrom(review)
                .join(review.member, member).fetchJoin()
                .join(review.store).fetchJoin()
                .where(review.member.id.eq(memberId))
                .orderBy(review.createdAt.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        Long total = jpaQueryFactory
                .select(review.count())
                .from(review)
                .where(review.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(reviews, pageRequest, total != null ? total : 0L);
    }
}