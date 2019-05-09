package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.exception.OrderNotFoundException;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.OrderManagementServiceSingle;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderManagementServiceSingle service;

    @GetMapping("/orders")
    Orders all(){
        ArrayList<Integer> products = new ArrayList<>();
        products.add(2);
        products.add(3);
        products.add(4);
        ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(2);
        quantities.add(50);
        quantities.add(1210);
        return service.createOrder(null,null,products, quantities);
    }
}
