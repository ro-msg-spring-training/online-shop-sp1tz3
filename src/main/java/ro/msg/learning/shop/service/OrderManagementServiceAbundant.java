package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.CustomerNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceAbundant{
    private final RepositoryFactory repositoryFactory;
    private final LocationManagementService locationService;
    private final StockManagementService stockService;
    private final OrderDetailManagementService detailService;

    @Transactional
    public Orders createOrder(LocalDateTime timestamp, Address deliveryAddress, List<Integer> products, List<Integer> quantities) {
        List<Location> orderLocations = new ArrayList<>();
        List<Orders> newOrders = new ArrayList<>();
        products.forEach(p->{
            orderLocations.add(stockService.containsaMostProduct(p, quantities.get(products.indexOf(p))).getLocation());
        });
        orderLocations.forEach(ol->{
            newOrders.add(repositoryFactory.createOrderRepository().save(new Orders(ol, repositoryFactory.createCustomerRepository().findById(1).orElseThrow(CustomerNotFoundException::new), timestamp, deliveryAddress)));
        });

        return null;
    }
}
