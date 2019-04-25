package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Orders;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Orders> findAll();
    Orders save(Orders order);
    void remove(Orders order);
    Optional<Orders> findById(int id);
}
