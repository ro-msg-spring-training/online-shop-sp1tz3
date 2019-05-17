package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entity.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {
}
