package ro.msg.learning.shop.service.strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.OrderOutputDTO;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.exception.OrderNotCreatedException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderStrategySingle implements OrderStrategy{
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    @Override
    public List<OrderOutputDTO> makeOrderOutputDTO(OrderInputDTO input) throws OrderNotCreatedException {
        List<OrderOutputDTO> orderOutputDTOS = new ArrayList<>();
        List<Integer> products = input.getProductsQuantitiesDTO().getProducts();
        List<Integer> quantities = input.getProductsQuantitiesDTO().getQuantities();
        List<Location> potentialLocations = new ArrayList<>();
        for(Integer p: products)
            potentialLocations.addAll(locationRepository.findSingleLocation(p, quantities.get(products.indexOf(p))));
        potentialLocations = potentialLocations.stream().distinct().collect(Collectors.toList());
        Location foundLocation = null;
        int cnt = input.getProductsQuantitiesDTO().getProducts().size();
        for(Location l: potentialLocations){
            for(Stock s: l.getStocks()){
                if(products.contains(s.getProduct().getProductId()) && s.getQuantity() >= quantities.get(products.indexOf(s.getProduct().getProductId())))
                    cnt--;
            }
            if(cnt ==0){
                foundLocation = l;
                break;
            }else {
                cnt = products.size();
            }
        }
        for(Integer p: products)
            orderOutputDTOS.add(new OrderOutputDTO(foundLocation,
                    productRepository.findById(p).orElseThrow(ProductNotFoundException::new),
                    quantities.get(products.indexOf(p))));
        return orderOutputDTOS;
    }
}
