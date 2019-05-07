package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Revenue;
import java.util.List;
import java.util.Optional;

public interface RevenueRepository {
    List<Revenue> findAll();
    Revenue save(Revenue revenue);
    void remove(Revenue revenue);
    Optional<Revenue> findById(int id);
}
