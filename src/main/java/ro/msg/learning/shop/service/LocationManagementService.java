package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.exception.LocationNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationManagementService {
    private final RepositoryFactory repositoryFactory;

    @Transactional
    public List<Location> listLocations() {return repositoryFactory.createLocationRepository().findAll();}

    @Transactional
    public Location addLocation(String name, Address address) { return repositoryFactory.createLocationRepository().save(
            new Location(name, address)
    );}

    @Transactional
    public void removeLocation(int id){
        LocationRepository repo = repositoryFactory.createLocationRepository();
        Location location = repo.findById(id).orElseThrow(LocationNotFoundException::new);
        repo.remove(location);
    }
}
