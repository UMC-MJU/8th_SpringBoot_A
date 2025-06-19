package umc.study.service.myPageService;

import umc.study.domain.Member;

import java.util.Optional;

public interface MyPageQueryService {
    Optional<Member> getMemberProfile(Long memberId);
    Integer getMemberPoint(Long memberId);
    boolean updateName(Long memberId, String newName);
}
