package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.exception.AddressNotFoundException;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAbundant implements OrderStrategy{
    @Autowired
    private RepositoryFactory repositoryFactory;
    @Autowired
    private LocationManagementService locationService;
    @Autowired
    private StockManagementService stockService;
    @Autowired
    private OrderDetailManagementService detailService;
    @Autowired
    private ProductManagementService productService;

    @Override
    @Transactional
    public List<Orders> createOrder(LocalDateTime timestamp, Integer deliveryAddress, List<Integer> products, List<Integer> quantities) {
        Location ol;
        Orders newOrder;
        List<Orders> allNewOrders = new ArrayList<>();
        for(Integer p: products){
            ol = (stockService.containsaMostProduct(p, quantities.get(products.indexOf(p))).getLocation());
            newOrder = (repositoryFactory.createOrderRepository().save(new Orders(ol, repositoryFactory.createCustomerRepository().findById(1).orElseThrow(CustomerNotFoundException::new), timestamp, repositoryFactory.createAddressRepository().findById(deliveryAddress).orElseThrow(AddressNotFoundException::new))));
            detailService.addOrderDetail(newOrder, productService.readById(p), quantities.get(products.indexOf(p)));
            allNewOrders.add(newOrder);
        };
        return null;
    }
}
