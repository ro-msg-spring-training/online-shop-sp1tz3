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
    private final ProductDTOManagementService service;

    @GetMapping("/products")
    List<ProductDTO> all(){
        return service.listProducts();
    }

    @PostMapping("/products")
    ProductDTO newProduct(@RequestBody ProductDTO newProductDTO){
        return service.create(
                newProductDTO
        );
    }

    @PutMapping("/products")
    ProductDTO updateProduct(@RequestBody ProductDTO newProduct){
        return (service.update(
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
        return service.readById(id);
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable int id){
        service.delete(id);
    }
}
