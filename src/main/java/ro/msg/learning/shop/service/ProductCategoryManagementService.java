package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.ProductCategoryNotFoundException;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<ProductCategory> listProductCategorys(){
        return repositoryFactory.createProductCategoryRepository().findAll();
    }

    @Transactional
    public ProductCategory addProductCategory(String name, String description){
        return repositoryFactory.createProductCategoryRepository().save(new ProductCategory(
                name, description
        ));
    }

    @Transactional
    public void removeProductCategory(int id){
        ProductCategoryRepository repo = repositoryFactory.createProductCategoryRepository();
        ProductCategory productCategory = repo.findById(id).orElseThrow(ProductCategoryNotFoundException::new);
        repo.remove(productCategory);
    }

    @Transactional
    public ProductCategory findByName(String name){
        ProductCategoryRepository repo = repositoryFactory.createProductCategoryRepository();
        for(ProductCategory pc: repo.findAll()){
            if(pc.getName().equals(name)){
                return pc;
            }
        }
        return null;
    }
}
