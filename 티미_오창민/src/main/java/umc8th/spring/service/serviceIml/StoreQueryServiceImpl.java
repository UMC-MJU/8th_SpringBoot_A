package umc8th.spring.service.serviceIml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring.apiPayload.code.status.ErrorStatus;
import umc8th.spring.apiPayload.exception.handler.StoreHandler;
import umc8th.spring.converter.StoreConverter;
import umc8th.spring.domain.Region;
import umc8th.spring.domain.Store;
import umc8th.spring.repository.RegionRepository;
import umc8th.spring.repository.StoreRepository;
import umc8th.spring.service.StoreQueryService;
import umc8th.spring.web.dto.store.StoreRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreQueryServiceImpl implements StoreQueryService {

    //스프링 데이터 JPA에 제공하는 리포지토리를 가져온다.
    //가져오면서, 커스텀 리포지토리 인터페이스를 같이 가져오게 된다.
    //storerepository가 extends하고 있기 때문에..
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;


    @Override
    public Store findStore(Long id) {
        return storeRepository.findById(id).orElseThrow(()-> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

    }

    public boolean existsById(Long id) {
        return storeRepository.existsById(id);
    }

    @Override
    public Store joinStore(StoreRequestDTO.joinDTO request) {
        //상점 생성 -> 생성되는 상점에는 아직 지역 할당 안됨
        Store store = StoreConverter.toStore(request);
        //요청에서 지역 아이디 조회 및 실제 지역 조회
        //핸들러로 만약 오류 발생시 generalexception ->  nostoreerror 발생
        Region region = regionRepository.findById(request.getRegionId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        //연관관계 매핑
        store.setRegion(region);
        //상점 저장 및 반환
        return storeRepository.save(store);

    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score) {
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);

        //가져온 목록들 출력 -> 테스트 코드인듯.. -> 나중에는 log.info로 로깅처리하면 좋을 거 같다.
        filteredStores.forEach(store -> System.out.println("Store : " + store));

        return filteredStores; //가져온 상점 목록 리턴
    }
}
