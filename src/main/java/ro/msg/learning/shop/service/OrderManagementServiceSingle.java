package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceSingle implements OrderStrategy {
    private final RepositoryFactory repositoryFactory;

    @Override
    public Orders createOrder(LocalDateTime timestamp, Address deliveryAddress, Map<Integer, Integer> products) {
        List<Stock> stocks = repositoryFactory.createStockRepository().findAll();
        stocks.forEach(s->{
            
        });
        return null;
    }
}
