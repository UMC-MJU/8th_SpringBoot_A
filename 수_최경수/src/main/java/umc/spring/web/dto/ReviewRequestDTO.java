package umc.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

  @Schema(name = "ReviewJoinDto")
  @Getter
  public static class JoinDto{
    String title;
    String body;
    Float score;
  }
}
