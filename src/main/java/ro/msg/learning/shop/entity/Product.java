package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    @ManyToOne(optional = false)
    @JoinColumn(name ="productCategoryId", referencedColumnName = "productCategoryId")
    private ProductCategory productCategory;
    @ManyToOne(optional = false)
    @JoinColumn(name = "supplierId", referencedColumnName = "supplierId")
    private Supplier supplier;
    private String imageURL;

    public Product(String name, String description, BigDecimal price, double weight, ProductCategory category, Supplier supplier, String imageURL){
        this.name= name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = category;
        this.supplier = supplier;
        this.imageURL = imageURL;
    }
}
