package ro.msg.learning.shop.service.strategy;

import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.exception.OrderNotCreatedException;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderStrategy {
    public List<Orders> createOrder(LocalDateTime timestamp, Integer deliveryAddressId, List<Integer> products, List<Integer> quantities) throws OrderNotCreatedException;
}
