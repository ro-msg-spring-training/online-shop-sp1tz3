package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    OrderService service;

    @PostMapping("/orders")
    public Orders createOrder(@RequestBody OrderInputDTO dto){
        return service.createOrder(dto);
    }
}
