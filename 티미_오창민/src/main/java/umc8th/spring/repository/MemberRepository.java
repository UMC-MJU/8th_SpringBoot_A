package umc8th.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring.domain.Member;
import umc8th.spring.domain.enums.MemberStatus;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameAndStatus(String name, MemberStatus status);
//    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.status = :status")
//    List<Member> findByNameAndStatus(@Param("name") String name, @Param("status") MemberStatus status);

}
