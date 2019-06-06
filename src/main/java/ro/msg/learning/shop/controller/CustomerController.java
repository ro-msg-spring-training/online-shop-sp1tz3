package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.CustomerDTO;
import ro.msg.learning.shop.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @GetMapping("/customers")
    List<CustomerDTO> all(){
        return service.getCustomers();
    }
}
