package umc.study.service.memberService;

import umc.study.domain.Member;
import umc.study.repository.memberRepository.MemberRepository;

public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        return null;
    }
}
