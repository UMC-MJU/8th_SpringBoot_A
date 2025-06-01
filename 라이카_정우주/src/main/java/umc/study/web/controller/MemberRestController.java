package umc.study.web.controller;

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
import umc.study.apiPayload.ApiResponse;
import umc.study.convertor.MemberConverter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.mapping.MemberMission;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.service.memberService.MemberCommandService;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.service.memberService.MemberQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    @Operation(summary = "회원 가입 API", description = "회원 가입을 진행합니다.")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰들을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.", example = "1")
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getMyReviews(
            @PathVariable(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<Review> reviewPage = memberQueryService.getReviewList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toReviewPreViewListDTO(reviewPage));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션들을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.", example = "1")
    })
    public ApiResponse<MemberResponseDTO.MemberMissionPreViewListDTO> getMyChallengingMissions(
            @PathVariable(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<MemberMission> memberMissionPage = memberQueryService.getChallengingMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toMemberMissionPreViewListDTO(memberMissionPage));
    }

    @GetMapping("/{memberId}/missions/completed")
    @Operation(summary = "내가 완료한 미션 목록 조회 API", description = "내가 완료한 미션들을 페이징하여 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 1번이 1 페이지 입니다.", example = "1")
    })
    public ApiResponse<MemberResponseDTO.CompletedMissionPreViewListDTO> getMyCompletedMissions(
            @PathVariable(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page) {

        Page<MemberMission> memberMissionPage = memberQueryService.getCompletedMissionList(memberId, page);
        return ApiResponse.onSuccess(MemberConverter.toCompletedMissionPreViewListDTO(memberMissionPage));
    }
}
