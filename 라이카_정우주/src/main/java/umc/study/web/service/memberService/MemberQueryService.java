package umc.study.web.service.memberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;

public interface MemberQueryService {
    Page<Review> getReviewList(Long memberId, Integer page);
    Page<MemberMission> getChallengingMissionList(Long memberId, Integer page);
    Page<MemberMission> getCompletedMissionList(Long memberId, Integer page);


}
