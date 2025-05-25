package umc8th.spring.service.serviceIml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring.apiPayload.code.status.ErrorStatus;
import umc8th.spring.apiPayload.exception.handler.MemberHandler;
import umc8th.spring.domain.Member;
import umc8th.spring.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember;

    }

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    }
}
