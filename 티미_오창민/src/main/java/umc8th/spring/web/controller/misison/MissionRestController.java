package umc8th.spring.web.controller.misison;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc8th.spring.apiPayload.ApiResponse;
import umc8th.spring.converter.MissionConverter;
import umc8th.spring.domain.Member;
import umc8th.spring.domain.Mission;
import umc8th.spring.domain.enums.MissionStatus;
import umc8th.spring.service.MemberCommandService;
import umc8th.spring.service.MissionQueryService;
import umc8th.spring.web.dto.mission.MissionRequestDto;
import umc8th.spring.web.dto.mission.MissionResponseDto;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
@Validated
public class MissionRestController {

    private final MissionQueryService missionQueryService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/addMission")
    public ApiResponse<MissionResponseDto.addMissionResult> addMission(@RequestBody @Valid MissionRequestDto.addMission request) {
        Mission mission = missionQueryService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResult(mission));
    }

    //특정 가게의 미션을 조회하는 컨트롤러
    @GetMapping("stores/{storeId}")
    public ApiResponse<MissionResponseDto.getMissionListByStore> getMissions(@PathVariable("storeId") Long storeId,  @RequestParam("page") Integer page) {
        //page 정보 수정
        int editPage = Math.min(0, page - 1); //1이상의 페이지면 -1 (페이징은 0부터 시작), 0이면 0 그대로 반환
        List<Mission> missionByStore = missionQueryService.getMissionByStore(storeId, editPage);
        return ApiResponse.onSuccess(MissionConverter.toGetMissionListByStoreResult(storeId,missionByStore));
    }

    //멤버의 진행 중인 미션을 조회하는 컨트롤러
    @GetMapping("members/{memberId}")
    public ApiResponse<MissionResponseDto.getChallengingMissionList> getChallengingMissions(@PathVariable("memberId") Long memberId, @RequestParam("page") Integer page) {
        Member member = memberCommandService.findMember(memberId);
        int editPage = Math.min(0, page - 1); //1이상의 페이지면 -1 (페이징은 0부터 시작), 0이면 0 그대로 반환

        List<Mission> missionsByMemberAndStatus = missionQueryService.getMissionsByMemberAndStatus(MissionStatus.CHALLENGING, member,editPage);
        return ApiResponse.onSuccess(MissionConverter.toChallengingMissionResult(member, missionsByMemberAndStatus));
    }


}
