package umc8th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
