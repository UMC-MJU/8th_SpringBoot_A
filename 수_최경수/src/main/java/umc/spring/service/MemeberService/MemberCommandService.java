package umc.spring.service.MemeberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {
  Member joinMember(MemberRequestDTO.JoinDto request);
}
