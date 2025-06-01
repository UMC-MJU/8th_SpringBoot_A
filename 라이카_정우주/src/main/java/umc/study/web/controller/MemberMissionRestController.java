package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.MemberMissionConverter;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.mapping.MemberMissionRequestDTO;
import umc.study.web.dto.mapping.MemberMissionResponseDTO;
import umc.study.web.service.memberMission.MemberMissionCommandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/challenge")
    public ApiResponse<MemberMissionResponseDTO.ChallengeMissionResultDTO> challengeMission(@RequestBody @Valid MemberMissionRequestDTO.ChallengeMissionDto request) {
        MemberMission memberMission = memberMissionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengeMissionResultDTO(memberMission));
    }

    @PatchMapping("/{memberMissionId}/complete")
    @Operation(summary = "진행중인 미션을 완료로 변경 API", description = "진행중인 미션을 완료 상태로 변경합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "해당 미션이 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4002", description = "이미 완료된 미션입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberMissionId", description = "회원 미션의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberMissionResponseDTO.CompleteMissionResultDTO> completeMission(
            @PathVariable(name = "memberMissionId") Long memberMissionId,
            @RequestBody @Valid MemberMissionRequestDTO.CompleteMissionDto request) {

        MemberMission memberMission = memberMissionCommandService.completeMission(memberMissionId, request.getMemberId());
        return ApiResponse.onSuccess(MemberMissionConverter.toCompleteMissionResultDTO(memberMission));
    }
}