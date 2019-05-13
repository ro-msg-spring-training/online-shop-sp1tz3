package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderDTO;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.exception.OrderNotCreatedException;
import ro.msg.learning.shop.service.strategy.OrderStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderWithStrategyController {
    @Autowired
    private OrderStrategy strategy;

    @PostMapping("/orders")
    List<Orders> createOrder(@RequestBody OrderDTO dto){
        try{
            return strategy.createOrder(LocalDateTime.now(), dto.getDeliveryAddressId(), dto.getProducts(), dto.getQuantities());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
