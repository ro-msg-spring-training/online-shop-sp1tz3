package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.ProductsQuantitiesDTO;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Orders;

import ro.msg.learning.shop.exception.OrderNotFoundException;
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

@RunWith(SpringRunner.class)
@DataJpaTest
public class ShopApplicationTests {
	@Autowired
	private LocationRepository locRepo;
	@Autowired
	private AddressRepository adrRepo;
	@Autowired
	private StockRepository stockRepo;
	@Autowired
	private CustomerRepository custRepo;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;

	@Test
	public void addLocation(){
		Address adr1 = new Address("a","a","a","a");
		adrRepo.save(adr1);
		Location loc1 = new Location("locatie1", adr1);
		locRepo.save(loc1);
		Iterable<Location> locs = locRepo.findAll();
		List<Location> locations = new ArrayList<>();
		locs.forEach(locations::add);
		assert locations.contains(loc1);
	}

	@Test
	public void singleLocation(){
		LocationService locationService = new LocationService(locRepo);
		List<Integer> products = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		products.add(1); products.add(3);
		quantities.add(50); quantities.add(100);
		ProductsQuantitiesDTO dto = new ProductsQuantitiesDTO(products, quantities);
		Location location = locationService.findSingleLocation(dto);
		assert location.getLocationId().equals(2);
	}

	@Test
	public void abundantLocation(){
		StockService stockService = new StockService(stockRepo);
		assert stockService.containsMostProduct(1,50).getLocation().getLocationId().equals(1);
		assert stockService.containsMostProduct(2,1000).getLocation().getLocationId().equals(3);
		assert stockService.containsMostProduct(3, 1000).getLocation().getLocationId().equals(2);
	}

	@Test
	public void createOrderSingleLocation(){
		LocationService locationService = new LocationService(locRepo);
		OrderStrategy strategy = new OrderStrategySingle(productRepository, locationService);
		OrderService orderService = new OrderService(strategy, custRepo, orderDetailRepository,adrRepo,stockRepo,orderRepository);
		List<Integer> products = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		products.add(1); products.add(3);
		quantities.add(50); quantities.add(100);
		ProductsQuantitiesDTO dto = new ProductsQuantitiesDTO(products, quantities);
		OrderInputDTO inputDTO = new OrderInputDTO(LocalDateTime.now(),1,dto);
		Orders newOrder = orderService.createOrder(inputDTO);
		assert orderRepository.findById(newOrder.getOrderId()).orElseThrow(OrderNotFoundException::new).equals(newOrder);
	}

	@Test
	public void createOrderAbundantLocation(){
		StockService stockService = new StockService(stockRepo);
		OrderStrategy strategy = new OrderStrategyAbundant(productRepository, stockService);
		OrderService orderService = new OrderService(strategy, custRepo, orderDetailRepository, adrRepo, stockRepo, orderRepository);
		List<Integer> products = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		products.add(1); products.add(3);
		quantities.add(50); quantities.add(100);
		ProductsQuantitiesDTO dto = new ProductsQuantitiesDTO(products, quantities);
		OrderInputDTO inputDTO = new OrderInputDTO(LocalDateTime.now(),1,dto);
		Orders newOrder = orderService.createOrder(inputDTO);
		assert orderRepository.findById(newOrder.getOrderId()).orElseThrow(OrderNotFoundException::new).equals(newOrder);
	}

}
