package ro.msg.learning.shop.repository;

public interface RepositoryFactory {
    CustomerRepository createCustomerRepository();
    OrderRepository createOrderRepository();
    LocationRepository createLocationRepository();
}
