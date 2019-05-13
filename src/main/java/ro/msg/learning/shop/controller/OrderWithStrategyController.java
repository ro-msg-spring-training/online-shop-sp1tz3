package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//not yet friend
@RestController
@RequiredArgsConstructor
public class OrderWithStrategyController {
    @Autowired
    private OrderStrategy strategy;

    @PostMapping("/orders")
    List<Orders> createOrder(){
        ArrayList<Integer> products = new ArrayList<>();
        products.add(1);
        products.add(3);
        ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(5);
        quantities.add(20);
        return strategy.createOrder(LocalDateTime.now(), 1, products, quantities);
    }
}
