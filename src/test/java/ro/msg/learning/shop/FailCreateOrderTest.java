package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.ProductsQuantitiesDTO;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.strategy.OrderStrategy;
import ro.msg.learning.shop.service.strategy.OrderStrategySingle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FailCreateOrderTest {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Test(expected = DataIntegrityViolationException.class)
    public void failCreateOrder(){
        OrderStrategy strategySingle = new OrderStrategySingle(productRepository, locationRepository);
        OrderService orderServiceSingle = new OrderService(strategySingle, customerRepository, orderDetailRepository,addressRepository,stockRepository,orderRepository);
        List<Integer> products = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        products.add(1); products.add(3);
        quantities.add(50); quantities.add(9999);
        ProductsQuantitiesDTO dto = new ProductsQuantitiesDTO(products, quantities);
        OrderInputDTO inputDTO = new OrderInputDTO(LocalDateTime.now(),1,dto);
        customerRepository.save(new Customer("x","x","x","x","x"));
        orderServiceSingle.createOrder(inputDTO);
    }
}
