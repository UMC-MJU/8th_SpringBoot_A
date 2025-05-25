package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.CustomerConverter;
import umc.study.domain.Customer;
import umc.study.service.customerService.CustomerCommandService;
import umc.study.web.dto.CustomerRequestDTO;
import umc.study.web.dto.CustomerResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Customer")
public class CustomerRestController {

    private final CustomerCommandService customerCommandService;

    @PostMapping("/")
    public ApiResponse<CustomerResponseDTO.JoinDTO> join(@RequestBody @Valid CustomerRequestDTO.JoinDto request) {
        Customer customer = customerCommandService.joinCustomer(request);
        return ApiResponse.onSuccess(CustomerConverter.toJoinResultDTO(customer));
    }
}
