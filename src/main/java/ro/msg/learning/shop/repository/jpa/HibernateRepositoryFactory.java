package ro.msg.learning.shop.repository.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repo-type", havingValue = "jpa")
public class HibernateRepositoryFactory implements RepositoryFactory {
    private final EntityManager entityManager;

    @Override
    public CustomerRepository createCustomerRepository() {
        return new HibernateCustomerRepository(entityManager);
    }

    @Override
    public OrderRepository createOrderRepository() {
        return new HibernateOrderRepository(entityManager);
    }

    @Override
    public LocationRepository createLocationRepository() { return new HibernateLocationRepository(entityManager); }
}
