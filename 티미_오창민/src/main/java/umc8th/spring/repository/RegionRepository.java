package umc8th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring.domain.Region;

public interface RegionRepository extends JpaRepository<Region,Long> {
}
