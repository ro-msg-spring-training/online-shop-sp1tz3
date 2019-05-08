package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.AddressRepository;
import ro.msg.learning.shop.repository.CustomerRepository;

public interface DataCustomerRepository extends Repository<Customer, Integer>, CustomerRepository {
    void delete(Customer address);

    @Override
    default void remove(Customer address) {delete(address);}
}
