package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.exception.OrderNotFoundException;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Orders> listOrders(){
        return repositoryFactory.createOrderRepository().findAll();
    }

    @Transactional
    public Orders addOrder(Location location, Customer customer, LocalDateTime createdAt, String country, String city, String county, String streetAddress){
        return repositoryFactory.createOrderRepository().save(new Orders(
               location, customer, createdAt, country, city, county, streetAddress
        ));
    }

    @Transactional
    public void removeOrder(int id){
        OrderRepository repo = repositoryFactory.createOrderRepository();
        Orders order = repo.findById(id).orElseThrow(OrderNotFoundException::new);
        repo.remove(order);
    }
}
