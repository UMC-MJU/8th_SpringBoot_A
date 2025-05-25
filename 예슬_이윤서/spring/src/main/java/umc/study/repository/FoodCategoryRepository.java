package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Categories;

public interface FoodCategoryRepository extends JpaRepository<Categories, Long> {
}
