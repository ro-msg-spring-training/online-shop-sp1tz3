package ro.msg.learning.shop.repository.hibernate;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.repository.OrderDetailRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateOrderDetailRepository implements OrderDetailRepository {
    private final EntityManager entityManager;

    @Override
    public List<OrderDetail> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> query = builder.createQuery(OrderDetail.class);
        query.select(query.from(OrderDetail.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        if(orderDetail.getOrderDetailId() == null) {
            entityManager.persist(orderDetail);
            return orderDetail;
        } else {
            return entityManager.merge(orderDetail);
        }
    }

    @Override
    public void remove(OrderDetail orderDetail) {
        entityManager.remove(orderDetail);
    }

    @Override
    public Optional<OrderDetail> findById(int id) {
        return Optional.ofNullable(entityManager.find(OrderDetail.class, id));
    }

}
