package umc8th.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc8th.spring.domain.Member;
import umc8th.spring.domain.Review;
import umc8th.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review, Long>  {
    Page<Review> findAllByMemberAndStore(Member member, Store store, PageRequest pageRequest);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
