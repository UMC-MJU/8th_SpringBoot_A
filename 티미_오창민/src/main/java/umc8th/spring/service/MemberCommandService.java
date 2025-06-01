package umc8th.spring.service;


import umc8th.spring.domain.Member;
import umc8th.spring.web.dto.member.MemberRequestDTO;

public interface MemberCommandService {
    //회원을 등록하고, 회원을 반환하는 메소드 + DB에 저장까지
    Member joinMember(MemberRequestDTO.JoinDto request);

    Member findMember(Long memberId);
}
