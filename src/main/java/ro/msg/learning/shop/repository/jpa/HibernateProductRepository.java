package ro.msg.learning.shop.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateProductRepository implements ProductRepository {
    private final EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        query.select(query.from(Product.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Product save(Product product) {
        if(product.getProductId() == null) {
            entityManager.persist(product);
            return product;
        } else {
            return entityManager.merge(product);
        }
    }

    @Override
    public void remove(Product product) {
        entityManager.remove(product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

}
