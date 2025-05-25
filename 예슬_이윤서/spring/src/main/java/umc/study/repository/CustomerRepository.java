package umc.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
