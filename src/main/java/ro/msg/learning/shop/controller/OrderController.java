package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.OrderInputDTO;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping("/orders")
    private Orders createOrder(@RequestBody OrderInputDTO dto){
        return service.createOrder(dto);
    }
}
