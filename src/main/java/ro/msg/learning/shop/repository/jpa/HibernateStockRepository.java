package ro.msg.learning.shop.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateStockRepository implements StockRepository {
    private final EntityManager entityManager;

    @Override
    public List<Stock> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
        query.select(query.from(Stock.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Stock save(Stock stock) {
        if(stock.getStockId() == null) {
            entityManager.persist(stock);
            return stock;
        } else {
            return entityManager.merge(stock);
        }
    }

    @Override
    public void remove(Stock stock) {
        entityManager.remove(stock);
    }

    @Override
    public Optional<Stock> findById(int id) {
        return Optional.ofNullable(entityManager.find(Stock.class, id));
    }

}
