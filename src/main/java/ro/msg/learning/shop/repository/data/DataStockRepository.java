package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;

public interface DataStockRepository extends Repository<Stock, Integer>, StockRepository {
    void delete(Stock address);

    @Override
    default void remove(Stock address) {delete(address);}
}
