package umc8th.spring.web.dto.store;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDTO {

    //특정 지역에 가게 추가하기
    //가게 생성 + 저장
    @Getter
    public static class joinDTO {
        @NotNull
        Float score;
        @NotNull
        Long regionId;
        @Size(min = 5, max = 12)
        String address;
        @NotEmpty
        String name;

    }
}
