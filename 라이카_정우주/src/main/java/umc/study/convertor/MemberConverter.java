package umc.study.convertor;

import umc.study.domain.Member;
import umc.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
