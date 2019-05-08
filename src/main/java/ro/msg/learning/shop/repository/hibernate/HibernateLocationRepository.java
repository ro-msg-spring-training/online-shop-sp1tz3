package ro.msg.learning.shop.repository.hibernate;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.repository.LocationRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateLocationRepository implements LocationRepository {
    private final EntityManager entityManager;

    @Override
    public List<Location> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Location> query = builder.createQuery(Location.class);
        query.select(query.from(Location.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Location save(Location location) {
        if(location.getLocationId() == null){
            entityManager.persist(location);
            return location;
        } else {
            return entityManager.merge(location);
        }
    }

    @Override
    public void remove(Location location) {
        entityManager.remove(location);
    }

    @Override
    public Optional<Location> findById(int id) {
        return Optional.ofNullable(entityManager.find(Location.class, id));
    }
}
