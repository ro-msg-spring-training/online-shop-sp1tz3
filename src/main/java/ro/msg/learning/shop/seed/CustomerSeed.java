package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomerSeed implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Customer customer = new Customer("a","a","a",passwordEncoder.encode("a"),"a");
        Customer customer2 = new Customer("b","b","b",passwordEncoder.encode("b"),"b");
        customerRepository.save(customer);
        customerRepository.save(customer2);
    }
}
