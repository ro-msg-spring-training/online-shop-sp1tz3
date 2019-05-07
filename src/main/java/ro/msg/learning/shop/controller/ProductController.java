package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductManagementService;

import java.util.List;

//@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductManagementService repo;

    @GetMapping("/products")
    List<Product> all(){
        return repo.listProducts();
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct){
        return repo.create(
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice(),
                newProduct.getWeight(),
                newProduct.getProductCategory(),
                newProduct.getSupplier(),
                newProduct.getImageURL()
                );
    }

    @PutMapping("/products")
    Product updatedProduct(@RequestBody Product newProduct){
        return repo.update(
                newProduct.getProductId(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice(),
                newProduct.getWeight(),
                newProduct.getProductCategory(),
                newProduct.getSupplier(),
                newProduct.getImageURL()
        );
    }

    @GetMapping("/products/{id}")
    Product readById(@PathVariable int id){
        return repo.readById(id);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable int id){
        repo.delete(id);
    }
}
