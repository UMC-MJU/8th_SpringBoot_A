package umc8th.spring.web.dto.mission;

import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {

    @Getter
    //가게에 미션 추가하기 위한 정보를 담는 DTO
    //미션생성 및 가게에 할당
    public static class addMission{
        Long storeId;// 미션을 할당할 가게 아이디
        Integer reward;// 성공 점수
        private LocalDate deadline;//미션 만료일

    }
}
