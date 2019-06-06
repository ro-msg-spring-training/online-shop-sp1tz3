package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomersWithEncodedPasswords implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        if(customerRepository.count()==0)
         customerRepository.save(new Customer("a","a","a","a","a"));
    }
}


