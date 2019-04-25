package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Product product;
    private Location location;
    private Integer quantity;
}
