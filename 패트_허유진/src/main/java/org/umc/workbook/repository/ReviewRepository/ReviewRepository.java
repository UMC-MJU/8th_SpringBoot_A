package org.umc.workbook.repository.ReviewRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.umc.workbook.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}
