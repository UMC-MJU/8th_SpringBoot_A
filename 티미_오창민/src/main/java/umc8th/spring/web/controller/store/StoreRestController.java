package umc8th.spring.web.controller.store;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc8th.spring.apiPayload.ApiResponse;
import umc8th.spring.converter.StoreConverter;
import umc8th.spring.domain.Store;
import umc8th.spring.service.StoreQueryService;
import umc8th.spring.web.dto.store.StoreRequestDTO;
import umc8th.spring.web.dto.store.StoreResponseDTO;

@RestController //객체를 반환함으로서 josn 파일을 반환할 것
@RequiredArgsConstructor
@RequestMapping("/stores") //공통 주소 처리
public class StoreRestController {
    private final StoreQueryService storeQueryService;

    @PostMapping("/join")
    public ApiResponse<StoreResponseDTO.joinResult> joinStore(@RequestBody @Valid StoreRequestDTO.joinDTO request) {
        Store store = storeQueryService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResult(store));

    }
}
