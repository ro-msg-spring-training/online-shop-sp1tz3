package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ro.msg.learning.shop.service.strategy.OrderServiceAbundant;
import ro.msg.learning.shop.service.strategy.OrderServiceSingle;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

@Configuration
public class OrderConfiguration {

    @Autowired
    Environment env;

    @Bean
    public OrderStrategy myStrategy(){
        String strategy = env.getProperty("strategy");
        switch(strategy){
            case "single_location":
                return new OrderServiceSingle();
            case "most_abundant":
                return new OrderServiceAbundant();
        }
        return null;
    };
}
