package umc.spring.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Member;

public interface UserRepository extends JpaRepository<Member, Long> {
}
