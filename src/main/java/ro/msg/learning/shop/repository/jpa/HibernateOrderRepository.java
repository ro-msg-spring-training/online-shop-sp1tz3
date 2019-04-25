package ro.msg.learning.shop.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateOrderRepository implements OrderRepository {
    private final EntityManager entityManager;

    @Override
    public List<Orders> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Orders> query = builder.createQuery(Orders.class);
        query.select(query.from(Orders.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Orders save(Orders order) {
        if(order.getOrderId() == null){
            entityManager.persist(order);
            return order;
        } else {
            return entityManager.merge(order);
        }
    }

    @Override
    public void remove(Orders order) {
        entityManager.remove(order);
    }

    @Override
    public Optional<Orders> findById(int id) {
        return Optional.ofNullable(entityManager.find(Orders.class, id));
    }
}
