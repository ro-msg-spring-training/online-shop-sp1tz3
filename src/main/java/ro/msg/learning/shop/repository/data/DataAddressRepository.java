package ro.msg.learning.shop.repository.data;

import org.springframework.data.repository.Repository;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.repository.AddressRepository;

public interface DataAddressRepository extends Repository<Address, Integer>, AddressRepository {
    void delete(Address address);

    @Override
    default void remove(Address address) {delete(address);}
}
