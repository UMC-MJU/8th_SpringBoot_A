package umc.spring.service.UserService;

import umc.spring.domain.Member;

import java.util.Optional;

public interface UserQueryService {
  Optional<Member> findMemberById(Long id);
}
