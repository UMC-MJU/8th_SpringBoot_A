package umc8th.spring.converter;

import umc8th.spring.domain.Member;
import umc8th.spring.domain.enums.Gender;
import umc8th.spring.web.dto.member.MemberRequestDTO;
import umc8th.spring.web.dto.member.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {

    //회원 가입 요청이 섣공하고, 만들어진 멤버를 memberResponseDTO에 담아서
    //성공 응답 API의 result 부분을 채울 것!
    //어딘가에서 회원 가입 요청을 받아 정보를 가지고, 멤버를 만들고, 이 메서드를 실행하겠지..
    public static MemberResponseDTO.joinResultDTO toJoinResultDTo(Member member) {
        return MemberResponseDTO.joinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    //바로 여기서 멤버를 만듭니다!
    //사용자가 회원 가입 요청하면서 입력했던 정보가 joinDTO에 담겨 들어온다.
    //들어온 정보로 멤버를 생성 후, 맴버 리턴
    public static Member toMember(MemberRequestDTO.JoinDto request) {

        Gender gender = null; //성별 초기화 -> null
        switch (request.getGender()) { //사용자가 입력한 성별에 대해서
            case 1: //남자
                gender = Gender.MALE;
                break;
            case 2: //여자
                gender = Gender.FEMALE;
                break;
            case 3: //없음
                gender = Gender.NONE;
                break;
        }
        //맴버 생성 및 리턴
        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>()) //리스트는 초기화 해야한다. -> 안하면 null로 될 걸?
                .build();

    }
}
