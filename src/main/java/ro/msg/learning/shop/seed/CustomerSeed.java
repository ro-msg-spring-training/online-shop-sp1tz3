package ro.msg.learning.shop.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

@Component
@RequiredArgsConstructor
public class CustomerSeed implements CommandLineRunner {
    private final RepositoryFactory factory;

    @Override
    public void run(String... args) throws Exception {
    /*    CustomerRepository repo = factory.createCustomerRepository();
        if(repo.findAll().isEmpty()){
            repo.save(new Customer("a","a","a","a","a"));
            repo.save(new Customer("b","b","b","b","b"));
            repo.save(new Customer("c","c","c","c","c"));
            repo.save(new Customer("d","d","d","d","d"));
        }*/
    }
}
