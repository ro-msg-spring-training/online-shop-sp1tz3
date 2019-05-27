package ro.msg.learning.shop;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.dto.ProductsQuantitiesDTO;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Location;

import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.LocationService;
import ro.msg.learning.shop.service.StockService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@RequiredArgsConstructor
public class LocationTests {
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private StockRepository stockRepository;

	@Test
	public void addLocation(){
		Address adr1 = new Address("a","a","a","a");
		addressRepository.save(adr1);
		Location loc1 = new Location("locatie1", adr1);
		locationRepository.save(loc1);
		Iterable<Location> locs = locationRepository.findAll();
		List<Location> locations = new ArrayList<>();
		locs.forEach(locations::add);
		assert locations.contains(loc1);
	}

	@Test
	public void singleLocation(){
		LocationService locationService = new LocationService(locationRepository);
		List<Integer> products = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		products.add(1); products.add(3);
		quantities.add(50); quantities.add(100);
		List<Location> locations = new ArrayList<>();
		products.forEach(p->{
			locations.addAll(locationRepository.findSingleLocation(p,quantities.get(products.indexOf(p))));
		});
		List<Location> distinctLocations = locations.stream().distinct().collect(Collectors.toList());
		Location neededLocation = locationService.findSingleLocation(new ProductsQuantitiesDTO(products, quantities));
		Assert.assertTrue(distinctLocations.contains(neededLocation));
	}

	@Test
	public void abundantLocation(){
		StockService stockService = new StockService(stockRepository);
		List<Integer> products = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		products.add(1); products.add(3);
		quantities.add(50); quantities.add(100);
		List<Location> locations = new ArrayList<>();
		products.forEach(p->{
			Assert.assertEquals(stockService.containsMostProduct(p, quantities.get(products.indexOf(p))).getLocation(), (locationRepository.findAbundantLocation(p, quantities.get(products.indexOf(p))).get(0)));
		});
	}

	@Test
	public void failSingleLocation(){
		LocationService locationService = new LocationService(locationRepository);
		List<Integer> products = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		products.add(1); products.add(3);
		quantities.add(50); quantities.add(9999);
		List<Location> locations = new ArrayList<>();
		products.forEach(p->{
			locations.addAll(locationRepository.findSingleLocation(p,quantities.get(products.indexOf(p))));
		});
		List<Location> distinctLocations = locations.stream().distinct().collect(Collectors.toList());
		Location neededLocation = locationService.findSingleLocation(new ProductsQuantitiesDTO(products, quantities));
		Assert.assertNull(neededLocation);
	}

	@Test(expected = NullPointerException.class)
	public void failAbundantLocation(){
		StockService stockService = new StockService(stockRepository);
		List<Integer> products = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		products.add(1); products.add(3);
		quantities.add(50); quantities.add(9999);
		List<Location> locations = new ArrayList<>();
		products.forEach(p->{
			Assert.assertEquals(stockService.containsMostProduct(p, quantities.get(products.indexOf(p))).getLocation(), (locationRepository.findAbundantLocation(p, quantities.get(products.indexOf(p))).get(0)));
		});
	}




}
