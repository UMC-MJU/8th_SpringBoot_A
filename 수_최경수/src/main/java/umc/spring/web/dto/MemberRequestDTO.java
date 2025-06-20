package umc.spring.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.domain.enums.Role;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {
  @Schema(name = "MemberJoinDto")
  @Getter
  @Setter
  public static class JoinDto{
    @NotBlank
    String name;
    @NotNull
    Integer gender;
    @Email
    String email;
    @NotNull
    String password;
    @NotNull
    Integer birthYear;
    @NotNull
    Integer birthMonth;
    @NotNull
    Integer birthDay;
    @Size(min = 5, max= 12)
    String address;
    @Size(min = 5, max= 12)
    String specAddress;
    @ExistCategories
    List<Long> preferCategory;
    @NotNull
    Role role;
  }
}
