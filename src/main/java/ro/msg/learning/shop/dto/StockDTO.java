package ro.msg.learning.shop.dto;

import lombok.Data;
import ro.msg.learning.shop.entity.Stock;

@Data
public class StockDTO {
    private Integer id;
    private Integer product;
    private Integer location;
    private Integer quantity;

    public static StockDTO ofEntity(Stock stock){
        StockDTO dto = new StockDTO();
        dto.setId(stock.getStockId());
        dto.setProduct(stock.getProduct().getProductId());
        dto.setLocation(stock.getLocation().getLocationId());
        dto.setQuantity(stock.getQuantity());
        return dto;
    }
}
