package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.exception.OrderNotFoundException;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.OrderManagementServiceAbundant;
import ro.msg.learning.shop.service.OrderManagementServiceSingle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


//well at least this mostly works
//@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderManagementServiceAbundant service;

    @PostMapping("/orders")
    List<Orders> createOrder(){
        ArrayList<Integer> products = new ArrayList<>();
        products.add(1);
        products.add(3);
        ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(5);
        quantities.add(20);
        return service.createOrder(LocalDateTime.now(),1 ,products, quantities);
    }
}
