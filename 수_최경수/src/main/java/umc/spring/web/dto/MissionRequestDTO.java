package umc.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {
  @Schema(name = "MissionJoinDto")
  @Getter
  public static class JoinDto{
    Integer reward;
    LocalDate deadline;
    String missionSpec;
  }
}
