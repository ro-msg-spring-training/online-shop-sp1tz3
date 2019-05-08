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
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productCategoryId;
    private String name;
    private String description;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;

    public ProductCategory(String name, String description){
        this.name=name;
        this.description = description;
    }
}
