package umc.study.repository.memberRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.Member;
import umc.study.domain.QMember;

import java.util.Optional;

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
}