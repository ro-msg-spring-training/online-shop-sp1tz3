package ro.msg.learning.shop.repository.hibernate;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Revenue;
import ro.msg.learning.shop.repository.RevenueRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateRevenueRepository implements RevenueRepository {
    private final EntityManager entityManager;

    @Override
    public List<Revenue> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Revenue> query = builder.createQuery(Revenue.class);
        query.select(query.from(Revenue.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Revenue save(Revenue revenue) {
        if(revenue.getRevenueId() == null) {
            entityManager.persist(revenue);
            return revenue;
        } else {
            return entityManager.merge(revenue);
        }
    }

    @Override
    public void remove(Revenue revenue) {
        entityManager.remove(revenue);
    }

    @Override
    public Optional<Revenue> findById(int id) {
        return Optional.ofNullable(entityManager.find(Revenue.class, id));
    }

}
