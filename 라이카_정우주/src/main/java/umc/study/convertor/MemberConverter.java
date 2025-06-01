package umc.study.convertor;

import org.springframework.data.domain.Page;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.enums.Gender;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            default:
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewDTO toReviewPreViewDTO(Review review) {
        return MemberResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewListDTO toReviewPreViewListDTO(Page<Review> reviewPage) {
        List<MemberResponseDTO.ReviewPreViewDTO> reviewList = reviewPage.stream()
                .map(MemberConverter::toReviewPreViewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }

    public static MemberResponseDTO.MemberMissionPreViewDTO toMemberMissionPreViewDTO(MemberMission memberMission) {
        return MemberResponseDTO.MemberMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.MemberMissionPreViewListDTO toMemberMissionPreViewListDTO(Page<MemberMission> memberMissionPage) {
        List<MemberResponseDTO.MemberMissionPreViewDTO> missionList = memberMissionPage.stream()
                .map(MemberConverter::toMemberMissionPreViewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.MemberMissionPreViewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }

    public static MemberResponseDTO.CompletedMissionPreViewDTO toCompletedMissionPreViewDTO(MemberMission memberMission) {
        return MemberResponseDTO.CompletedMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus())
                .completedAt(memberMission.getUpdatedAt()) // 완료 시간
                .build();
    }

    public static MemberResponseDTO.CompletedMissionPreViewListDTO toCompletedMissionPreViewListDTO(Page<MemberMission> memberMissionPage) {
        List<MemberResponseDTO.CompletedMissionPreViewDTO> missionList = memberMissionPage.stream()
                .map(MemberConverter::toCompletedMissionPreViewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.CompletedMissionPreViewListDTO.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}
