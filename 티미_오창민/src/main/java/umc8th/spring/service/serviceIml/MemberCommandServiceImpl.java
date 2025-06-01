package umc8th.spring.service.serviceIml;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc8th.spring.apiPayload.code.status.ErrorStatus;
import umc8th.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc8th.spring.apiPayload.exception.handler.MemberHandler;
import umc8th.spring.converter.MemberConverter;
import umc8th.spring.converter.MemberPreferConverter;
import umc8th.spring.domain.FoodCategory;
import umc8th.spring.domain.Member;
import umc8th.spring.domain.MemberPrefer;
import umc8th.spring.repository.FoodCategoryRepository;
import umc8th.spring.repository.MemberRepository;
import umc8th.spring.service.MemberCommandService;
import umc8th.spring.web.dto.member.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
        private final MemberRepository memberRepository;
        private final FoodCategoryRepository foodCategoryRepository;
        //config 에서 설정했던 BCryptPa....는 passwordEncoder 인터페이스의 구현체이다.



        @Override
        @Transactional //한 트렌젝션 안에서 진행된다. -> 롤백의 단위
        // 요청에서 정보를 받아, 멤버 객체를 생성하고, 저장한다.
        //멤버 생성 -> 음식 카테고리 생성-> 멤버 선호 카테고리 생성 -> 연관관계 매핑 -> 멤버 저장
        // 이 과정에서 변화가 생긴 부분 더티 체크 한 후, DB에 반영한다.
        public Member joinMember(MemberRequestDTO.JoinDto request) {
            //맴버 생성
            //컨버터는 순수한 자바 코드여서. 딱히 주입 받을 필요가 없다.
            Member newMember = MemberConverter.toMember(request);

            //음식 카테고리 생성
            //입력 받은 번호로 음식 카테고리 생성
            //요청에서 음식 목록 번호로 DB에서 꺼낸 후, 리스트로 변환 -> 만약 없는 번호를 넣었다면, FOOD_c.... 에러 반환 -> generalexception으로 처리
            List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                    .map(category -> {
                        return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                    }).collect(Collectors.toList());

            //멤버 선호 카테고리 생성
            //만들어진 음식 카테고리를 멤버 선호 음식 카테고리로 변환
            List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
            //여기서 리턴된 memberPreferList에는 아직 멤버가 없다.

            //연관관계 매핑
            memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

            //멤버 저장
            return memberRepository.save(newMember );
        }

        @Override
        public Member findMember(Long memberId) {
            return memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        }
}
