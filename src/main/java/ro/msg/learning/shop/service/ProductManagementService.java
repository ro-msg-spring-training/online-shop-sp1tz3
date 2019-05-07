package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Product> listProducts() {
        return repositoryFactory.createProductRepository().findAll();
    }

    @Transactional
    public Product create(String name, String description, BigDecimal price, Double weight, ProductCategory category, Supplier supplier, String imageURL){
        return repositoryFactory.createProductRepository().save(
                new Product(name, description, price, weight, category, supplier, imageURL)
        );
    }

    @Transactional
    public void delete(int id){
        ProductRepository repo = repositoryFactory.createProductRepository();
        Product product = repo.findById(id).orElseThrow(ProductNotFoundException::new);
        repo.remove(product);
    }

    @Transactional
    public Product update(int id, String name, String description, BigDecimal price, Double weight, ProductCategory category, Supplier supplier, String imageURL){
        ProductRepository repo = repositoryFactory.createProductRepository();
        Product product = repo.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setWeight(weight);
        product.setProductCategory(category);
        product.setSupplier(supplier);
        product.setImageURL(imageURL);
        return repo.save(product);
    }

    @Transactional
    public Product readById(int id){
        ProductRepository repo = repositoryFactory.createProductRepository();
        return repo.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}
