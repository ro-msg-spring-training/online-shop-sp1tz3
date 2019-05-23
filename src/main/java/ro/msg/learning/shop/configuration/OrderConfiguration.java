package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.strategy.OrderStrategyAbundant;
import ro.msg.learning.shop.service.strategy.OrderStrategySingle;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

@Configuration
public class OrderConfiguration {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private StockRepository stockRepository;

    @Bean
    public OrderStrategy selectStrategy(@Value("${strategy}") Strategies strategy){
        switch(strategy){
            case SINGLE:
                return new OrderStrategySingle(productRepository,locationRepository);
            case ABUNDANT:
                return new OrderStrategyAbundant(productRepository, locationRepository);

            default: return null;
        }
    }
    private enum Strategies{
        SINGLE, ABUNDANT
    }
}

