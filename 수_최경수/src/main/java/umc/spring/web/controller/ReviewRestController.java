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
import umc.spring.converter.ReviewConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {
  private final ReviewCommandService reviewCommandService;
  private final ReviewQueryService reviewQueryService;

  @PostMapping("/{storeId}")
  public ApiResponse<ReviewResponseDTO.JoinResultDTO> join(@PathVariable("storeId") Long storeId, @RequestBody @Valid ReviewRequestDTO.JoinDto request){
    Review review = reviewCommandService.joinReview(storeId, request);
    return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
  }

  @GetMapping("/{userId}")
  @Operation(summary = "내가 쓴 리뷰 목록 조회 API", description = "내가 쓴 리뷰들의 목록을 조회하는 API 이며, 페이징을 포함합니다. param값으로  page와 size를 주세요")
  @ApiResponses({
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
          @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
  })
  @Parameters({
          @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!")
  })
  public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@Valid @PathVariable(name = "userId") Long userId, @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
    Page<Review> reviewList = reviewQueryService.getReviewList(userId, page, size);
    return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewList));
  }
}
