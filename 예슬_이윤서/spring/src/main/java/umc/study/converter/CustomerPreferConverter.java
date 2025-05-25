package umc.study.converter;

import umc.study.domain.Categories;
import umc.study.service.customerService.CustomerPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerPreferConverter {
    public static List<CustomerPrefer> toCustomerPreferList(List<Categories> foodCategoryList) {

        return foodCategoryList.stream()
                .map(foodCategory ->
                        CustomerPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
