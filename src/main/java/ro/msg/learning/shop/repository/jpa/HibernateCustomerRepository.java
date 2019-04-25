package ro.msg.learning.shop.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateCustomerRepository implements CustomerRepository {
    private final EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        query.select(query.from(Customer.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Customer save(Customer customer) {
        if(customer.getCustomerId() == null) {
            entityManager.persist(customer);
            return customer;
        } else {
            return entityManager.merge(customer);
        }
    }

    @Override
    public void remove(Customer customer) {
        entityManager.remove(customer);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return Optional.ofNullable(entityManager.find(Customer.class, id));
    }

}
