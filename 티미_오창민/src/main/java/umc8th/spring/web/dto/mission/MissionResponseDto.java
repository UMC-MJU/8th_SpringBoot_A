package umc8th.spring.web.dto.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc8th.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class addMissionResult{
        Long missionId; //만들어진 미션의 아이디
        LocalDateTime createdAt; //미션이 만들어진 시간
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getMissionListByStore {
        Long storeId; //조회하는 리뷰의 가게의 아이디
        List<GetMissionListByStoreDetail> missionList;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetMissionListByStoreDetail {
        LocalDateTime date; //미션이 생성된 날짜
        Integer reward; // 미션 성공 점수
        LocalDate deadline; //미션 만료일
    }
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getChallengingMissionList {
        Long memberId;
        List<ChallengingMissionListDetail> memberMissions;
    }
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChallengingMissionListDetail {
        LocalDateTime date; //미션이 생성된 날짜
        Integer reward; // 미션 성공 점수
        LocalDate deadline; //미션 만료일
        MissionStatus status;; //확인용 미션 상태
    }
}
