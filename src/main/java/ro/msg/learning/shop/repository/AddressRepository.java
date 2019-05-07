package ro.msg.learning.shop.repository;

import ro.msg.learning.shop.entity.Address;
import java.util.List;
import java.util.Optional;

public interface AddressRepository {
    List<Address> findAll();
    Address save(Address address);
    void remove(Address address);
    Optional<Address> findById(int id);
}
