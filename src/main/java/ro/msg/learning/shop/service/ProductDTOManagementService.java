package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.exception.SupplierNotFoundException;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDTOManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<ProductDTO> listProducts() {
        return repositoryFactory.createProductRepository().findAll().stream()
                .map(ProductDTO::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO create(ProductDTO dto){
        Product product = new Product();
        product.setProductId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImageURL(dto.getImageUrl());
        product.setPrice(dto.getPrice());
        product.setWeight(dto.getWeight());
        product.setProductCategory(repositoryFactory.createProductCategoryRepository().findById(dto.getCategory()).orElseThrow(ProductCategoryNotFoundException::new));
        product.setSupplier(repositoryFactory.createSupplierRepository().findById(dto.getSupplier()).orElseThrow(SupplierNotFoundException::new));
        return ProductDTO.ofEntity(repositoryFactory.createProductRepository().save(product));
    }

    @Transactional
    public void delete(int id){
        ProductRepository repo = repositoryFactory.createProductRepository();
        Product product = repo.findById(id).orElseThrow(ProductNotFoundException::new);
        repo.remove(product);
    }

    @Transactional
    public ProductDTO update(int id, String name, String description, BigDecimal price, Double weight,Integer category, Integer supplier, String imageURL){
        ProductRepository repo = repositoryFactory.createProductRepository();
        Product product = repo.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setWeight(weight);
        product.setProductCategory(repositoryFactory.createProductCategoryRepository().findById(category).orElseThrow(ProductCategoryNotFoundException::new));
        product.setSupplier(repositoryFactory.createSupplierRepository().findById(supplier).orElseThrow(SupplierNotFoundException::new));
        product.setImageURL(imageURL);
        return ProductDTO.ofEntity(repo.save(product));
    }

    @Transactional
    public ProductDTO readById(int id){
        ProductRepository repo = repositoryFactory.createProductRepository();
        return ProductDTO.ofEntity(repo.findById(id).orElseThrow(ProductNotFoundException::new));
    }
}
