package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.exception.OrderNotFoundException;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.service.OrderManagementServiceSingle;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderManagementServiceSingle service;

    @GetMapping("/orders")
    Orders all(){
        return service.createOrder();
    }
}
