package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orderDetail_order",
    joinColumns = {@JoinColumn(name = "orderDetailId")},
    inverseJoinColumns = {@JoinColumn(name = "orderId")})
    private List<Orders> orders;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orderDetail_product",
    joinColumns = {@JoinColumn(name = "orderDetailId")},
    inverseJoinColumns = {@JoinColumn(name = "productId")})
    private List<Product> products;
    private Integer quantity;

    public OrderDetail(List<Orders> orders, List<Product> products, Integer quantity){
        this.orders = orders;
        this.products= products;
        this.quantity = quantity;
    }
}
