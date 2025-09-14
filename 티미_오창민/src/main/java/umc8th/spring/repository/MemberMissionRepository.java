package umc8th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring.domain.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom{
}
