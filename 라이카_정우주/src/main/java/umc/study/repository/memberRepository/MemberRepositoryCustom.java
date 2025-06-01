package umc.study.repository.memberRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.study.domain.Member;
import umc.study.domain.Review;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findMemberProfile(Long memberId);
    Integer findMemberPoint(Long memberId);
    boolean updateMemberName(Long memberId, String newName);

    Page<Review> findReviewsByMemberId(Long memberId, PageRequest pageRequest);

}