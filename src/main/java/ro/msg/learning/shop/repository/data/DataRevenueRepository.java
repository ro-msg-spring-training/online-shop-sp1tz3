package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Revenue;
import ro.msg.learning.shop.repository.RevenueRepository;

public interface DataRevenueRepository extends Repository<Revenue, Integer>, RevenueRepository {
    void delete(Revenue address);

    @Override
    default void remove(Revenue address) {delete(address);}
}
