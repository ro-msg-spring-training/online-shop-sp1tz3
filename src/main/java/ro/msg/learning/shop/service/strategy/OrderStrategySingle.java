package ro.msg.learning.shop.service.strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.OrderOutputDTO;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.exception.OrderNotCreatedException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.LocationService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderStrategySingle implements OrderStrategy{
    private final ProductRepository productRepository;
    private final LocationService locationService;

    @Override
    public List<OrderOutputDTO> makeOrderOutputDTO(OrderInputDTO input) throws OrderNotCreatedException {
        List<OrderOutputDTO> orderOutputDTOS = new ArrayList<>();
        Location location = locationService.findSingleLocation(input.getProductsQuantitiesDTO());
        List<Integer> products = input.getProductsQuantitiesDTO().getProducts();
        List<Integer> quantities = input.getProductsQuantitiesDTO().getQuantities();
        products.forEach(p->{
            orderOutputDTOS.add(new OrderOutputDTO(location,
                    productRepository.findById(p).orElseThrow(ProductNotFoundException::new),
                    quantities.get(products.indexOf(p))));
        });
        return orderOutputDTOS;
    }
}
