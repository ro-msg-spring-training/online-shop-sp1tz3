package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.ProductCategory;
import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository {
    List<ProductCategory> findAll();
    ProductCategory save(ProductCategory productCategory);
    void remove(ProductCategory productCategory);
    Optional<ProductCategory> findById(int id);
}
