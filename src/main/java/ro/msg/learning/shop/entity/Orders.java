package ro.msg.learning.shop.entity;

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
    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location location;
    @ManyToOne(optional = false)
    @JoinColumn(name = "customerId", referencedColumnName = "customerId")
    private Customer customer;
    private LocalDateTime createdAt;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    public Orders(Location location, Customer customer, LocalDateTime createdAt, String country, String city, String county, String streetAddress){
        this.location= location;
        this.customer = customer;
        this.createdAt = createdAt;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }
}
