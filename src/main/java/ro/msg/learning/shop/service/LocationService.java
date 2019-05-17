package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.ProductsQuantitiesDTO;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    @Transactional
    public Location findSingleLocation(ProductsQuantitiesDTO productsQuantitiesDTO){
        Iterable<Location> locations = locationRepository.findAll();
        List<Location> locationList = new ArrayList<>();
        locations.forEach(locationList::add);
        List<Integer> products = productsQuantitiesDTO.getProducts();
        List<Integer> quantities = productsQuantitiesDTO.getQuantities();

        int cnt = productsQuantitiesDTO.getProducts().size();
        for(Location l: locationList){
            for(Stock s: l.getStocks()){
                if(products.contains(s.getProduct().getProductId()) && s.getQuantity() >= quantities.get(products.indexOf(s.getProduct().getProductId())))
                    cnt--;
            }
            if(cnt ==0){
                return l;
            }else {
                cnt = products.size();
            }
        }
        return null;
    }
}
