package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
  // 일반적인 400 ~ 500번대 에러
  _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의바랍니다."),
  _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
  _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
  _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

  // 멤버 관련 에러
  MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
  NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임 입력은 필수 입니다"),

  // 글 관련 에러
  ARTICLE_NOT_FOOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

  // 음식 카테고리 관련 에러
  FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY4001", "해당하는 음식 카테고리를 찾을 수 없습니다."),

  // 가게 관련 에러
  STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001", "해당하는 가게가 없습니다."),

  // 미션 관련 에러
  MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "해당하는 미션이 없습니다."),

  //테스트용
  TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "테스트용");

  private final HttpStatus httpStatus;
  private final String code;
  private final String message;

  @Override
  public ErrorReasonDTO getReason() {
    return ErrorReasonDTO.builder()
            .message(message)
            .code(code)
            .isSuccess(false)
            .build();
  }

  @Override
  public ErrorReasonDTO getReasonHttpStatus() {
    return ErrorReasonDTO.builder()
            .message(message)
            .code(code)
            .isSuccess(false)
            .httpStatus(httpStatus)
            .build();
  }
}
