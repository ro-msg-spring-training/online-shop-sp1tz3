package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shippedFrom", referencedColumnName = "locationId")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private Customer customer;
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Orders(Location location, Customer customer, LocalDateTime createdAt, Address address){
        this.location= location;
        this.customer = customer;
        this.createdAt = createdAt;
        this.address = address;
    }
}
