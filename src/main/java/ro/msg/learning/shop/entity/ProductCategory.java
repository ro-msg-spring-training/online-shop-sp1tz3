package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productCategoryId;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

    public ProductCategory(String name, String description){
        this.name=name;
        this.description = description;
    }
}
