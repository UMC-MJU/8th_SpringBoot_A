package umc8th.spring.converter;


import umc8th.spring.domain.Store;
import umc8th.spring.web.dto.store.StoreRequestDTO;
import umc8th.spring.web.dto.store.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResponseDTO.joinResult toJoinResult(Store store) {
        return StoreResponseDTO.joinResult.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    //상점 생성 -> 지역 할당은 다른 곳에서 할당.
    public static Store toStore(StoreRequestDTO.joinDTO request) {
        return Store.builder()
                .address(request.getAddress())
                .name(request.getName())
                .score(request.getScore()).build();
    }


}
