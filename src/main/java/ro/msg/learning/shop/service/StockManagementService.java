package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.StockNotFoundException;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockManagementService {
    private final RepositoryFactory repositoryFactory;

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

    @Transactional
    public Stock update(int id, Integer quantity){
        StockRepository repo = repositoryFactory.createStockRepository();
        Stock stock = repo.findById(id).orElseThrow(StockNotFoundException::new);
        stock.setQuantity(quantity);
        return repo.save(stock);
    }

    @Transactional
    public Stock containsaMostProduct(int productId, int quantity){
        List<Stock> stocks = listStocks();
        int maxContainedNb = 0;
        for(Stock s: stocks){
            if(s.getProduct().getProductId().equals(productId) && s.getQuantity() >= quantity && s.getQuantity() > maxContainedNb){
                maxContainedNb = s.getQuantity();
            }
        }
        if(maxContainedNb >= quantity)
            for(Stock s: stocks){
                if(s.getProduct().getProductId().equals(productId) && s.getQuantity().equals(maxContainedNb)){
                    return update(s.getStockId(), s.getQuantity()-quantity);
                }
            }
        return null;
    }
}
