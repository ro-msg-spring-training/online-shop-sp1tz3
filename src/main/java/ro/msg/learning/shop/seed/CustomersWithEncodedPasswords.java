package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomersWithEncodedPasswords implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    //private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        //customerRepository.save(new Customer("a","a","a",passwordEncoder.encode("a"),"a"));
    }
}
