package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
