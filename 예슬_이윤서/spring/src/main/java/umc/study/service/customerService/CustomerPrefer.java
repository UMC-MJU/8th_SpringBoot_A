package umc.study.service.customerService;

import jakarta.persistence.*;
import lombok.*;
import umc.study.domain.Categories;
import umc.study.domain.Customer;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CustomerPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories foodCategory;

    public void setMember(Customer customer) {

    }

    public void setFoodCategory(Categories foodCategory){
        this.foodCategory = foodCategory;
    }

    public void setCustomer(Customer newCustomer) {
    }
}