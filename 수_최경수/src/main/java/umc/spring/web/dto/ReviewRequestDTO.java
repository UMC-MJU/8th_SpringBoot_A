package umc.spring.web.dto;

import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {
  @Getter
  public static class JoinDto{
    String title;
    String body;
    Float score;
    Long storeId;
  }
}
