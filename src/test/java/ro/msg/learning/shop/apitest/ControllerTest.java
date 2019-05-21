package ro.msg.learning.shop.apitest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.ProductDTO;
import ro.msg.learning.shop.dto.ProductsQuantitiesDTO;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.strategy.OrderStrategy;
import ro.msg.learning.shop.service.strategy.OrderStrategyAbundant;
import ro.msg.learning.shop.service.strategy.OrderStrategySingle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControllerTest extends AbstractTest{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private AddressRepository addressRepository;


    @Override
    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void listPorudcts() throws Exception{
        String uri = "/products";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assert status == 200;
        String content = mvcResult.getResponse().getContentAsString();
        ProductDTO[] productDTOS = super.mapFromJson(content, ProductDTO[].class);
        for(ProductDTO dto: productDTOS){
            System.out.println(dto.toString());
        }
        assert productDTOS.length > 0;
    }

    @Test
    public void createOrderSingle() throws Exception{
        String uri = "/orders";
        LocationService locationService = new LocationService(locationRepository);
        OrderStrategy strategy = new OrderStrategySingle(productRepository, locationService);
        OrderService orderService = new OrderService(strategy, customerRepository, orderDetailRepository,addressRepository,stockRepository,orderRepository);
        List<Integer> products = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        products.add(1); products.add(3);
        quantities.add(50); quantities.add(100);
        ProductsQuantitiesDTO dto = new ProductsQuantitiesDTO(products, quantities);
        OrderInputDTO inputDTO = new OrderInputDTO(LocalDateTime.now(),1,dto);
        Orders newOrder = orderService.createOrder(inputDTO);
        String inp = "{\n" +
                "        \"timestamp\": \"2001-05-21T14:09\",\n" +
                "        \"deliveryAddressId\": 3,\n" +
                "        \"productsQuantitiesDTO\": {\n" +
                "        \t\"products\": [1,3],\n" +
                "        \t\"quantities\": [50,500]\n" +
                "        }\n" +
                "    }";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inp)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assert status == 200;
    }

    @Test
    public void createOrderAbundant() throws Exception{
        String uri = "/orders";
        StockService stockService = new StockService(stockRepository);
        OrderStrategy strategy = new OrderStrategyAbundant(productRepository, stockService);
        OrderService orderService = new OrderService(strategy, customerRepository, orderDetailRepository,addressRepository,stockRepository,orderRepository);
        List<Integer> products = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        products.add(1); products.add(3);
        quantities.add(50); quantities.add(100);
        ProductsQuantitiesDTO dto = new ProductsQuantitiesDTO(products, quantities);
        OrderInputDTO inputDTO = new OrderInputDTO(LocalDateTime.now(),1,dto);
        //Orders newOrder = orderService.createOrder(inputDTO);
        String inp = "{\n" +
                "        \"timestamp\": \"2001-05-21T14:09\",\n" +
                "        \"deliveryAddressId\": 3,\n" +
                "        \"productsQuantitiesDTO\": {\n" +
                "        \t\"products\": [1,3],\n" +
                "        \t\"quantities\": [50,500]\n" +
                "        }\n" +
                "    }";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inp)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assert status == 200;
    }
}
