package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MissionResponse {
    private Long missionId;
    private String storeName;
    private Integer rewardAmount;
    private Boolean isComplete;
}
