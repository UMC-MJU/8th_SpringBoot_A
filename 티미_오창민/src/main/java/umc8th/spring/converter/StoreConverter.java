package umc8th.spring.converter;


import org.springframework.data.domain.Page;
import umc8th.spring.domain.Review;
import umc8th.spring.domain.Store;
import umc8th.spring.web.dto.store.StoreRequestDTO;
import umc8th.spring.web.dto.store.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getTitle())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
