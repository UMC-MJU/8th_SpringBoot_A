package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.MissionMemberConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.MissionService.MissionQueryService;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
  private final MissionCommandService missionCommandService;
  private final MissionQueryService missionQueryService;

  @PostMapping("/{storeId}")
  @Operation(summary = "가게에 미션 추가 API", description = "가게에 미션을 추가하는 API 입니다.")
  @ApiResponses({
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  public ApiResponse<MissionResponseDTO.JoinResultDTO> join(@PathVariable("storeId") Long storeId, @RequestBody @Valid MissionRequestDTO.JoinDto request){
    Mission misson = missionCommandService.joinMission(storeId, request);
    return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(misson));
  }

  @PostMapping("/update")
  @Operation(summary = "미션 상태 업데이트 API", description = "미션 상태를 업데이트 하는 API 입니다. 0: 도전중, 1: 완료입니다.")
  @ApiResponses({
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  public ApiResponse<MissionResponseDTO.JoinMemberResultDTO> join(@RequestBody @Valid MissionRequestDTO.JoinMemberDto request){
    Mission mission = missionCommandService.joinPreferMission(request);
    return ApiResponse.onSuccess(MissionMemberConverter.toJoinResultDTO(mission));
  }

  @GetMapping("/my")
  @Operation(summary = "내가 진행중인 미션 목록 API", description = "내가 진행중인 미션 목록을 조회하는 API 이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
  @ApiResponses({
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  public ApiResponse<MissionResponseDTO.MissionPreViewListDTO> getMissionList(@Valid @RequestParam(name="userId") Long userId, @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
    Page<MemberMission> memberMissionList = missionQueryService.getMissionList(userId, page, size);
    return ApiResponse.onSuccess(MissionConverter.missionPreViewListDTO(memberMissionList));
  }
}
