package ro.msg.learning.shop.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateProductCategoryRepository implements ProductCategoryRepository {
    private final EntityManager entityManager;

    @Override
    public List<ProductCategory> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductCategory> query = builder.createQuery(ProductCategory.class);
        query.select(query.from(ProductCategory.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        if(productCategory.getProductCategoryId() == null) {
            entityManager.persist(productCategory);
            return productCategory;
        } else {
            return entityManager.merge(productCategory);
        }
    }

    @Override
    public void remove(ProductCategory productCategory) {
        entityManager.remove(productCategory);
    }

    @Override
    public Optional<ProductCategory> findById(int id) {
        return Optional.ofNullable(entityManager.find(ProductCategory.class, id));
    }

    @Override
    public ProductCategory findByName(String name){
        return (ProductCategory)entityManager.createQuery("select pc from productCategory pc where pc.name like :pcname").setParameter("pcname", name).getSingleResult();

    }

}
