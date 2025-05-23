package umc.study.service.myPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.repository.memberRepository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageQueryServiceImpl implements MyPageQueryService {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> getMemberProfile(Long memberId) {
        return memberRepository.findMemberProfile(memberId);
    }

    @Override
    public Integer getMemberPoint(Long memberId) {
        return memberRepository.findMemberPoint(memberId);
    }

    @Override
    @Transactional
    public boolean updateName(Long memberId, String newName) {
        return memberRepository.updateMemberName(memberId, newName);
    }
}