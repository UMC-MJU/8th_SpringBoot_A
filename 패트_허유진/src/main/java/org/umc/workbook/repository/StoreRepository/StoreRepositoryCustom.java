package org.umc.workbook.repository.StoreRepository;

import org.umc.workbook.domain.Store;

import java.util.List;

public interface StoreRepositoryCustom {

    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);
}
