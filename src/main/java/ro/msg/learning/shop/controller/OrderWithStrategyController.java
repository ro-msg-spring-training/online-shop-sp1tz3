package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.service.OrderStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//not yet friend
@RestController
@RequiredArgsConstructor
public class OrderWithStrategyController {

    @PostMapping("/orders")
    List<Orders> createOrder(OrderStrategy strategy){
        ArrayList<Integer> products = new ArrayList<>();
        products.add(1);
        products.add(3);
        ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(5);
        quantities.add(20);
        //return createOrder(LocalDateTime.now(), 1, products, quantities);
        return strategy.createOrder(LocalDateTime.now(), 1, products, quantities);
    }
}
