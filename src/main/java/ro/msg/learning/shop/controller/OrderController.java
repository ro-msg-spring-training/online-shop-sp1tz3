package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.service.OrderManagementServiceSingle;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private OrderManagementServiceSingle service;

    @GetMapping("/orders")
    void meh(){
        service.createOrder();
    }
}
