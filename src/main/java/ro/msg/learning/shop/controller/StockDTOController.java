package ro.msg.learning.shop.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.StockDTO;
import ro.msg.learning.shop.service.StockDTOManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockDTOController {

    private final StockDTOManagementService service;

    @GetMapping("/stocks")
    List<StockDTO> all(){
        return service.listStocks();
    }
}
