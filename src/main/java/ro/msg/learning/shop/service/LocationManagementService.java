package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Location;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.RepositoryFactory;
import ro.msg.learning.shop.exception.LocationNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Transactional
    public Location containsAll(List<Integer> products, List<Integer> quantities){
        List<Location> locations = listLocations();
        int cnt = products.size();
        for(Location l: locations){
            for(Stock s: l.getStocks()){
                if(products.contains(s.getProduct().getProductId()) && s.getQuantity() >= quantities.get(products.indexOf(s.getProduct().getProductId()))){
                    cnt--;
                }
            }
            if(cnt==0){
                return l;
            } else {
                cnt = products.size();
            }
        }
        return null;
    }

}
