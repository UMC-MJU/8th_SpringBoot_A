package umc.spring.service.MemeberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.service.FoodCategoryService.FoodCategoryRepository;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
  private final MemberRepository memberRepository;
  private final FoodCategoryRepository foodCategoryRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  @Transactional
  public Member joinMember(MemberRequestDTO.JoinDto request) {

    Member newMember = MemberConverter.toMember(request);
    newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
    List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
            .map(category -> {
              return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
            }).collect(Collectors.toList());

    List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

    memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

    return memberRepository.save(newMember);
  }

  @Override
  public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
    // 요청값의 email로 기존 회원이 존재하는지를 따지고, 존재한다면 Member 객체가 반환
    Member member = memberRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new MemberHandler(ErrorStatus.MISSION_NOT_FOUND));

    // Member의 패스워드 요청값의 password가 일치하는지 따져서, 일치하지 않으면 예외를 반환
    if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
      throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
    }

    // 위 메서드를 통과하면, 로그인에 필요한 Authentication 객체를 만들어서 jwt Token을 발급해줌
    Authentication authentication = new UsernamePasswordAuthenticationToken(
            member.getEmail(), null,
            Collections.singleton(() -> member.getRole().name())
    );
    String accessToken = jwtTokenProvider.generateToken(authentication);

    return MemberConverter.toLoginResultDTO(
            member.getId(),
            accessToken
    );
  }
}
