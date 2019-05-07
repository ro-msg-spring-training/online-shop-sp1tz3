package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;
    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;
    @OneToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location location;
    private Integer quantity;

    public Stock(Product product, Location location, Integer quantity){
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }
}
