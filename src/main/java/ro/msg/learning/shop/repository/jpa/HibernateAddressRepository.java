package ro.msg.learning.shop.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.repository.AddressRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateAddressRepository implements AddressRepository {
    private final EntityManager entityManager;

    @Override
    public List<Address> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Address> query = builder.createQuery(Address.class);
        query.select(query.from(Address.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Address save(Address address) {
        if(address.getAddressId() == null) {
            entityManager.persist(address);
            return address;
        } else {
            return entityManager.merge(address);
        }
    }

    @Override
    public void remove(Address address) {
        entityManager.remove(address);
    }

    @Override
    public Optional<Address> findById(int id) {
        return Optional.ofNullable(entityManager.find(Address.class, id));
    }

}
