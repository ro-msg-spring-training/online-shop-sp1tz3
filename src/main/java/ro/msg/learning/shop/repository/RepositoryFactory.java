package ro.msg.learning.shop.repository;

public interface RepositoryFactory {
    AddressRepository createAddressRepository();
    CustomerRepository createCustomerRepository();
    LocationRepository createLocationRepository();
    OrderDetailRepository createOrderDetailRepository();
    OrderRepository createOrderRepository();
    ProductCategoryRepository createProductCategoryRepository();
    ProductRepository createProductRepository();
    RevenueRepository createRevenueRepository();
    StockRepository createStockRepository();
    SupplierRepository createSupplierRepository();
}
