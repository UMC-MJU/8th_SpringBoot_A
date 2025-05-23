package umc.study.repository.memberRepository;

import umc.study.domain.Member;
import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<Member> findMemberProfile(Long memberId);
    Integer findMemberPoint(Long memberId);
    boolean updateMemberName(Long memberId, String newName);
}