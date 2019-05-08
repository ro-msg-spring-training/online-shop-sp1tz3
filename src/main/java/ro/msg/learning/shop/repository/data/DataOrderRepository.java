package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.repository.OrderRepository;

public interface DataOrderRepository extends Repository<Orders, Integer>, OrderRepository {
    void delete(Orders address);

    @Override
    default void remove(Orders address) {delete(address);}
}
