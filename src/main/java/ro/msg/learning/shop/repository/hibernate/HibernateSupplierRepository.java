package ro.msg.learning.shop.repository.hibernate;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.SupplierRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateSupplierRepository implements SupplierRepository {
    private final EntityManager entityManager;

    @Override
    public List<Supplier> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Supplier> query = builder.createQuery(Supplier.class);
        query.select(query.from(Supplier.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Supplier save(Supplier supplier) {
        if(supplier.getSupplierId() == null) {
            entityManager.persist(supplier);
            return supplier;
        } else {
            return entityManager.merge(supplier);
        }
    }

    @Override
    public void remove(Supplier supplier) {
        entityManager.remove(supplier);
    }

    @Override
    public Optional<Supplier> findById(int id) {
        return Optional.ofNullable(entityManager.find(Supplier.class, id));
    }

    @Override
    public Optional<Supplier> findByName(String name) {
        return Optional.ofNullable(entityManager.find(Supplier.class, name));
    }

}
