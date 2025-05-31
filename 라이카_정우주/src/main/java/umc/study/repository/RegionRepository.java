package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsByName(String name);

    Region findByName(String name);
}
