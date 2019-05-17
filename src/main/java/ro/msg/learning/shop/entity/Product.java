package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

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

    @Override
    public String toString(){
        return "product id: "+ productId + ", name: "+ name + ", description: " + description + ", product category: "+ productCategory.getName() + ", supplier: " + supplier.getName() + ", image URL: " + imageURL;
    }
}
