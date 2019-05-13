package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.exception.LocationNotFoundException;
import ro.msg.learning.shop.repository.AddressRepository;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.repository.data.DataRepositoryFactory;
import ro.msg.learning.shop.repository.hibernate.HibernateRepositoryFactory;
import ro.msg.learning.shop.service.LocationManagementService;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ShopApplicationTests {

	@Autowired
	LocationRepository locRepo;
	@Autowired
	AddressRepository adrRepo;

	@Test
	public void contextLoads(){

	}

	@Test
	public void addLocation(){
		Address adr1 = new Address("a","a","a","a");
		adrRepo.save(adr1);
		Location loc1 = new Location("locatie1", adr1);
		locRepo.save(loc1);
		assert locRepo.findAll().contains(loc1);
	}

	@Test
	public void singleLocation(){

	}

	@Test
	public void abundantLocation(){

	}
}
