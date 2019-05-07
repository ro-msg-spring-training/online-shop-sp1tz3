package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.exception.AddressNotFoundException;
import ro.msg.learning.shop.repository.AddressRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Address> listAddresses() {return repositoryFactory.createAddressRepository().findAll();}

    @Transactional
    public Address addAddress(String country, String city, String county, String streetAddress){
        return repositoryFactory.createAddressRepository().save(
                new Address(country, city, county, streetAddress)
        );
    }

    @Transactional
    public void removeAddress(int id){
        AddressRepository repo = repositoryFactory.createAddressRepository();
        Address address = repo.findById(id).orElseThrow(AddressNotFoundException::new);
        repo.remove(address);
    }
}
