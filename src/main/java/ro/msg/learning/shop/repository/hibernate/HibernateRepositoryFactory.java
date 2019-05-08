package ro.msg.learning.shop.repository.hibernate;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.repository.*;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repo-type", havingValue = "hibernate")
public class HibernateRepositoryFactory implements RepositoryFactory {
    private final EntityManager entityManager;

    @Override
    public AddressRepository createAddressRepository() {
        return new HibernateAddressRepository(entityManager);
    }

    @Override
    public CustomerRepository createCustomerRepository() {
        return new HibernateCustomerRepository(entityManager);
    }

    @Override
    public OrderRepository createOrderRepository() {
        return new HibernateOrderRepository(entityManager);
    }

    @Override
    public ProductCategoryRepository createProductCategoryRepository() {
        return new HibernateProductCategoryRepository(entityManager);
    }

    @Override
    public ProductRepository createProductRepository() {
        return new HibernateProductRepository(entityManager);
    }

    @Override
    public RevenueRepository createRevenueRepository() {
        return new HibernateRevenueRepository(entityManager);
    }

    @Override
    public StockRepository createStockRepository() {
        return new HibernateStockRepository(entityManager);
    }

    @Override
    public SupplierRepository createSupplierRepository() {
        return new HibernateSupplierRepository(entityManager);
    }

    @Override
    public LocationRepository createLocationRepository() { return new HibernateLocationRepository(entityManager); }

    @Override
    public OrderDetailRepository createOrderDetailRepository() {
        return new HibernateOrderDetailRepository(entityManager);
    }
}
