package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
