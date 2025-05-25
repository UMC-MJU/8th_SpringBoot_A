package umc.study.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Store;
import umc.study.repository.store.StoreRepositoryCustom;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}