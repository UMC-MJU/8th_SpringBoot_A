package umc8th.spring.repository;


import umc8th.spring.domain.Store;

import java.util.List;

//스프링 데이터 JPA에서 지원하지 않는 커스텀으로 가져오는 메소드를 담은 인터페이스
//해당 인터페이스를 스프링 데이터 JPA 리포지토리가 가져가서 쓸 것.
public interface StoreRepositoryCustom {
    //동적 쿼리를 위해 booleanBuiler를 이용해서 가져오는 메소드
    List<Store> dynamicQueryWithBooleanBuilder(String name, Float score);


}
