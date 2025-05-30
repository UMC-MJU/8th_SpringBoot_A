package umc8th.spring.web.controller.review;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc8th.spring.apiPayload.ApiResponse;
import umc8th.spring.converter.ReviewConverter;
import umc8th.spring.domain.Member;
import umc8th.spring.domain.Review;
import umc8th.spring.domain.Store;
import umc8th.spring.service.ReviewService;
import umc8th.spring.service.serviceIml.MemberServiceImpl;
import umc8th.spring.service.serviceIml.StoreQueryServiceImpl;
import umc8th.spring.web.dto.review.ReviewRequestDto;
import umc8th.spring.web.dto.review.ReviewResponseDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
@Validated
public class ReviewRestController {
    private final ReviewService reviewService;
    private final StoreQueryServiceImpl storeService;
    private final MemberServiceImpl memberService;

    @PostMapping("addReview")
    public ApiResponse<ReviewResponseDto.addReviewResult> addReview(@RequestBody @Valid ReviewRequestDto.addReview request) {
        Review review = reviewService.addReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toaddReviewResult(review));
    }




//    //컨트롤러 단에서 필요한 정보를 요청하고, 데이터를 찾아 변환해 반환
    //위의 서비스 메소드 안에서 한번에 이루어지는 것과 같은 쿼리가 나가지만, 좀더 역할을 분리해서 안정적으로 하였다.
    @GetMapping("reviews/{memberId}")

    public ApiResponse<ReviewResponseDto.GetReviewsResult> getReviewsByController(@PathVariable("memberId") Long memberId, @RequestParam("storeId") Long storeId, @RequestParam("page") Integer page) {
        //page 정보 수정
        int editPage = Math.max(0, page - 1); //1이상의 페이지면 -1 (페이징은 0부터 시작), 0이면 0 그대로 반환
        Member member = memberService.getMemberById(memberId);
        Store store = storeService.findStore(storeId);
        List<Review> reviews = reviewService.getReviewsByNameAndStore(member, store, editPage);
        return ApiResponse.onSuccess(ReviewConverter.toGetReviewResult(reviews, member, store.getName()));
    }
}
