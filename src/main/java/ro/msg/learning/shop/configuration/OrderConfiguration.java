package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ro.msg.learning.shop.service.*;

@Configuration
public class OrderConfiguration {

    @Autowired
    Environment env;

    @Bean
    public OrderStrategy myStrategy(){
        String strategy = env.getProperty("strategy");
        OrderStrategy myStrategy = null;
        switch(strategy){
            case "single_location":
                return new OrderSingle();
            case "most_abundant":
                return new OrderAbundant();
        }
        return null;
    };
}
