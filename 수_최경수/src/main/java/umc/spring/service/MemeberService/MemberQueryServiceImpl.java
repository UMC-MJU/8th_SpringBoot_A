package umc.spring.service.MemeberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
  private final MemberRepository memberRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public Optional<Member> findMemberById(Long id) {
    return memberRepository.findById(id);
  }

  @Override
  public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request) {
    Authentication authentication = jwtTokenProvider.extractAuthentication(request);
    String email = authentication.getName();

    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
    return MemberConverter.toMemberInfoDTO(member);
  }
}
