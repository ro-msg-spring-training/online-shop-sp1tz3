package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;
    private String name;

    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "addressId")
    private Address address;

    @OneToMany(mappedBy = "location")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "location")
    private List<Orders> orders;

    @OneToMany(mappedBy = "location")
    private List<Revenue> revenues;

    public Location(String name, Address address){
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString(){
        return "location id: " +locationId + ", name: " + name + ", " + address.toString();
    }
}
