package ro.msg.learning.shop.service.strategy;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.dto.OrderOutputDTO;
import ro.msg.learning.shop.exception.OrderNotCreatedException;
import ro.msg.learning.shop.exception.ProductNotFoundException;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.StockService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderStrategyAbundant implements OrderStrategy {
    private final ProductRepository productRepository;
    private final StockService stockService;

    @Override
    public List<OrderOutputDTO> makeOrderOutputDTO(OrderInputDTO input) throws OrderNotCreatedException {
        List<OrderOutputDTO> orderOutputDTOS = new ArrayList<>();
        List<Integer> products = input.getProductsQuantitiesDTO().getProducts();
        List<Integer> quantities = input.getProductsQuantitiesDTO().getQuantities();
        products.forEach(p->{
            orderOutputDTOS.add(new OrderOutputDTO(stockService.containsMostProduct(p, quantities.get(products.indexOf(p))).getLocation(),
                    productRepository.findById(p).orElseThrow(ProductNotFoundException::new),
                    quantities.get(products.indexOf(p))));
        });
        return orderOutputDTOS;
    }
}
