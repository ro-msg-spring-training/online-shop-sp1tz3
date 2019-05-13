package ro.msg.learning.shop.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.AddressNotFoundException;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.service.LocationManagementService;
import ro.msg.learning.shop.service.OrderDetailManagementService;
import ro.msg.learning.shop.service.ProductManagementService;
import ro.msg.learning.shop.service.StockManagementService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceSingle implements OrderStrategy{
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
    public List<Orders> createOrder(LocalDateTime timestamp, Integer deliveryAddressId, List<Integer> products, List<Integer> quantities) {
        Location location = locationService.containsAll(products, quantities);
        Orders newOrder = new Orders();
        List<OrderDetail> newOrderDetails = new ArrayList<>();
        List<Orders> allNewOrders = new ArrayList<>();
        if(location!= null){
            newOrder = repositoryFactory.createOrderRepository().save(
                    new Orders(location, repositoryFactory.createCustomerRepository().findById(1).orElseThrow(CustomerNotFoundException::new),
                            timestamp, repositoryFactory.createAddressRepository().findById(deliveryAddressId).orElseThrow(AddressNotFoundException::new))
            );
            allNewOrders.add(newOrder);
        }
        for(Integer p: products){
            for(Stock s: location.getStocks()){
                if(s.getProduct().getProductId().equals(p)){
                    stockService.update(s.getStockId(), s.getQuantity() - quantities.get(products.indexOf(p)));
                }
            }
            detailService.addOrderDetail(newOrder, productService.readById(p), quantities.get(products.indexOf(p)));
        }
        return null;
    }
}
