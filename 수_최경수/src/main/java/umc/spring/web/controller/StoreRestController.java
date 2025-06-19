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
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
  private final StoreQueryService storeQueryService;

  @GetMapping("/{storeId}/reviews")
  @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API 이며, 페이징을 포함합니다. query String으로 page 번호를 주세요")
  @ApiResponses({
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  @Parameters({
          @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
  })
  public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@Valid @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page){
    Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
    return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
  }


  @GetMapping("/{storeId}/missions")
  @Operation(summary = "특정 가게의 미션 목록 API", description = "특정 가게의 미션 목록을 조회하는 API 이며, 페이징을 포함합니다. params 값으로 page 번호와 size를 주세요")
  @ApiResponses({
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMissionListByStore(@Valid @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
    Page<Mission> missionsList = storeQueryService.getMissionList(storeId, page, size);
    return ApiResponse.onSuccess(StoreConverter.missionPreViewListDTO(missionsList));
  }
}
