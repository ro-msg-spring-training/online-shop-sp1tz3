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


    @ManyToOne(optional = false)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders order;


    @ManyToOne(optional = false)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;


    private Integer quantity;

    public OrderDetail(Orders order, Product product, Integer quantity){
        this.order = order;
        this.product= product;
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "order detail id: " + orderDetailId + " orderId: " + order.getOrderId() + " product id: " + product.getProductId() + " quantity: " + quantity;
    }
}
