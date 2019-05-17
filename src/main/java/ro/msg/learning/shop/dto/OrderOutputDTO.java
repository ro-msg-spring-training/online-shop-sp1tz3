package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

@Data
@AllArgsConstructor
public class OrderOutputDTO {
    private Location location;
    private Product product;
    private Integer quantity;
}
