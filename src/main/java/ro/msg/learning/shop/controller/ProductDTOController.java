package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductDTOManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductDTOController {
    private final ProductDTOManagementService repo;

    @GetMapping("/products")
    List<ProductDTO> all(){
        return repo.listProducts();
    }

    @PostMapping("/products")
    ProductDTO newProduct(@RequestBody ProductDTO newProductDTO){
        return repo.create(
                newProductDTO
        );
    }

    @PutMapping("/products")
    ProductDTO updateProduct(@RequestBody ProductDTO newProduct){
        return (repo.update(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice(),
                newProduct.getWeight(),
                newProduct.getCategory(),
                newProduct.getSupplier(),
                newProduct.getImageUrl()
        ));
    }

    @GetMapping("/products/{id}")
    ProductDTO readById(@PathVariable int id){
        return repo.readById(id);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable int id){
        repo.delete(id);
    }
}
