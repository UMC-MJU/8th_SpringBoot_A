package umc.study.web.service.memberService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberResponseDTO;

public interface MemberQueryService {
    Page<Review> getReviewList(Long memberId, Integer page);
    Page<MemberMission> getChallengingMissionList(Long memberId, Integer page);
    Page<MemberMission> getCompletedMissionList(Long memberId, Integer page);
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);

}
