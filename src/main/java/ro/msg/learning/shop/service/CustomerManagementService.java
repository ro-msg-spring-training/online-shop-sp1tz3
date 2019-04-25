package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.exception.CustomerNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Customer> listCustomers() {return repositoryFactory.createCustomerRepository().findAll();}

    @Transactional
    public Customer addCustomer(String firstName, String lastName, String username, String password, String emailAddress) { return repositoryFactory.createCustomerRepository().save(
            new Customer(firstName, lastName, username, password, emailAddress)
    );}

    @Transactional
    public void removeCustomer(int id){
        CustomerRepository repo = repositoryFactory.createCustomerRepository();
        Customer customer = repo.findById(id).orElseThrow(CustomerNotFoundException::new);
        repo.remove(customer);
    }
}
