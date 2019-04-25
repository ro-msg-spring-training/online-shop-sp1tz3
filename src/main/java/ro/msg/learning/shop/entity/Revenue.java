package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer revenueId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "locationId", referencedColumnName = "locationId")
    private Location location;
    private LocalDate date;
    private BigDecimal sum;

    public Revenue(Location location, LocalDate date, BigDecimal sum){
        this.location = location;
        this.date = date;
        this.sum = sum;
    }
}
