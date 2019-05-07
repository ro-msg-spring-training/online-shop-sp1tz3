package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Stock;
import java.util.List;
import java.util.Optional;

public interface StockRepository {
    List<Stock> findAll();
    Stock save(Stock stock);
    void remove(Stock stock);
    Optional<Stock> findById(int id);
}
