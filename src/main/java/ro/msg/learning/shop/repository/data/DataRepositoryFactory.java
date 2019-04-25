package ro.msg.learning.shop.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repo-type", havingValue = "data")
public class DataRepositoryFactory implements RepositoryFactory {
    private final DataCustomerRepository customerRepository;
    private final DataOrderRepository orderRepository;

    @Override
    public CustomerRepository createCustomerRepository() {
        return customerRepository;
    }

    @Override
    public OrderRepository createOrderRepository() {
        return orderRepository;
    }

    @Override
    public LocationRepository createLocationRepository() {
        return null;
    }
}
