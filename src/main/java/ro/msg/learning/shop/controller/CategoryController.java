package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.CategoryDTO;
import ro.msg.learning.shop.service.CategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping("/categories")
    public List<CategoryDTO> all(){
        return service.getAll();
    }
}
