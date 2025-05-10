package umc.study.repository.quest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.study.domain.Quest;
import umc.study.web.dto.MissionResponse;

public interface QuestRepositoryCustom {

    /**
     * 내가 진행중인 미션 조회 (isComplete = false)
     */
    Page<MissionResponse> findInProgressQuests(Long customerId, Pageable pageable);

    /**
     * 내가 완료한 미션 조회 (isComplete = true)
     */
    Page<MissionResponse> findCompletedQuests(Long customerId, Pageable pageable);
}
