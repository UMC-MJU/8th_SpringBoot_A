package umc.spring.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.properties.Constants;
import umc.spring.config.properties.JwtProperties;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
  private final JwtProperties jwtProperties;

  private Key getSigningKey(){
    return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
  }

  // 인증 정보를 받아서, JWT Access Token을 생성하고 반환함
  public String generateToken(Authentication authentication) {
    String email = authentication.getName();

    return Jwts.builder()
            .setSubject(email)
            .claim("role", authentication.getAuthorities().iterator().next().getAuthority())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration().getAccess()))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
  }


  // JWT 토큰이 유효한지 검증, 파싱되면 유효한 토큰이고 만료되거나 위조, 형식 오류가 생기면 예외가 발생하여 false를 반환함
  public boolean validateToken(String token){
    try{
      Jwts.parserBuilder()
              .setSigningKey(getSigningKey())
              .build()
              .parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e){
      return false;
    }
  }

  // Jwt 토큰에서 인증 정보를 추출해서, Spring Security의 Authentication 객체로 변환해줌
  public Authentication getAuthentication(String token){
    Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    String email = claims.getSubject();
    String role = claims.get("role", String.class);

    User principal = new User(email, "", Collections.singleton(() -> role));
    return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
  }


  // 순수 토큰을 반환해주는 메소드
  public static String resolveToken(HttpServletRequest request) {
    String bearerToken = request.getHeader(Constants.AUTH_HEADER);
    if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
      return bearerToken.substring(Constants.TOKEN_PREFIX.length());
    }
    return null;
  }

  public Authentication extractAuthentication(HttpServletRequest request){
    String accessToken = resolveToken(request);
    if(accessToken == null || !validateToken(accessToken)) {
      throw new MemberHandler(ErrorStatus.INVALID_TOKEN);
    }
    return getAuthentication(accessToken);
  }
}
