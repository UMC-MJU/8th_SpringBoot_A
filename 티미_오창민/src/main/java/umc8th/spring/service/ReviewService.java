package umc8th.spring.service;


import umc8th.spring.domain.Member;
import umc8th.spring.domain.Review;
import umc8th.spring.domain.Store;
import umc8th.spring.web.dto.review.ReviewRequestDto;

import java.util.List;

public interface ReviewService {
    Review addReview(ReviewRequestDto.addReview request);
    List<Review> getReviewsByNameAndStore(Member member, Store store, Integer page);

}
