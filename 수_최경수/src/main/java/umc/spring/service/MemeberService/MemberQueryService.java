package umc.spring.service.MemeberService;

import umc.spring.domain.Member;

import java.util.Optional;

public interface MemberQueryService {
  Optional<Member> findMemberById(Long id);
}
