package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockManagementService {
    private RepositoryFactory repositoryFactory;

    @Transactional
    public List<Stock> listStocks(){
        return repositoryFactory.createStockRepository().findAll();
    }

    @Transactional
    public Stock create(Product product, Location location, Integer quantity){
        return repositoryFactory.createStockRepository().save(
                new Stock(product, location, quantity)
        );
    }

    @Transactional
    public void delete(int id){
        StockRepository repo = repositoryFactory.createStockRepository();
        Stock stock = repo.findById(id).orElseThrow(StockNotFoundException::new);
        repo.remove(stock);
    }
}
