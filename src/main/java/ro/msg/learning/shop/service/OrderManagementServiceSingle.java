package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.exception.StockNotFoundException;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceSingle{
    private final LocationManagementService locationService;
    private final StockManagementService stockService;

    @Transactional
    public Orders createOrder(LocalDateTime timestamp, Address deliveryAddress, ArrayList<Integer> products, ArrayList<Integer> quantities) {
        ArrayList<Location> locations = new ArrayList<>(locationService.listLocations());
        System.out.println(locationService.containsAll(products, quantities));
        return null;
    }
}
