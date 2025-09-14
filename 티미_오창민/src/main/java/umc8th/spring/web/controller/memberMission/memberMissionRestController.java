package umc8th.spring.web.controller.memberMission;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc8th.spring.apiPayload.ApiResponse;
import umc8th.spring.converter.MemberMissionConverter;
import umc8th.spring.domain.MemberMission;
import umc8th.spring.service.MemberMissionService;
import umc8th.spring.web.dto.memberMission.MemberMissionRequestDto;
import umc8th.spring.web.dto.memberMission.MemberMissionResponseDto;

@RestController
@RequestMapping("/memberMission")
@RequiredArgsConstructor
public class memberMissionRestController {
    private final MemberMissionService memberMissionService;

    @PostMapping("/addMemberMission")
    public ApiResponse<MemberMissionResponseDto.addMissionResult> addMemberMission(@RequestBody @Valid MemberMissionRequestDto.addMemberMission request) {
        MemberMission memberMission = memberMissionService.addMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toAddMemberMissionResult(memberMission));
    }

    @PatchMapping("/members/toComplete")
    public ApiResponse<MemberMissionResponseDto.toCompleteResult> toComplete(@RequestBody @Valid MemberMissionRequestDto.toComplete request) {
        MemberMission editedMemberMission = memberMissionService.toComplete(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toCompleteResult(editedMemberMission));
    }
}
