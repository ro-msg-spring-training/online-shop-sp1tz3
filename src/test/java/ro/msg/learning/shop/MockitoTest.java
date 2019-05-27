package ro.msg.learning.shop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.annotation.Order;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.ProductsQuantitiesDTO;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.OrderNotCreatedException;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;
import ro.msg.learning.shop.service.strategy.OrderStrategy;
import ro.msg.learning.shop.service.strategy.OrderStrategySingle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class MockitoTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private LocationRepository locationRepository = Mockito.mock(LocationRepository.class);
    private CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
    private AddressRepository addressRepository = Mockito.mock(AddressRepository.class);
    private StockRepository stockRepository = Mockito.mock(StockRepository.class);

    @Mock
    List<Location> locationList;

    @Mock
    List<Product> productList;

    @Mock
    List<Stock> stockList;

    @Before
    public void init(){
        Address a1 = new Address("x","x","x","x");
        Address a2 = new Address("y","y","y","y");
        Address a3 = new Address("z","z","z","z");
        when(addressRepository.findById(1)).thenReturn(Optional.of(a1));
        when(addressRepository.findById(2)).thenReturn(Optional.of(a2));
        when(addressRepository.findById(3)).thenReturn(Optional.of(a3));

        Location l1 = new Location(1,"l1", a1);
        Location l2 = new Location(2,"l2", a2);
        Location l3 = new Location(3,"l3", a3);

        ProductCategory c1 = new ProductCategory("umm", "hmmm");
        ProductCategory c2 = new ProductCategory("hmm", "umm");

        Supplier s1 = new Supplier("eh");
        Supplier s2 = new Supplier("meh");

        Product p1 = new Product("ce","portocale", new BigDecimal(50), 100, c1, s1, "pfff");
        Product p2 = new Product("ce2","mere", new BigDecimal(50), 100, c2, s2, "pfff");

        locationList = Arrays.asList(l1,l2,l3);
        when(locationRepository.findAll()).thenReturn(locationList);
        when(locationRepository.findSingleLocation(1, 50)).thenReturn(Arrays.asList(l1,l3));
        when(locationRepository.findSingleLocation(2,300)).thenReturn(Arrays.asList(l2,l3));
        when(locationRepository.findAbundantLocation(1, 100)).thenReturn(Arrays.asList(l3,l1));
        when(locationRepository.findAbundantLocation(2,100)).thenReturn(Arrays.asList(l3,l2));

        productList = Arrays.asList(p1,p2);
        when(productRepository.findAll()).thenReturn(productList);
        when(productRepository.findById(1)).thenReturn(Optional.of(p1));
        when(productRepository.findById(2)).thenReturn(Optional.of(p2));

        Customer customer = new Customer("bla","bla","bla","bla","bla");
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Stock st1 = new Stock(p1,l1,1000);
        Stock st2 = new Stock(p1,l3,1001);
        Stock st3 = new Stock(p2,l2,1000);
        Stock st4 = new Stock(p2,l3,1001);

        stockList = Arrays.asList(st1,st2,st3,st4);
        when(stockRepository.findAll()).thenReturn(stockList);
    }

    @Test
    public void successSingleLocation() throws OrderNotCreatedException {
        List<Integer> products = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        products.add(1);
        quantities.add(50);
        products.add(2);
        quantities.add(1001);
        ProductsQuantitiesDTO dto = new ProductsQuantitiesDTO(products, quantities);
        OrderInputDTO input = new OrderInputDTO(LocalDateTime.now(), 1, dto);
        LocationService locationService = new LocationService(locationRepository);
        //Assert.assertEquals(locationList.get(2), locationService.findSingleLocation(dto));
    }

    @Test
    public void successAbundantLocation() throws OrderNotCreatedException {
        StockService stockService = new StockService(stockRepository);
        Assert.assertEquals(locationList.get(0), stockService.containsMostProduct(1,50));
    }

}
