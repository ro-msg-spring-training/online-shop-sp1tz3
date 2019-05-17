package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderInputDTO {
    private LocalDateTime timestamp;
    private Integer deliveryAddressId;
    private ProductsQuantitiesDTO productsQuantitiesDTO;
}
