package umc.spring.service.ReviewService;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
  Review joinReview(ReviewRequestDTO.JoinDto request);
}
