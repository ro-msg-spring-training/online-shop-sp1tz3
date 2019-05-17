package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Revenue;

@Repository
public interface RevenueRepository extends CrudRepository<Revenue, Integer> {
}
