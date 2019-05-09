package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.RepositoryFactory;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceSingle{
    private final LocationManagementService locationService;
    private final ProductManagementService productService;
    private final RepositoryFactory repo;

    @Transactional
    public Orders createOrder() {
        ArrayList<Location> locations = new ArrayList<>(locationService.listLocations());
        ArrayList<Product> products = new ArrayList<>(productService.listProducts());
        locations.forEach(l->{
            System.out.println("location: " + l.getLocationId());
            List<Stock> stocks = l.getStocks();
            stocks.forEach(s->{
                System.out.println(s.toString());
            }
            );
        });
        return null;
    }
}
