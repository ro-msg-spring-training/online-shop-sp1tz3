package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entity.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer> {
}
