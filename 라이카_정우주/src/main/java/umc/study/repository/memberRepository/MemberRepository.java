package umc.study.repository.memberRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Member;
import umc.study.domain.Review;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Page<Review> findReviewsByMemberId(Long memberId, PageRequest pageRequest);
}