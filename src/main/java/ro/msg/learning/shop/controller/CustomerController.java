package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Customer;

import ro.msg.learning.shop.service.CustomerManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerManagementService service;

    @GetMapping("/customers")
    List<Customer> all(){
        return service.listCustomers();
    }

    @PostMapping("/customers")
    Customer newCustomer(@RequestBody Customer newCustomer){
        return service.addCustomer(newCustomer.getFirstName(),
                newCustomer.getLastName(),
                newCustomer.getUsername(),
                newCustomer.getPassword(),
                newCustomer.getEmailAddress());
    }
}
