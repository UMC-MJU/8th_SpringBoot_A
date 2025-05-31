package umc.study.web.service.memberService;

import umc.study.domain.Member;
import umc.study.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
