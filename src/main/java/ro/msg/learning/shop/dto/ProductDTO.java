package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Product;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private Integer category;
    private Integer supplier;
    private String imageUrl;

    public static ProductDTO ofEntity(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setId(product.getProductId());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setCategory(product.getProductCategory().getProductCategoryId());
        productDTO.setSupplier(product.getSupplier().getSupplierId());
        productDTO.setImageUrl(product.getImageURL());
        return productDTO;
    }

}
