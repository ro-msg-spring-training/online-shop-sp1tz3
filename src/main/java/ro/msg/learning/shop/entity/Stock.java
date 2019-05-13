package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location location;

    private Integer quantity;

    public Stock(Product product, Location location, Integer quantity){
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "stock id: " + stockId + ", "+ product.toString() + ", location: " + location.toString() + ", quantity: "+ quantity;
    }

}
