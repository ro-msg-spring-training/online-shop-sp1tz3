package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Stock containsMostProduct(int productId, int quantity){
        Iterable<Stock> stocks = stockRepository.findAll();
        List<Stock> stockList = new ArrayList<>();
        stocks.forEach(stockList::add);
        int maxContained = 0;
        for(Stock s: stockList){
            if(s.getProduct().getProductId().equals(productId) && s.getQuantity() >= quantity && s.getQuantity() > maxContained)
                maxContained = s.getQuantity();
        }
        if(maxContained >= quantity){
            for(Stock s: stockList){
                if(s.getProduct().getProductId().equals(productId) && s.getQuantity().equals(maxContained))
                    return s;
            }
        }
        return null;
    }
}
