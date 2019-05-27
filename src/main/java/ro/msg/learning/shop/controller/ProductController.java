package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.repository.CustomerRepository;
import ro.msg.learning.shop.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final CustomerRepository customerRepository;

    @GetMapping("/products")
    private List<ProductDTO> all(){
        return service.listProducts();
    }

    @PostMapping("/products")
    private ProductDTO addProduct(@RequestBody ProductDTO newProductDTO){
        return service.createProduct(newProductDTO);
    }

    @PutMapping("/products")
    private ProductDTO updateProduct(@RequestBody ProductDTO newProduct){
        return (service.updateProduct(
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
    private ProductDTO readById(@PathVariable int id){
        return service.readById(id);
    }

    @DeleteMapping("/products/{id}")
    private void deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
    }
}
