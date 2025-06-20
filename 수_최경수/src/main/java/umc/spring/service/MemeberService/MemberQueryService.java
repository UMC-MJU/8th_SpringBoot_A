package umc.spring.service.MemeberService;

import jakarta.servlet.http.HttpServletRequest;
import umc.spring.domain.Member;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.Optional;

public interface MemberQueryService {
  Optional<Member> findMemberById(Long id);
  MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
