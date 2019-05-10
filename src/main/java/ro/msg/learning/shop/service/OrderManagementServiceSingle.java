package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.AddressNotFoundException;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceSingle{
    private final LocationManagementService locationService;
    private final StockManagementService stockService;
    private final OrderDetailManagementService detailService;
    private final ProductManagementService productService;
    private final CustomerManagementService customerService;
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Orders> listOrders(){
        return repositoryFactory.createOrderRepository().findAll();
    }

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
            detailService.addOrderDetail(newOrder, productService.readById(p), quantities.get(products.indexOf(p)));
        }

        return null;
    }
}
