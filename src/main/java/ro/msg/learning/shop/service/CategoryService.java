package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.CategoryDTO;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProductCategoryRepository repo;

    @Transactional
    public List<CategoryDTO> getAll(){
        Iterable<ProductCategory> categories = repo.findAll();
        List<ProductCategory> categoryList = new ArrayList<>();
        categories.forEach(categoryList::add);
        return categoryList.stream().map(CategoryDTO::ofEntity).collect(Collectors.toList());
    }
}