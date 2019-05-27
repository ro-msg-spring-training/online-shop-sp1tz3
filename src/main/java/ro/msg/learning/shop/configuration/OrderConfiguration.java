package ro.msg.learning.shop.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.strategy.OrderStrategyAbundant;
import ro.msg.learning.shop.service.strategy.OrderStrategySingle;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

@Configuration
@RequiredArgsConstructor
public class OrderConfiguration {
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

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

