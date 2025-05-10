package umc.study.repository.quest;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long>, QuestRepositoryCustom {
}
