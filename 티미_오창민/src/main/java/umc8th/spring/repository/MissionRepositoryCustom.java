package umc8th.spring.repository;


import umc8th.spring.domain.Member;
import umc8th.spring.domain.Mission;
import umc8th.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionRepositoryCustom {
    //아직 api 명세서 형식이 나오지 않아 객체를 그대로 반환하도록 짜겟닷

    //진행중,진행 완료한 미션을 모아서 보는 쿼리(페이지 포함)
    //이름을 어떻게 지어야 할까..
    List<Mission> getMissionsByMemberAndStatus(MissionStatus status, Member member, Integer page);



}
