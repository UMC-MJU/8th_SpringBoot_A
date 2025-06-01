package umc.study.web.service.memberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.memberMission.MemberMissionRepository;
import umc.study.repository.memberRepository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        return memberRepository.findReviewsByMemberId(memberId, pageRequest);
    }

    @Override
    public Page<MemberMission> getChallengingMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        Page<MemberMission> memberPage = memberMissionRepository.findAllByMemberAndStatus(
                member, MissionStatus.CHALLENGING, PageRequest.of(page - 1, 10));
        return memberPage;
    }

    @Override
    public Page<MemberMission> getCompletedMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        Page<MemberMission> memberPage = memberMissionRepository.findAllByMemberAndStatus(
                member, MissionStatus.COMPLETE, PageRequest.of(page - 1, 10));
        return memberPage;
    }
}
