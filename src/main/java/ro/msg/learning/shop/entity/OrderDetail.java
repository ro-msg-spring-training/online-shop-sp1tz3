package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailId;

    @OneToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders order;
    @OneToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;
    private Integer quantity;

    public OrderDetail(Orders order, Product product, Integer quantity){
        this.order = order;
        this.product= product;
        this.quantity = quantity;
    }
}
