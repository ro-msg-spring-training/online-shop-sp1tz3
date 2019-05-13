package ro.msg.learning.shop.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {
    private List<Integer> products;
    private List<Integer> quantities;
    private Integer deliveryAddressId;
}
