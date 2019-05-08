package ro.msg.learning.shop.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.repository.*;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "repo-type", havingValue = "data")
public class DataRepositoryFactory implements RepositoryFactory {
    private final DataAddressRepository addressRepository;
    private final DataCustomerRepository customerRepository;
    private final DataLocationRepository locationRepository;
    private final DataOrderRepository orderRepository;
    private final DataOrderDetailRepository orderDetailRepository;
    private final DataProductCategoryRepository productCategoryRepository;
    private final DataProductRepository productRepository;
    private final DataRevenueRepository revenueRepository;
    private final DataStockRepository stockRepository;
    private final DataSupplierRepository supplierRepository;

    @Override
    public AddressRepository createAddressRepository(){return addressRepository;}

    @Override
    public CustomerRepository createCustomerRepository() {
        return customerRepository;
    }

    @Override
    public LocationRepository createLocationRepository() {
        return locationRepository;
    }

    @Override
    public OrderDetailRepository createOrderDetailRepository() {
        return orderDetailRepository;
    }

    @Override
    public OrderRepository createOrderRepository() {
        return orderRepository;
    }

    @Override
    public ProductCategoryRepository createProductCategoryRepository() {
        return productCategoryRepository;
    }

    @Override
    public ProductRepository createProductRepository() {
        return productRepository;
    }

    @Override
    public RevenueRepository createRevenueRepository() {
        return revenueRepository;
    }

    @Override
    public StockRepository createStockRepository() {
        return stockRepository;
    }

    @Override
    public SupplierRepository createSupplierRepository() {
        return supplierRepository;
    }
}
