package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.exception.SupplierNotFoundException;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final SupplierRepository supplierRepository;

    @Transactional
    public List<ProductDTO> listProducts(){
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);
        return productList.stream().map(ProductDTO::ofEntity).collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO dto){
        Product product = new Product();
        product.setProductId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImageURL(dto.getImageUrl());
        product.setPrice(dto.getPrice());
        product.setWeight(dto.getWeight());
        product.setProductCategory(productCategoryRepository.findById(dto.getCategory()).orElseThrow(ProductCategoryNotFoundException::new));
        product.setSupplier(supplierRepository.findById(dto.getSupplier()).orElseThrow(SupplierNotFoundException::new));
        return ProductDTO.ofEntity(productRepository.save(product));
    }

    @Transactional
    public ProductDTO updateProduct(int id, String name, String description, BigDecimal price, Double weight, Integer category, Integer supplier, String imageURL){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setWeight(weight);
        product.setProductCategory(productCategoryRepository.findById(category).orElseThrow(ProductCategoryNotFoundException::new));
        product.setSupplier(supplierRepository.findById(supplier).orElseThrow(SupplierNotFoundException::new));
        product.setImageURL(imageURL);
        return ProductDTO.ofEntity(productRepository.save(product));
    }

    @Transactional
    public ProductDTO readById(int id){
        return ProductDTO.ofEntity(productRepository.findById(id).orElseThrow(ProductNotFoundException::new));
    }

    @Transactional
    public void deleteProduct(int id){
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }

}
