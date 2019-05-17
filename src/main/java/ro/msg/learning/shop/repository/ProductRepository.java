package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
