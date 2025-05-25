package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.MissionMemberConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {
  private final MissionCommandService missionCommandService;

  @PostMapping("/{storeId}")
  public ApiResponse<MissionResponseDTO.JoinResultDTO> join(@PathVariable("storeId") Long storeId, @RequestBody @Valid MissionRequestDTO.JoinDto request){
    Mission misson = missionCommandService.joinMission(storeId, request);
    return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(misson));
  }

  @PostMapping("/update")
  public ApiResponse<MissionResponseDTO.JoinMemberResultDTO> join( @RequestBody @Valid MissionRequestDTO.JoinMemberDto request){
    Mission mission = missionCommandService.joinPreferMission(request);
    return ApiResponse.onSuccess(MissionMemberConverter.toJoinResultDTO(mission));
  }
}
