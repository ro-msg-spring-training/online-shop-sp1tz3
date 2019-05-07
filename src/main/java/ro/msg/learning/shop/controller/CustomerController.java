package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.service.CustomerManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerManagementService repo;

    @GetMapping("/customers")
    List<Customer> all(){
        return repo.listCustomers();
    }

    @PostMapping("/customers")
    Customer newCustomer(@RequestBody Customer newCustomer){
        return repo.addCustomer(newCustomer.getFirstName(),
                newCustomer.getLastName(),
                newCustomer.getUsername(),
                newCustomer.getPassword(),
                newCustomer.getEmailAddress());
    }
}
