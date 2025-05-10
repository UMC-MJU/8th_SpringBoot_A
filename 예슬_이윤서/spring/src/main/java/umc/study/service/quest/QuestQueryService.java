package umc.study.service.quest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.repository.quest.QuestRepository;
import umc.study.web.dto.MissionResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestQueryService {

    private final QuestRepository questRepository;

    public Page<MissionResponse> getInProgressMissions(Long customerId, Pageable pageable) {
        return questRepository.findInProgressQuests(customerId, pageable);
    }

    public Page<MissionResponse> getCompletedMissions(Long customerId, Pageable pageable) {
        return questRepository.findCompletedQuests(customerId, pageable);
    }
}
