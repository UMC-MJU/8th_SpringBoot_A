package umc.study.web.service.memberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.config.security.jwt.JwtTokenProvider;
import umc.study.convertor.MemberConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.memberMission.MemberMissionRepository;
import umc.study.repository.memberRepository.MemberRepository;
import umc.study.web.dto.MemberResponseDTO;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final JwtTokenProvider jwtTokenProvider;

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

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request){
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(member);
    }
}
