package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Orders;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderStrategy {
    public List<Orders> createOrder(LocalDateTime timestamp, Integer deliveryAddressId, List<Integer> products, List<Integer> quantities);
}
