package umc.study.converter;

import umc.study.domain.Customer;
import umc.study.domain.enums.Gender;
import umc.study.web.dto.CustomerRequestDTO;
import umc.study.web.dto.CustomerResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerConverter {
    public static CustomerResponseDTO.JoinDTO toJoinResultDTO(Customer customer){
        return CustomerResponseDTO.JoinDTO.builder()
                .customerId(customer.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Customer toCustomer(CustomerRequestDTO.JoinDto request){
        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }

        return Customer.builder()
                .address(request.getAddress())
                .gender(gender)
                .name(request.getName())
                .customerPreferList(new ArrayList<>())
                .build();
    }
}
