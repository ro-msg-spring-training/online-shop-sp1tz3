package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stock_product",
    joinColumns = {@JoinColumn(name = "stockId")},
    inverseJoinColumns = {@JoinColumn(name = "productId")})
    private List<Product> product;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stock_location",
    joinColumns = {@JoinColumn(name = "stockId")},
    inverseJoinColumns = {@JoinColumn(name = "locationId")})
    private List<Location> location;
    private Integer quantity;

    public Stock(List<Product> product, List<Location> location, Integer quantity){
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }
}
