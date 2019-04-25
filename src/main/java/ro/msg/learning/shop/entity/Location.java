package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;
    private String name;
    private String country;
    private String city;
    private String county;
    private String streetAddress;

    public Location(String name, String country, String city, String county, String streetAddress){
        this.name = name;
        this.country = country;
        this.city = city;
        this.county = county;
        this.streetAddress = streetAddress;
    }
}
