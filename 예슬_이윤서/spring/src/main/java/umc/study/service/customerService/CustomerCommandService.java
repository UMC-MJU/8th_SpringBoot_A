package umc.study.service.customerService;

import umc.study.domain.Customer;
import umc.study.web.dto.CustomerRequestDTO;

public interface CustomerCommandService {
    Customer joinCustomer(CustomerRequestDTO.JoinDto request);
}
