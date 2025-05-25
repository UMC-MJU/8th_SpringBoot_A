package umc.study.service.customerService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.exception.handler.FoodCategoryHandler;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.converter.CustomerConverter;
import umc.study.converter.CustomerPreferConverter;
import umc.study.domain.Categories;
import umc.study.domain.Customer;
import umc.study.repository.CustomerRepository;
import umc.study.repository.FoodCategoryRepository;
import umc.study.web.dto.CustomerRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerCommandServiceImpl implements CustomerCommandService {

    private final CustomerRepository customerRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Transactional
    @Override
    public Customer joinCustomer(CustomerRequestDTO.JoinDto request) {
        Customer newCustomer = CustomerConverter.toCustomer(request);
        List<Categories> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<CustomerPrefer> customerPreferList = CustomerPreferConverter.toCustomerPreferList(foodCategoryList);

        customerPreferList.forEach(customerPrefer -> customerPrefer.setCustomer(newCustomer));

        return customerRepository.save(newCustomer);
    }
}
