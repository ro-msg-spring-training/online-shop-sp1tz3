package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.OrderDetail;
import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {
    List<OrderDetail> findAll();
    OrderDetail save(OrderDetail orderDetail);
    void remove(OrderDetail orderDetail);
    Optional<OrderDetail> findById(int id);
}
