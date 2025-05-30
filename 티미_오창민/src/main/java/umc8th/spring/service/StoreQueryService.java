package umc8th.spring.service;


import umc8th.spring.domain.Store;
import umc8th.spring.web.dto.store.StoreRequestDTO;

import java.util.List;


//상점 서비스 인터페이스
public interface StoreQueryService {
    Store findStore(Long id);
    //상점을 등록하고, 회원을 반환하는 메소드 + DB에 저장까지
    Store joinStore(StoreRequestDTO.joinDTO request);
    //상점의 이름과 점수로 조회하는 메소드
    List<Store> findStoresByNameAndScore(String name, Float score);
    //상점의 존제 여부를 반환하는 메소드
    boolean existsById(Long id);

}

