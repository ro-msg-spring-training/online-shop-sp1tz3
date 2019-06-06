package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.CustomerDTO;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repo;

    @Transactional
    public List<CustomerDTO> getCustomers(){
        Iterable<Customer> customers = repo.findAll();
        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);
        return customerList.stream().map(CustomerDTO::ofEntity).collect(Collectors.toList());
    }
}
