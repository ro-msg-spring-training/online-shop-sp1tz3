package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsQuantitiesDTO {
    private List<Integer> products;
    private List<Integer> quantities;
}
