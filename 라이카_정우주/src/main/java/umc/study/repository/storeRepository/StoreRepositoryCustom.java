package umc.study.repository.storeRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import java.util.List;

public interface StoreRepositoryCustom {
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
    Page<Mission> findMissionsByStoreId(Long storeId, PageRequest pageRequest);
}