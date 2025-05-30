package umc8th.spring.service.serviceIml;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring.apiPayload.code.status.ErrorStatus;
import umc8th.spring.apiPayload.exception.handler.MemberMissionHandler;
import umc8th.spring.converter.MemberMissionConverter;
import umc8th.spring.domain.Member;
import umc8th.spring.domain.MemberMission;
import umc8th.spring.domain.Mission;
import umc8th.spring.domain.Region;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.repository.MemberMissionRepository;
import umc8th.spring.repository.MemberRepository;
import umc8th.spring.repository.MissionRepository;
import umc8th.spring.service.MemberMissionService;
import umc8th.spring.web.dto.memberMission.MemberMissionRequestDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberMissionServiceImpl implements MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;



    public List<MemberMission> getAbleMissionsInRegionByMember(Region region, Member member) {
        List<MemberMission> memberMissions = memberMissionRepository.getAbleMissionsInRegionByMember(region, member);
        return memberMissions;
    }


    @Override
    public MemberMission addMemberMission(MemberMissionRequestDto.addMemberMission request) {
        //리턴되는 미션에는, 멤버, 미션 모두 없다.
        MemberMission memberMission = MemberMissionConverter.toMemberMission(request);
        //아이디로 객체 조회
        Member member = memberRepository.findById(request.getMemberId()).get();
        Mission mission = missionRepository.findById(request.getMissionId()).get();
        //연관관계 매핑
        memberMission.setMember(member);
        memberMission.setMission(mission);
        //저장 및 반환
        return memberMissionRepository.save(memberMission);
    }

    @Override
    public MemberMission toComplete(MemberMissionRequestDto.toComplete request) {
        //멤버 미션 조회
        MemberMission memberMission = memberMissionRepository.findById(request.getMemberMissionId()).orElseThrow(() ->
                new MemberMissionHandler(ErrorStatus.MEMBER_MISSION_NOT_FOUND));
        memberMission.editStatus(MissionStatus.COMPLETE); //성공으로 바꿔주기 -> 더티체킹으로 자동으로 수정될 것
        return memberMission;

    }


}
