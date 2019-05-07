package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Orders;

import java.time.LocalDateTime;
import java.util.Map;

public interface OrderStrategy {
    public Orders createOrder(LocalDateTime timestamp, Address deliveryAddress, Map<Integer, Integer> products);
}
